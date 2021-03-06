package silence.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import silence.entity.AttendanceRecord;
import silence.entity.Diary;
import silence.entity.Question;
import silence.entity.Students;
import silence.service.StudentService;
import silence.service.TeachersService;

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
	@Resource
	TeachersService teachersService;

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的：stuNo 学号， stuPwd 密码
	 * 
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
	public ModelAndView studentLogin(String stuNo, String stuPwd, HttpSession session, String kaptcha) {
		String info = "";
		ModelAndView mv = new ModelAndView(); // 这里的路径是真实的JSP页面路径;
		Students student = studentService.getStudentByStuNo(stuNo);
		// 通过组件获得验证码
		String kaptchaExpected = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (student != null) {
			if (student.getStuPwd().equals(stuPwd)) {
				if (kaptcha.equalsIgnoreCase(kaptchaExpected)) {
					// 创建一个模型和视图
					mv.setViewName("student");
					session.setAttribute("student", student);
					// 登录成功之后获得学生的总积分数
					Integer integral = studentService.countIntegrals(student.getId());
					session.setAttribute("integral", integral);
					// 登录成功之后收到最新提问推送(最近本周内的)
					List<Question> questions = teachersService.findWeekQuestion();
					if (questions.size() > 0) {
						mv.addObject("weekQuestionNo", questions.size());
						mv.addObject("tag", "stuAttendance/question");
					} else {
						info = "最近没有新提问。";
					}
				} else {
					info = "验证码输入错误！";
					mv.setViewName("studentLogin");
				}
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

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String student() {
		return "student";
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public String question(Model model) {
		List<Question> tenQuestion = teachersService.findTenQuestion();
		model.addAttribute("tenQuestion", tenQuestion);
		return "question";
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的：id修改密码的学生编号，stuOldPwd旧密码
	 * 
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/verifyPwd", method = RequestMethod.POST)
	@ResponseBody // AJAX注解,把info当作字符串返回给页面
	public Map<String, Object> verifyPwd(Integer id, String stuOldPwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		String stuPwd = studentService.getStudentPwd(id);
		if (stuPwd.equals(stuOldPwd)) {
			map.put("success", true);
			map.put("message", "密码输入正确!");
		} else {
			map.put("success", false);
			map.put("message", "密码输入错误！请重新输入");
		}
		return map;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的：id修改密码的学生编号，stuOldPwd旧密码，stuNewPwd新密码，stuRePwd重复密码
	 * 
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/updateStuPwd", method = RequestMethod.POST)
	public ModelAndView updateStuPwd(Integer id, String stuNewPwd, String stuRePwd) {
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
	 * 
	 * @param 页面传过来的：stuId插入到校时间的学生编号
	 * 
	 * @return 把签到是否成功的信息返回给页面
	 */
	@RequestMapping(value = "/insertComeTime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertComeTime(Integer stuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获得系统当前时间
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(d);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String hours = sdf.format(time);
		Date h = null;
		Date rule = null;
		Integer integrals = 0;
		try {
			h = sdf.parse(hours);
			// 学校规定的考勤时间
			rule = sdf.parse("08:30:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查询是否有点击签到按钮获得的当前系统时间对应的考勤记录，如果有说明今天已经签到过，不能再继续签到；如果没有，则可以进行签到
		AttendanceRecord att = studentService.selectStuAttRecord(stuId, date);
		if (att == null) {
			int result = studentService.insertComeTime(stuId, d, time, "N");
			if (result > 0) {
				map.put("success", 0);
				map.put("info", "签到成功！");
				// 签到成功之后系统给学生分配积分：8：30之前不算迟到可以获得+10积分；之后算迟到：迟到一分钟获得-1积分，-10积分封顶
				if (h.getTime() < rule.getTime()) {
					integrals = 10;
				} else {
					if ((h.getTime() - rule.getTime()) > 10) {
						integrals = -10;
					} else {
						integrals = (int) (h.getTime() - rule.getTime()) * (-1);
					}
				}
				Integer re = studentService.insertIntegrals(stuId, 0, integrals);
				if (re > 0) {
					map.put("integral", studentService.countIntegrals(stuId));
				}
			} else {
				map.put("success", 1);
				map.put("info", "签到失败！");
			}
		} else {
			map.put("success", 2);
			map.put("info", "不能重复签到!");
		}
		return map;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的：stuId更新离校时间的学生编号
	 * 
	 * @return 把签到是否成功的信息返回给页面
	 */
	@RequestMapping(value = "/saveBackTime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBackTime(Integer stuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 考勤时间
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String dates = s.format(date);
		// 签退时间
		Timestamp attBackTime = new Timestamp(System.currentTimeMillis());
		// 查询是否有点击签退按钮获得的当前系统时间对应的考勤记录，如果有说明今天已经签到过，只需更新签退时间和考勤状态；
		// 如果没有，说明还没有签到过则需插入签退时间和考勤状态
		AttendanceRecord att = studentService.selectStuAttRecord(stuId, dates);
		if (att == null) {
			// 插入离校时间和考勤状态
			Integer result = studentService.insertBackTime(stuId, date, attBackTime, "N");
			if (result > 0) {
				map.put("success", 0);
				map.put("message", "签退成功！");
			} else {
				map.put("success", 1);
				map.put("message", "签退失败！");
			}
		} else {
			if (att.getAttendanceBackTime() == null) {
				// 更新离校时间和考勤状态
				// 先获得考勤状态
				String attStatus = "";
				Timestamp attComeTime = att.getAttendanceComeTime();
				SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss"); // 格式化时间函数
				try {
					// 签到时间
					String comeTime = simple.format(attComeTime);
					Date comeTimes = simple.parse(comeTime);
					// 签退时间
					String backTime = simple.format(attBackTime);
					Date backTimes = simple.parse(backTime);
					// 学校规定的考勤时间
					Date time1 = simple.parse("08:30:00");
					Date time2 = simple.parse("17:30:00");
					if (comeTimes.getTime() < time1.getTime() && backTimes.getTime() > time2.getTime()) {
						attStatus = "Y";
					} else {
						attStatus = "N";
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 更新离校时间和考勤状态
				Integer results = studentService.updateBackTime(stuId, dates, attBackTime, attStatus);
				if (results > 0) {
					map.put("success", 0);
					map.put("message", "签退成功！");
				} else {
					map.put("success", 1);
					map.put("message", "签退失败！");
				}
			} else {
				map.put("success", 2);
				map.put("message", "不能重复签退！");
			}
		}
		return map;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的stuId学生编号,classId班级编号，curPage当前页码。
	 * 
	 * @return 学生编号对应的学生考勤记录
	 */
	@RequestMapping(value = "/selectStuAttendanceRecord", method = RequestMethod.GET)
	public ModelAndView selectStuAttendanceRecord(Integer stuId, Integer classId, Integer curPage) {
		if (curPage == null) {
			curPage = 1; // 当页面传过来的参数为null时，纠正为第一页，避免出错
		}
		if (curPage < 1) {
			curPage = 1; // 下限判断，当小于第一页时，纠正为第一页
		}
		int maxRecord = studentService.getMaxStuAttendanceReocrd(stuId, classId);
		int maxPage = (maxRecord + 4) / 5; // 每页显示五条信息，总共有几页
		if (curPage > maxPage) {
			curPage = maxPage; // 上限判断，当大于最后一页时，纠正为最后一页
		}
		int pageIndex = (curPage - 1) * 5;
		List<AttendanceRecord> record = studentService.selectStuAttendanceReocrd(stuId, classId, pageIndex);
		String info = "";
		// 创建一个模型和视图
		ModelAndView mv = new ModelAndView("studentlookattendancerecord");
		// 向页面返回数据
		mv.addObject("attendanceRecord", record);
		mv.addObject("curPage", curPage);
		mv.addObject("maxPage", maxPage);
		mv.addObject("maxRecord", maxRecord);
		mv.addObject("info", info);
		return mv;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的stuId学生编号,classId班级编号，choice当前选择按什么查询。
	 * 
	 * @return 学生编号对应的学生考勤记录
	 */
	@RequestMapping(value = "/selectStuAttendanceRecordByTime", method = RequestMethod.POST)
	public ModelAndView selectStuAttendanceRecordByTime(Integer stuId, Integer classId, Integer choice,
			Integer curPage) {
		if (curPage == null) {
			curPage = 1; // 当页面传过来的参数为null时，纠正为第一页，避免出错
		}
		if (curPage < 1) {
			curPage = 1; // 下限判断，当小于第一页时，纠正为第一页
		}
		int maxRecord = studentService.getMaxAttendanceRecordByTime(stuId, classId, choice);
		int maxPage = (maxRecord + 4) / 5; // 每页显示五条信息，总共有几页
		if (curPage > maxPage) {
			curPage = maxPage; // 上限判断，当大于最后一页时，纠正为最后一页
		}
		int pageIndex = (curPage - 1) * 5;
		List<AttendanceRecord> record = null;
		String info = "";
		if (pageIndex < 0) {
			info = "此时段没有考勤记录。";
		} else {
			record = studentService.selectStuAttendanceRecordByTime(stuId, classId, choice, pageIndex);
		}
		// 创建一个模型和视图
		ModelAndView mv = new ModelAndView();
		// 设置跳转页面
		mv.setViewName("studentlookattendancerecord");
		// 向页面返回数据
		mv.addObject("attendanceRecord", record);
		mv.addObject("curPage", curPage);
		mv.addObject("maxPage", maxPage);
		mv.addObject("maxRecord", maxRecord);
		mv.addObject("info", info);
		if (choice != 0) {
			mv.addObject("choice", choice);
		}
		return mv;
	}

	@RequestMapping(value = "/selectStuAttendanceRecordByTimes", method = RequestMethod.GET)
	public ModelAndView selectStuAttendanceRecordByTimes(Integer stuId, Integer classId, Integer choice,
			Integer curPage) {
		ModelAndView mv = selectStuAttendanceRecordByTime(stuId, classId, choice, curPage);
		return mv;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的stuId学生编号,
	 * 
	 * @return 学生编号对应的学生考勤率
	 */
	@RequestMapping(value = "/selectStuAttendanceRate", method = RequestMethod.POST)
	public ModelAndView selectStuAttendanceRate(Integer stuId, Integer classId, String choiceTime1,
			String choiceTime2) {
		String attRate = ""; // 考勤率
		// 查询某个学生某个时间内全勤的记录数
		Integer count = studentService.countStuAttendanceRate(stuId, choiceTime1, choiceTime2);
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date time1 = sim.parse(choiceTime1);
			long times1 = time1.getTime();
			Date time2 = sim.parse(choiceTime2);
			long times2 = time2.getTime();
			long days = (times2 - times1) / (1000 * 60 * 60 * 24);
			if (count > 0) {
				// double保留两位小数(四舍五入)
				double f = (double) count / days;
				BigDecimal b = new BigDecimal(f);
				double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				attRate = "" + f1;
			} else {
				attRate = "没有全勤记录，故没有出勤率。";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 查询某个学生的考勤记录
		List<AttendanceRecord> record = studentService.selectStuAttendanceRecordByTime(stuId, classId, null, null);
		// 创建一个模型和视图
		ModelAndView mv = new ModelAndView();
		// 设置跳转页面
		mv.setViewName("studentlookattendancerecord");
		// 向页面返回数据
		mv.addObject("attStu", record);
		mv.addObject("attRate", attRate);
		mv.addObject("choiceTime1", choiceTime1);
		mv.addObject("choiceTime2", choiceTime2);
		return mv;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的stuId学生编号，classId班级编号，curPage页码
	 * 
	 * @return 学生编号对应的学生日志
	 */
	@RequestMapping(value = "/selectStuDiary", method = RequestMethod.GET)
	public ModelAndView selectStuDiary(Integer stuId, Integer classId, Integer curPage) {
		String info = "";
		if (curPage == null) {
			curPage = 1; // 当页面传过来的参数为null时，纠正为第一页，避免出错
		}
		if (curPage < 1) {
			curPage = 1; // 下限判断，当小于第一页时，纠正为第一页
		}
		int maxRecord = studentService.getMaxDiary(stuId, classId);
		int maxPage = (maxRecord + 4) / 5; // 每页显示五条信息，总共有几页
		if (curPage > maxPage) {
			curPage = maxPage; // 上限判断，当大于最后一页时，纠正为最后一页
		}
		int pageIndex = (curPage - 1) * 5;
		List<Diary> diarys = studentService.selectDiary(stuId, classId, pageIndex);
		// 获得系统当前时间
		Date date = new Date();
		// 判断是否是周末
		Boolean flag = false;
		Calendar cal = Calendar.getInstance(); // 实例化
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			flag = true;
		}
		// 创建一个模型和视图
		ModelAndView mv = new ModelAndView();
		// 设置跳转页面
		mv.setViewName("diary");
		// 向页面返回数据
		mv.addObject("diarys", diarys);
		mv.addObject("curPage", curPage);
		mv.addObject("maxPage", maxPage);
		mv.addObject("maxRecord", maxRecord);
		mv.addObject("info", info);
		mv.addObject("flag", flag);
		return mv;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的stuId学生编号，classId班级编号，curPage页码
	 * 
	 * @return 学生编号和对应时间所对应的学生日志
	 */
	@RequestMapping(value = "/selectStuDiaryByTime", method = RequestMethod.GET)
	public ModelAndView selectStuDiaryByTime(Integer stuId, Integer classId, Integer choice, Integer curPage,
			HttpSession session) {
		if (curPage == null) {
			curPage = 1; // 当页面传过来的参数为null时，纠正为第一页，避免出错
		}
		if (curPage < 1) {
			curPage = 1; // 下限判断，当小于第一页时，纠正为第一页
		}
		int maxRecord = studentService.getMaxDiaryByTime(stuId, classId, choice);
		int maxPage = (maxRecord + 4) / 5; // 每页显示五条信息，总共有几页
		if (curPage > maxPage) {
			curPage = maxPage; // 上限判断，当大于最后一页时，纠正为最后一页
		}
		int pageIndex = (curPage - 1) * 5;
		List<Diary> diarys = null;
		String info = "";
		if (pageIndex < 0) {
			info = "此时间段没有日志信息。";
		} else {
			diarys = studentService.selectDiaryByTime(stuId, classId, choice, pageIndex);
		}
		// 创建一个模型和视图
		ModelAndView mv = new ModelAndView();
		// 设置跳转页面
		mv.setViewName("diary");
		// 向页面返回数据
		mv.addObject("diarys", diarys);
		mv.addObject("curPage", curPage);
		mv.addObject("maxPage", maxPage);
		mv.addObject("maxRecord", maxRecord);
		mv.addObject("info", info);
		if (choice != 0) {
			mv.addObject("choice", choice);
		}
		return mv;
	}

	/*
	 * @author 连慧
	 * 
	 * @param
	 * 页面传过来的stuId学生编号diaryCommitTime日志提交时间，stuNo学号，stuName学生姓名，className班级名称，
	 * curPage页码
	 * 
	 * @return 学生编号和日志日期对应的学生日志
	 */
	@RequestMapping(value = "/selectStuDiaryDetail", method = RequestMethod.GET)
	public ModelAndView selectStuDiaryDetail(Integer stuId, Timestamp diaryCommitTime, String stuNo, String stuName,
			String className) {
		String info = "";
		String infos = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(diaryCommitTime);
		Diary diary = studentService.selectDiaryByDiaryDate(stuId, date);
		Question question = null;
		if (diary == null) {
			infos = "没有日志内容！";
		} else {
			question = studentService.selectQuestionByDate(stuId, diary.getDiaryCommitTime());
			if (question == null) {
				info = "没有填写提问！";
			}
		}
		// 创建一个模型和视图
		ModelAndView mv = new ModelAndView("showDiaryDetail");
		// 向页面返回数据
		mv.addObject("diary", diary);
		mv.addObject("question", question);
		mv.addObject("infos", infos);
		mv.addObject("info", info);
		mv.addObject("stuNo", stuNo);
		mv.addObject("stuName", stuName);
		mv.addObject("className", className);
		return mv;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的stuId学生编号，diaryDate日志时间，diaryContent日志内容
	 * 
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/updateDiary", method = RequestMethod.GET)
	public ModelAndView updateDiary(Integer stuId, Timestamp diaryCommitTime, String stuNo, String stuName,
			String className) {
		String info = "";
		// 创建一个模型和视图
		ModelAndView mv = new ModelAndView("updateDiaryDetail");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(diaryCommitTime);
		Diary diary = studentService.selectDiaryByDiaryDate(stuId, date);
		// 向页面返回数据
		mv.addObject("diary", diary);
		mv.addObject("info", info);
		mv.addObject("stuNo", stuNo);
		mv.addObject("stuName", stuName);
		mv.addObject("className", className);
		return mv;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的stuId学生编号，diaryDate日志时间，diaryContent日志内容
	 * 
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@ResponseBody
	@RequestMapping(value = "/updateDiaryDetail", method = RequestMethod.POST)
	public Map<String, Object> updateDiaryDetail(Integer stuId, Timestamp diaryCommitTime, String diaryContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(diaryCommitTime);
		Integer result = studentService.updateDiary(stuId, date, diaryContent);
		if (result > 0) {
			map.put("success", true);
			map.put("info", "修改成功！");
		} else {
			map.put("success", false);
			map.put("info", "修改失败！");
		}
		return map;
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的：stuId为增加工作日志的学生编号
	 * 
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/addDiary", method = RequestMethod.GET)
	public String addDiary() {
		return "addDiary";
	}

	/*
	 * @author 连慧
	 * 
	 * @param 页面传过来的：stuId为增加工作日志的学生编号
	 * 
	 * @return ModelAndView对象，用来设置跳转页面和向页面传递参数（把参数放到了request对象）
	 */
	@RequestMapping(value = "/addDiary", method = RequestMethod.POST)
	public ModelAndView addDiary(Integer stuId, String diaryContent, String questionContent) {
		String message = "";
		// 获得系统当前时间
		Date date = new Date();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
		ModelAndView mv = new ModelAndView("addDiary");
		String commitTime = sdf.format(time);
		String dates = sdfs.format(date);
		Diary diary = studentService.selectDiaryByDiaryDate(stuId, dates);
		if (diary == null) { // 当天没有写过工作日志才能插入日志
			if (diaryContent != "") { // 工作日志内容不为空才能保存插入工作日志

				// 插入日志
				Integer result = studentService.insertDiary(stuId, diaryContent, commitTime, dates);
				if (questionContent != "") {
					// 插入问题
					Integer results = studentService.insertQuestion(stuId, questionContent, commitTime);
				}
				if (result > 0) {
					mv.setViewName("addDiary");
					message = "保存成功！";
					// 提交一篇工作日志加5积分
					studentService.insertIntegrals(stuId, 0, 5);
				} else {
					mv.setViewName("addDiary");
					message = "保存失败！";
				}
			} else {
				mv.setViewName("addDiary");
				message = "尚未填写日志,请填写！";
			}
		} else {
			message = "今天已经写过工作日志,保存失败！";
		}
		mv.addObject("message", message);
		return mv;
	}
}
