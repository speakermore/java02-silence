package silence.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import silence.entity.Students;
import silence.service.StudentService;
import silence.service.TeachersService;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年6月19日 上午11:44:39
*/
@Controller
@RequestMapping("/tecAttendance") //在此控制器中的所有请求映射均要添加tecAttendance前缀
public class TeachersController {
	@Resource
	TeachersService teacherService; 
	@Resource
	StudentService studentService;
	/**
	 * 袁云：老师添加学生用户，添加内容为学号，学生所在班级，学生姓名，密码，学生初始积分，学生性别
	 * @param stu 封装了学生信息的实体对象
	 * @return 大于0表示添加成功，否则失败
	 */
	@RequestMapping(value="/reg", method = RequestMethod.POST)
	public ModelAndView reg(Students stu){
		String reg= "";
		Students students = studentService.getStudentByStuNo(stu.getStuNo());
		//创建一个模型和试图用于设置返回页面及向页面返回数据
		ModelAndView mv = new ModelAndView("reg");
		if(students == null){
			int result = teacherService.reg(stu);
			if(result > 0){
				reg = "注册成功！";
			}else {
				reg = "注册失败！";
			}
		}else {
			reg = "该用户已存在，请重新注册！";
		}
		// 向页面返回数据
		mv.addObject("reg",reg);
		return mv;
	}
	@RequestMapping(value="/reg",method=RequestMethod.GET)
	public String reg(){
		return "reg";
	}
}
