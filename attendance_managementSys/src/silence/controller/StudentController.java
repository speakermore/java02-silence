package silence.controller;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import silence.entity.Students;
import silence.service.StudentService;

/**
 * @author 作者：连慧
 * @date 创作时间：2017年7月17日 上午11:52:41 控制器：学生类控制器
 */

@Controller
@RequestMapping("/stuAttendance") // 在此控制器中的所有请求映射均要添加stuAttendance前缀
public class StudentController {
	// @Resource为自动注入的注解，表明此类要通过Spring容器完成注入
	@Resource
	StudentService studentService;

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的：stuNo 学号， stuPwd 密码
	 * 
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
	public ModelAndView studentLogin(String stuNo, String stuPwd, HttpSession session) {
		String info = "";
		ModelAndView mv = new ModelAndView(); // 这里的路径是真实的JSP页面路径;
		Students student = studentService.getStudentByStuNo(stuNo);
		if (student != null) {
			if (student.getStuPwd().equals(stuPwd)) {
				// 创建一个模型和视图
				mv.setViewName("student");
				session.setAttribute("student", student);
			} else {
				info = "用户名或者密码不存在！";
				mv.setViewName("studentLogin");
			}
		} else {
			info = "用户名或者密码不存在！";
			mv.setViewName("studentLogin");
		}
		// 向页面返回数据
		mv.addObject("info", info);
		return mv;
	}

	/**
	 * @author 连慧 get请求的方法：地址栏访问页面的时候都是get请求访问， 而上面定义的业务是通过POST方式请求访问的，
	 *         所以需要定义一个GET请求访问的逻辑路径才能访问到页面
	 * @return
	 */
	@RequestMapping(value = "/studentLogin", method = RequestMethod.GET)
	public String login() {
		return "studentLogin";
	}
	
	/*
	 * @author 连慧
	 * @param 页面传过来的：id修改密码的学生编号，stuOldPwd旧密码
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value="/verifyPwd",method=RequestMethod.POST)
	@ResponseBody       //AJAX注解,把info当作字符串返回给页面
	public String verifyPwd(Integer id,String stuOldPwd) {
		String info="";
		String stuPwd=studentService.getStudentPwd(id);
		if (stuPwd.equals(stuOldPwd)) {
			info="密码输入正确!";
		}else{
			info="密码输入错误！请重新输入";
		}
		return info;
	}

	/*
	 * @author 连慧
	 * @param 页面传过来的：id修改密码的学生编号，stuOldPwd旧密码，stuNewPwd新密码，stuRePwd重复密码
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/updateStuPwd", method = RequestMethod.POST)
	public ModelAndView updateStuPwd(Integer id,String stuNewPwd, String stuRePwd) {
		String info = "";
		ModelAndView mv = new ModelAndView();
			if (stuNewPwd.equals(stuRePwd)) {
				int result = studentService.updateStuPwd(stuNewPwd, id);
				if (result > 0) {
					info = "修改密码成功！";
					mv.setViewName("student");
				} else {
					info = "修改密码失败！";
					mv.setViewName("studentUpdatePwd");
				}
			} else {
				info = "两次输入密码不一致！";
				mv.setViewName("studentUpdatePwd");
			}
		mv.addObject("message", info);
		return mv;
	}

	@RequestMapping(value = "/updateStuPwd", method = RequestMethod.GET)
	public String updateStuPwd() {
		return "studentUpdatePwd";
	}
	
	/*
	 * @author 连慧
	 * @param 页面传过来的：stuId插入到校时间的学生编号，stuOldPwd旧密码，stuNewPwd新密码，stuRePwd重复密码
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/updateStuPwd", method = RequestMethod.GET)
	public String insertComeTime(Integer stuId,Timestamp comeTime) {
		return "签到成功!";
	}
}
