package silence.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import silence.entity.Students;
import silence.service.StudentService;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月17日 上午11:52:41
* 控制器：学生类控制器
*/

@Controller
@RequestMapping("/stuAttendance")    //在此控制器中的所有请求映射均要添加stuAttendance前缀
public class StudentController {
	//@Resource为自动注入的注解，表明此类要通过Spring容器完成注入
	@Resource
	StudentService studentService;
	
	 /**
	   * 
	   * @param stuNo 学号， stuPwd 密码
	   * @return
	   */
	  @RequestMapping(value="/studentLogin",method=RequestMethod.POST)
	  public ModelAndView studentLogin(String stuNo,String stuPwd){
		  String info = null;
		  ModelAndView mv = null;
		  Students student=studentService.studentLogin(stuNo, stuPwd);
		  if(student!=null){
			  //创建一个模型和视图
			  mv=new ModelAndView("student");     //这里的路径是真实的JSP页面路径
		  }else{
			  info="用户名或者密码不存在！";
		  }
		  //向页面返回数据
		  mv.addObject("info",info);
		  return mv;
	  }
}
