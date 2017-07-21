package silence.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import silence.entity.Students;
import silence.entity.Teacher;
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
	
	/**
	 * 袁云：老师登录功能
	 * @param stu 页面传过来的老师姓名和老师密码
	 * @return 老师对象
	 */
	@RequestMapping(value="/teacherLogin", method = RequestMethod.POST)
	public ModelAndView teacherLogin(String tecName,String tecPwd,HttpSession session){
		String tecLogin= "";
		Teacher teacher = teacherService.getTeacherByName(tecName);
		//创建一个模型和试图用于设置返回页面及向页面返回数据
		ModelAndView mv = new ModelAndView();
		if(teacher != null){
			if(tecPwd.equals(teacher.getTecPwd())){
				tecLogin = "登录成功!";
				session.setAttribute("teacher", teacher);
				mv.setViewName("teacher");
			}else {
				tecLogin = "姓名或密码错误，请重新输入！";
				mv.setViewName("teacherLogin");
			}
		}else {
			tecLogin = "姓名或密码错误，请重新输入！";
			// 设置跳转的页面
			mv.setViewName("teacherLogin");
		}
		// 向页面返回数据
		mv.addObject("tecLogin",tecLogin);
		return mv;
	}
	@RequestMapping(value="/teacherLogin",method=RequestMethod.GET)
	public String teacherLogin(){
		return "teacherLogin";
	}
	
	/**
	 * 袁云：用于点击修改密码按钮时跳转到修改密码页面
	 */
	@RequestMapping(value="/jumpUpdatePage",method=RequestMethod.GET)
	public String jumpUpdatePage(){
		return "teacherUpdatePwd";
	}
	
	/**
	 * 袁云：老师验证密码的功能
	 * @param  页面传过来的id和姓名
	 * @return 老师对象
	 */
	@RequestMapping(value="/verifyPwd",method=RequestMethod.POST)
	@ResponseBody //ajax注解，把verifyPwd当做字符串返回给页面
	public String verifyPwd(Integer id,String oldPwd){
		String verifyPwd = "";
		Teacher teacher = teacherService.getTecById(id);
		if(oldPwd.equals(teacher.getTecPwd())){
			verifyPwd = "验证密码成功！";
		}else{
			verifyPwd = "密码输入错误！";
		}
		return verifyPwd;
	}
	
	/**
	 * 袁云：老师修改密码功能
	 * @param  页面传过来的新密码和确认密码
	 * @return 老师对象
	 */
	@RequestMapping(value="/tecUpdatePwd",method=RequestMethod.POST)
	public ModelAndView tecUpdatePwd(Integer id,String oldPwd,String newPwd,String confirmPwd){
		String update = "";
		ModelAndView mv = new ModelAndView();
		if(verifyPwd(id, oldPwd).equals("验证密码成功！")){
			if(newPwd.equals(confirmPwd)){
				int result = teacherService.tecUpdatePwd(id,newPwd);
				if(result > 0){
					update = "修改密码成功！";
					// 设置跳转的页面
					mv.setViewName("teacher");
				}else{
					update = "修改密码失败";
					mv.setViewName("teacherUpdatePwd");
				}
			}else{
				update = "两次输入的密码不一致，请重新输入！";
				// 设置跳转的页面
				mv.setViewName("teacherUpdatePwd");
			}
		}else{
			update = "密码输入错误,请重新输入！";
			// 设置跳转的页面
			mv.setViewName("teacherUpdatePwd");
		}
		// 向页面返回数据
		mv.addObject("update",update);
		return mv;
	}
}
