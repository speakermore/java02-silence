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
	   * @author 连慧
	   * @param stuNo 学号， stuPwd 密码
	   * @return
	   */
	  @RequestMapping(value="/studentLogin",method=RequestMethod.POST)
	  public ModelAndView studentLogin(String stuNo,String stuPwd){
		  String info = "";
		  ModelAndView mv = new ModelAndView();     //这里的路径是真实的JSP页面路径;
		  Students student=studentService.studentLogin(stuNo, stuPwd);
		  if(student!=null){
			  //创建一个模型和视图
			  mv.setViewName("student");
		  }else{
			  info="用户名或者密码不存在！";
			  mv.setViewName("studentLogin");
		  }
		  //向页面返回数据
		  mv.addObject("info",info);
		  return mv;
	  }
	  
	  
	  /**
	   * @author 连慧
	   *get请求的方法：地址栏访问页面的时候都是get请求访问，
	   *而上面定义的业务是通过POST方式请求访问的，
	   *所以需要定义一个GET请求访问的逻辑路径才能访问到页面
	   * @return
	   */
	  @RequestMapping(value="/studentLogin",method=RequestMethod.GET)
	  public String login(){
		  return "studentLogin";
	  }
	  
}
