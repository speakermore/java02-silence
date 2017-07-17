package silence.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import silence.entity.Students;
import silence.service.TeachersService;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年6月19日 上午11:44:39
*/
@Controller
@RequestMapping("/tecAttendance") //在此控制器中的所有请求映射均要添加message前缀
public class TeachersController {
	@Resource
	TeachersService teacherService;
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ModelAndView reg(Students stu){
		int result = teacherService.reg(stu);
		ModelAndView mv = new ModelAndView("reg");
		String success = "注册成功！";
		mv.addObject("success",success);
		return mv;
	}
}
