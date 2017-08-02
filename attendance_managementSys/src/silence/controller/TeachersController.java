package silence.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import silence.entity.AttendanceRecord;
import silence.entity.Diary;
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
	 * @param  页面传过来的老师id和老师密码
	 * @return 老师对象
	 */
	@RequestMapping(value="/verifyPwd",method=RequestMethod.POST)
	@ResponseBody //ajax注解，把verifyPwd当做字符串返回给页面
	public String verifyPwd(Integer id,String oldPwd){
		String verifyPwdInfo = "";
		Teacher teacher = teacherService.getTecById(id);
		if(oldPwd.equals(teacher.getTecPwd())){
			verifyPwdInfo = "验证密码成功！";
		}else{
			verifyPwdInfo = "密码输入错误！";
		}
		return verifyPwdInfo;
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
	
	/**
	 * 袁云：用于点击查看学生考勤记录按钮时跳转到查看学生考勤记录页面
	 * @param  页面传过来的当前页
	 * @return 考勤记录对象
	 */
	@RequestMapping(value="/jumpLookAttendanceRecord",method=RequestMethod.GET)
	public ModelAndView jumpLookAttendanceRecord(Integer curPage ){
		ModelAndView mv = new ModelAndView();
		if(curPage==null){
			mv = jumpLookAttendanceRecord(1);
			return mv;
		}else{
			if(curPage < 1){
				curPage=1;//下限判断，当小于第一页是，纠正为第一页
			}
			Integer maxRecord = teacherService.getMaxRecord();
			Integer maxPage = (maxRecord+4)/5;
			if(curPage > maxPage){
				curPage = maxPage; // 上限判断，当大于最后一页时，纠正为最后一页
			}
			Integer pageIndex = (curPage-1)*5; 
			List<AttendanceRecord> attendanceRecords = teacherService.findAll(pageIndex);
			mv = new ModelAndView("teacherlookattendancerecord");
			mv.addObject("attendanceRecords",attendanceRecords);
			mv.addObject("curPage",curPage);
			mv.addObject("maxPage",maxPage);
			mv.addObject("maxRecord",maxRecord);
			return mv;
		}
	}
	
	/**
	 * 袁云：验证该班级是否有该学号的学生的功能
	 * @param  页面传过来的班级id和学生学号
	 * @return map集合
	 */
	@RequestMapping(value="/verifyStuExist",method=RequestMethod.POST)
	@ResponseBody //ajax注解，把verifyStuExist当做字符串返回给页面
	public Map<String,Object> verifyStuExist(Integer id,String stuNo){
		Map<String, Object> verifyStuExistInfo = new HashMap<String,Object>();
		Students students = teacherService.verifyStuExist(id,stuNo);
		if(students!=null){
			verifyStuExistInfo.put("succeed",true);
			verifyStuExistInfo.put("message", "该班存在该学号的学生！");
		}else{
			verifyStuExistInfo.put("succeed",false);
			verifyStuExistInfo.put("message", "该班不存在该学号的学生！");
		}
		return verifyStuExistInfo;
	}
	
	/**
	 * 袁云：验证该时间段是否有考勤记录
	 * @param  页面传过来的时间段
	 * @return map集合
	 */
	@RequestMapping(value="/verifyAttendanceExistInfo",method=RequestMethod.POST)
	@ResponseBody //ajax注解，把verifyStuExist当做字符串返回给页面
	public Map<String,Object> verifyAttendanceExistInfo(Timestamp attendanceTime1,Timestamp attendanceTime2){
		Date date1 = new Date(attendanceTime1.getTime());
		Date date2 = new Date(attendanceTime2.getTime());
		Map<String, Object> verifyAttendanceExistInfo = new HashMap<String,Object>();
		ArrayList<AttendanceRecord> attendanceRecords = teacherService.verifyStuExist2(date1,date2);
		if(attendanceRecords.size()>0){
			verifyAttendanceExistInfo.put("succeed",true);
			verifyAttendanceExistInfo.put("message", "该时间段有考勤记录！");
		}else{
			verifyAttendanceExistInfo.put("succeed",false);
			verifyAttendanceExistInfo.put("message", "该时间段没有考勤记录！");
		}
		return verifyAttendanceExistInfo;
	}
	
	/**
	 * 袁云：老师查看考勤记录
	 * @param  页面传过来的考勤时间、班级、姓名和学号，可以为空
	 * @return 考勤记录集合
	 */
	@RequestMapping(value="/lookAttendanceRecord",method=RequestMethod.POST)
	public ModelAndView lookAttendanceRecord(Timestamp attendanceTime1,Timestamp attendanceTime2,Integer stuClass,String stuNo,String stuName,Integer curPage){
		ModelAndView mv = new ModelAndView();
		// 当什么查询条件都没有选择时的处理
		if(attendanceTime1==null&&attendanceTime2==null&&stuClass==0&&stuNo==""&&stuName==""){
			mv = jumpLookAttendanceRecord(1);
			mv.addObject("attendanceRecordInfo", "请选择查询条件！");
			return mv;
		}else{
			Date date1=null;
			Date date2=null;
			if(attendanceTime1!=null&&attendanceTime2!=null){
				date1 = new Date(attendanceTime1.getTime());
				date2 = new Date(attendanceTime2.getTime());
			}
			Integer maxRecord = teacherService.getMaxRecordByCondition(date1, date2, stuClass, stuNo, stuName);
			// 当没有符合查询条件的数据时的处理
			if(maxRecord==0){
				mv = jumpLookAttendanceRecord(1);
				mv.addObject("attendanceRecordInfo","没有符合条件的考勤记录！");
				return mv;
			}else{
				if(curPage < 1){
					curPage=1;//下限判断，当小于第一页是，纠正为第一页
				}
				Integer maxPage = (maxRecord+4)/5;
				if(curPage > maxPage){
					curPage = maxPage; // 上限判断，当大于最后一页时，纠正为最后一页
				}
				Integer pageIndex = (curPage-1)*5; 
				List<AttendanceRecord> attendanceRecords = teacherService.lookAttendanceRecord(date1, date2, stuClass, stuNo, stuName, pageIndex);
				mv.setViewName("teacherlookattendancerecord");
				mv.addObject("attendanceRecords",attendanceRecords);
				mv.addObject("curPage",curPage);
				mv.addObject("maxPage",maxPage);
				mv.addObject("maxRecord",maxRecord);
				String time1 = "";
				String time2 = "";
				// 当页面还需要用到传过来的值时，则将传过来的值在放到mv里传回给页面
				if(attendanceTime1==null){
					time1 = "";
				}else{
					time1 = new SimpleDateFormat("yyyy-MM-dd").format(attendanceTime1);
				}
				if(attendanceTime2==null){
					time2 = "";
				}else{
					time2 = new SimpleDateFormat("yyyy-MM-dd").format(attendanceTime2);
				}
				mv.addObject("attendanceTime1",time1);
				mv.addObject("attendanceTime2",time2);
				mv.addObject("stuClass",stuClass);
				mv.addObject("stuNo",stuNo);
				mv.addObject("stuName",stuName);
				return mv;
			}
		}
	}
	/**
	 * 袁云：按条件查询查出来的数据当点击上一页、下一页等时，跳转的方法且需要将查询条件通过参数的形式传过来
	 * @param  页面传过来的考勤时间、班级、姓名和学号
	 * @return 考勤记录集合
	 */
	@RequestMapping(value="/lookAttendanceRecords",method=RequestMethod.GET)
	public ModelAndView lookAttendanceRecords(Timestamp attendanceTime1,Timestamp attendanceTime2,Integer stuClass,String stuNo,String stuName,Integer curPage){
		ModelAndView mv = new ModelAndView();
		if(curPage==null){
			mv = lookAttendanceRecord(attendanceTime1, attendanceTime2, stuClass, stuNo, stuName,1);
			return mv;
		}else{
			mv = lookAttendanceRecord(attendanceTime1, attendanceTime2, stuClass, stuNo, stuName, curPage);
			return mv;
		}
	}
	
	/**
	 * 袁云：老师查看个人出勤率
	 * @param  页面传过来的时间段，班级，学号，姓名
	 * @return 个人出勤率
	 */
	@RequestMapping(value="/lookPersonalAttendanceRate",method=RequestMethod.POST)
	public ModelAndView lookPersonalAttendanceRate(Timestamp attendanceTime1,Timestamp attendanceTime2,Integer stuClass,String stuNo,String stuName){
		ModelAndView mv = new ModelAndView("teacherlookattendancerecord");
		if(attendanceTime1==null&&attendanceTime2==null&&stuClass==0&&stuNo==""&&stuName==""){
			mv.addObject("personalVerify", "请选择查询条件！");
		}else{
			if(attendanceTime1==null||attendanceTime2==null){
				mv.addObject("personalVerify", "考勤时间不能为空！");
			}else{
				if(stuClass==0&&stuNo==""&&stuName==""){
					mv.addObject("personalVerify", "学号和姓名必须填一个");
				}else{
					Date date1 = new Date(attendanceTime1.getTime());
					Date date2 = new Date(attendanceTime2.getTime());
					Integer maxRecord = teacherService.getMaxRecordByCondition(date1 , date2, stuClass, stuNo, stuName);
					if(maxRecord==0){
						mv.addObject("personalVerify", "没有符合条件的记录！");
					}else{
						// 考勤状态为y的数量
						Integer yCount = teacherService.getStuStatusIsYNo(date1, date2,stuClass, stuNo, stuName);
						long second = date2.getTime()-date1.getTime();
						// 期间段总天数
						long days = second/(1000 * 60 * 60 * 24);
						double attendanceRates = (double)yCount/days;
						BigDecimal bg = new BigDecimal(attendanceRates);  
						// 出勤率，保留两位小数
						double personalAttendanceRate = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
						List<AttendanceRecord> personalAttendanceRecords = teacherService.lookAttendanceRecord(date1, date2, stuClass, stuNo, stuName, 0);
						mv.addObject("personalAttendanceRate",personalAttendanceRate);
						mv.addObject("personalAttendanceRecords", personalAttendanceRecords);
						String time1 = "";
						String time2 = "";
						// 当页面还需要用到传过来的值时，则将传过来的值在放到mv里传回给页面
						time1 = new SimpleDateFormat("yyyy-MM-dd").format(attendanceTime1);
						time2 = new SimpleDateFormat("yyyy-MM-dd").format(attendanceTime2);
						mv.addObject("attendanceTime3",time1);
						mv.addObject("attendanceTime4",time2);
						mv.addObject("stuClass2",stuClass);
						mv.addObject("stuNo2",stuNo);
						mv.addObject("stuName2",stuName);
						
					}
				}
			}
		}
		return mv;
	}
	
	/**
	 * 袁云：老师查看班级出勤率
	 * @param  页面传过来的时间段，班级
	 * @return 班级出勤率
	 */
	@RequestMapping(value="/lookClassAttendanceRate",method=RequestMethod.POST)
	public ModelAndView lookClassAttendanceRate(Timestamp attendanceTime1,Timestamp attendanceTime2,Integer stuClass){
		ModelAndView mv = new ModelAndView("teacherlookattendancerecord");
		if(attendanceTime1==null&&attendanceTime2==null&&stuClass==0){
			mv.addObject("classVerify", "请选择查询条件！");
		}else{
			if(attendanceTime1==null||attendanceTime2==null){
				mv.addObject("classVerify", "考勤时间不能为空！");
			}else{
				if(stuClass==0){
					mv.addObject("classVerify", "必须选择班级！");
				}else{
					Date date1 = new Date(attendanceTime1.getTime());
					Date date2 = new Date(attendanceTime2.getTime());
					Integer maxRecord = teacherService.getMaxRecordByClass(date1, date2, stuClass);
					if(maxRecord==0){
						mv.addObject("classVerify", "没有符合条件的记录！");
					}else{
						// 考勤状态为y的数量
						Integer yCount = teacherService.getClassStatusIsYNo(date1, date2, stuClass);
						Integer classStuCount = teacherService.getClassStuCount(stuClass);
						long second = date2.getTime()-date1.getTime();
						// 期间段总天数
						long days = second/(1000 * 60 * 60 * 24);
						double attendanceRates = (double)yCount/(days*classStuCount);
						BigDecimal bg = new BigDecimal(attendanceRates);  
						// 出勤率，保留两位小数
						double classAttendanceRate = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
						mv.addObject("classAttendanceRate", classAttendanceRate);
						String time1 = "";
						String time2 = "";
						// 当页面还需要用到传过来的值时，则将传过来的值在放到mv里传回给页面
						time1 = new SimpleDateFormat("yyyy-MM-dd").format(attendanceTime1);
						time2 = new SimpleDateFormat("yyyy-MM-dd").format(attendanceTime2);
						mv.addObject("attendanceTime5",time1);
						mv.addObject("attendanceTime6",time2);
						mv.addObject("stuClass1",stuClass);
					}
				}
			}
		}
		return mv;
	}
	
	/**
	 * 袁云：用于点击查看学生工作日志按钮时跳转到查看学生工作日志页面
	 * @param  页面传过来的当前页
	 * @return 工作日志对象
	 */
	@RequestMapping(value="/jumpLookDiary",method=RequestMethod.GET)
	public ModelAndView jumpLookDiary(Integer curPage ){
		ModelAndView mv = new ModelAndView();
		if(curPage==null){
			mv = jumpLookDiary(1);
			return mv;
		}else{
			if(curPage < 1){
				curPage=1;//下限判断，当小于第一页是，纠正为第一页
			}
			Integer maxRecord = teacherService.getMaxDiaryRecord();
			Integer maxPage = (maxRecord+4)/5;
			if(curPage > maxPage){
				curPage = maxPage; // 上限判断，当大于最后一页时，纠正为最后一页
			}
			Integer pageIndex = (curPage-1)*5; 
			List<Diary> diarys = teacherService.findAllDiary(pageIndex);
			mv.setViewName("teacherLookDiary");
			mv.addObject("diarys",diarys);
			mv.addObject("curPage",curPage);
			mv.addObject("maxPage",maxPage);
			mv.addObject("maxRecord",maxRecord);
			return mv;
		}
	}
	
	/**
	 * 袁云：老师查看工作日志
	 * @param  页面传过来的提交时间、班级、姓名和学号，可以为空
	 * @return 工作日志集合
	 */
	@RequestMapping(value="/lookDiary",method=RequestMethod.POST)
	public ModelAndView lookDiary(Timestamp diaryCommitTime1,Timestamp diaryCommitTime2,Integer stuClass,String stuNo,String stuName,Integer curPage){
		ModelAndView mv = new ModelAndView();
		// 当什么查询条件都没有选择时的处理
		if(diaryCommitTime1==null&&diaryCommitTime1==null&&stuClass==0&&stuNo==""&&stuName==""){
			mv = jumpLookDiary(1);
			mv.addObject("diaryInfo", "请选择查询条件！");
			return mv;
		}else{
			Integer maxRecord = teacherService.getDiaryMaxByCondition(diaryCommitTime1, diaryCommitTime2, stuClass, stuNo, stuName);
			// 当没有符合查询条件的数据时的处理
			if(maxRecord==0){
				mv = jumpLookDiary(1);
				mv.addObject("diaryInfo","没有符合条件的工作日志！");
				return mv;
			}else{
				if(curPage < 1){
					curPage=1;//下限判断，当小于第一页是，纠正为第一页
				}
				Integer maxPage = (maxRecord+4)/5;
				if(curPage > maxPage){
					curPage = maxPage; // 上限判断，当大于最后一页时，纠正为最后一页
				}
				Integer pageIndex = (curPage-1)*5; 
				List<Diary> diarys = teacherService.lookDiary(diaryCommitTime1, diaryCommitTime2, stuClass, stuNo, stuName, pageIndex);
				mv.setViewName("teacherLookDiary");
				mv.addObject("diarys",diarys);
				mv.addObject("curPage",curPage);
				mv.addObject("maxPage",maxPage);
				mv.addObject("maxRecord",maxRecord);
				String time1 = "";
				String time2 = "";
				// 当页面还需要用到传过来的值时，则将传过来的值在放到mv里传回给页面
				if(diaryCommitTime1==null){
					time1 = "";
				}else{
					time1 = new SimpleDateFormat("yyyy-MM-dd").format(diaryCommitTime1);
				}
				if(diaryCommitTime2==null){
					time2 = "";
				}else{
					time2 = new SimpleDateFormat("yyyy-MM-dd").format(diaryCommitTime2);
				}
				mv.addObject("diaryCommitTime1",time1);
				mv.addObject("diaryCommitTime2",time2);
				mv.addObject("stuClass",stuClass);
				mv.addObject("stuNo",stuNo);
				mv.addObject("stuName",stuName);
				return mv;
			}
		}
	}
	
	/**
	 * 袁云：验证该时间段是否有工作日志
	 * @param  页面传过来的时间段
	 * @return map集合
	 */
	@RequestMapping(value="/verifyDiaryExist",method=RequestMethod.POST)
	@ResponseBody //ajax注解，把verifyStuExist当做字符串返回给页面
	public Map<String,Object> verifyDiaryExist(Timestamp diaryCommitTime1,Timestamp diaryCommitTime2){
		Map<String, Object> verifyDiaryExistInfo = new HashMap<String,Object>();
		ArrayList<Diary> diarys = teacherService.verifyDiaryExists(diaryCommitTime1,diaryCommitTime2);
		if(diarys.size()>0){
			verifyDiaryExistInfo.put("succeed",true);
			verifyDiaryExistInfo.put("message", "该时间段有工作日志！");
		}else{
			verifyDiaryExistInfo.put("succeed",false);
			verifyDiaryExistInfo.put("message", "该时间段没有工作日志！");
		}
		return verifyDiaryExistInfo;
	}
	
	/**
	 * 袁云：按条件查询查出来的数据当点击上一页、下一页等时，跳转的方法且需要将查询条件通过参数的形式传过来
	 * @param  页面传过来的考勤时间、班级、姓名和学号
	 * @return 考勤记录集合
	 */
	@RequestMapping(value="/lookDiarys",method=RequestMethod.GET)
	public ModelAndView lookDiarys(Timestamp diaryCommitTime1,Timestamp diaryCommitTime2,Integer stuClass,String stuNo,String stuName,Integer curPage){
		ModelAndView mv = new ModelAndView();
		if(curPage==null){
			mv = lookDiary(diaryCommitTime1, diaryCommitTime2, stuClass, stuNo, stuName,1);
			return mv;
		}else{
			mv = lookDiary(diaryCommitTime1, diaryCommitTime2, stuClass, stuNo, stuName, curPage);
			return mv;
		}
	}
	
	/**
	 * 袁云：查看工作日志的详细内容
	 * @param  页面传过来的提交时间、班级、姓名和学号
	 * @return 某一条工作日志
	 */
	@RequestMapping(value="/lookDiaryDetail",method=RequestMethod.GET)
	public ModelAndView lookDiaryDetail(Timestamp diaryCommitTime,String className,String stuNo,String stuName,String diaryContent){
		ModelAndView mv = new ModelAndView("teacherLookDiaryDetail");
			mv.addObject("diaryCommitTime", diaryCommitTime);
			mv.addObject("stuClass", className);
			mv.addObject("stuNo", stuNo);
			mv.addObject("stuName", stuName);
			mv.addObject("diaryContent", diaryContent);
			return mv;
	}
}
