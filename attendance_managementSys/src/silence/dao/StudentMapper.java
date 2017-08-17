package silence.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

import silence.entity.AttendanceRecord;
import silence.entity.Diary;
import silence.entity.Question;
import silence.entity.Students;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月17日 上午9:53:47
* Dao层接口：学生接口
*/
public interface StudentMapper {
	/**
	 * @author 连慧
	 * 查询所有学生，用于JSP页面上进行学生信息的显示，进行页面学生信息的验证
	 * @return 符合条件的学生实体类对象（返回值的意义）
	 */
	public Students getStudentByStuNo(@Param("stuNumber")String stuNumber);
	
	/**
	 * @author 连慧
	 * 查询id对应的学生的密码
	 * @return 符合条件的学生密码（返回值的意义）
	 */
	public String getStudentPwd(@Param("id")Integer id);
	
	/**
	 * @author 连慧
	 * 修改学生密码
	 * @return >0表示修改成功，否则修改失败
	 */
	public Integer updateStuPwd(@Param("stuPwd")String stuPwd,@Param("id")Integer id);
	
	/**
	 * @author 连慧
	 * @param stuId学生编号，comeTime学生到校时间,attDate学生签到时间,attStatus考勤状态
	 * @return >0表示插入到校时间和考勤状态成功，否则失败
	 */
	public Integer insertComeTime(@Param("stuId")Integer stuId,@Param("attDate")Date attDate,@Param("comeTime")Timestamp comeTime,@Param("attStatus")String attStatus);
	/**
	 * @author 连慧
	 * @param stuId学生编号,attendanceDate学生签到时间
	 * @return 符合条件的考勤记录
	 */
	public AttendanceRecord selectStuAttRecord(@Param("stuId")Integer stuId,@Param("attendanceDate")String attendanceDate);
	/**
	 * @author 连慧
	 * @param stuId学生编号，backTime学生签退时间,attDate学生考勤时间,attStatus考勤状态
	 * @return >0表示插入签退时间和考勤状态成功，否则失败
	 */
	public Integer insertBackTime(@Param("stuId")Integer stuId,@Param("attDate")Date attDate,@Param("backTime")Timestamp backTime,@Param("attStatus")String attStatus);
	/**
	 * @author 连慧
	 * @param stuId学生编号，backTime学生签退时间,attDate学生考勤时间,attStatus考勤状态
	 * @return >0表示更新签退时间和考勤状态成功，否则失败
	 */
	public Integer updateBackTime(@Param("stuId")Integer stuId,@Param("attDate")String attDate,@Param("backTime")Timestamp backTime,@Param("attStatus")String attStatus);
	
	/**
	 * @author 连慧
	 * 查询学生考勤记录
	 * @param id学生编号，classId为班级编号,pageIndex为页码下标
	 * @return 符合该学生条件的所有考勤记录
	 */
	public List<AttendanceRecord> selectStuAttendanceReocrd(@Param("id")Integer id,@Param("classId")Integer classId,@Param("pageIndex")Integer pageIndex);
	/**
	 * @author 连慧
	 * 查询学生考勤记录的总记录数
	 * @param id学生编号，classId为班级编号
	 * @return 符合该学生条件的所有考勤记录
	 */
	public Integer getMaxStuAttendanceReocrd(@Param("id")Integer id,@Param("classId")Integer classId);
	/**
	 * @author 连慧
	 * 通过时间查询学生考勤记录
	 * @param id学生编号，classId为班级编号,choice是option选择的值,pageIndex页码下标
	 * @return 符合该时间段的学生所有考勤记录
	 */
	public List<AttendanceRecord> selectStuAttendanceRecordByTime(@Param("id")Integer id,@Param("classId")Integer classId,@Param("choice")Integer choice,@Param("pageIndex")Integer pageIndex);
	public Integer getMaxAttendanceRecordByTime(@Param("id")Integer id,@Param("classId")Integer classId,@Param("choice")Integer choice);
	
	/**
	 * @author 连慧
	 * 查询某个学生某个时间内全勤的记录数
	 * @param stuId学生编号，choiceTime1为查询的开始时间,choiceTime2为查询的结束时间
	 * @return 符合条件的所有考勤率的记录数
	 */
	public Integer countStuAttendanceRate(@Param("stuId")Integer stuId,@Param("choiceTime1")String choiceTime1,@Param("choiceTime2")String choiceTime2);
	/**
	 * @author 连慧
	 * 查询某个学生的工作日志最大记录数
	 * @param stuId学生编号，classId为班级编号
	 * @return 获得符合条件的工作日志记录数
	 */
	public Integer getMaxDiary(@Param("id")Integer id,@Param("classId")Integer classId);
	/**
	 * @author 连慧
	 * 查询学生工作日志
	 * @param id学生编号，classId为班级编号,pageIndex为页码下标
	 * @return 符合该学生条件的所有工作日志
	 */
	public List<Diary> selectDiary(@Param("id")Integer id,@Param("classId")Integer classId,@Param("pageIndex")Integer pageIndex);
	/**
	 * @author 连慧
	 * 通过时间查询学生工作日志
	 * @param id学生编号，classId为班级编号,choice是option选择的值,pageIndex页码下标
	 * @return 符合该时间段的学生所有工作日志
	 */
	public List<Diary> selectDiaryByTime(@Param("id")Integer id,@Param("classId")Integer classId,@Param("choice")Integer choice,@Param("pageIndex")Integer pageIndex);
	/**
	 * @author 连慧
	 * 查询某个学生某个时段的工作日志最大记录数
	 * @param stuId学生编号，classId为班级编号
	 * @return 获得符合条件的工作日志记录数
	 */
	public Integer getMaxDiaryByTime(@Param("id")Integer id,@Param("classId")Integer classId,@Param("choice")Integer choice);
	/**
	 * @author 连慧
	 * 插入日志信息
	 * @param stuId学生编号，diaryContent问题内容，commitTime提交时间
	 * @return >0插入工作日志成功，否则失败
	 */
	public Integer insertDiary(@Param("stuId")Integer stuId,@Param("diaryContent")String diaryContent,@Param("commitTime")String commitTime,@Param("diaryDate")String diaryDate);
	/**
	 * @author 连慧
	 * 插入提问信息
	 * @param stuId学生编号，questionContent问题内容，commitTime提交时间
	 * @return >0插入问题成功，否则失败
	 */
	public Integer insertQuestion(@Param("stuId")Integer stuId,@Param("questionContent")String questionContent,@Param("commitTime")String commitTime);
	/**
	 * @author 连慧
	 * 通过日志时间查询学生工作日志
	 * @param stuId学生编号，diaryDate日志提交时间（年月日）
	 * @return 符合该时间段的学生工作日志
	 */
	public Diary selectDiaryByDiaryDate(@Param("stuId")Integer stuId,@Param("diaryDate")String diaryDate);
	/**
	 * @author 连慧
	 * 通过问题时间查询学生提问
	 * @param stuId学生编号，questionDate提问提交时间（年月日）
	 * @return 符合该时间段的学生提问
	 */
	public Question selectQuestionByDate(@Param("stuId")Integer stuId,@Param("questionDate")Timestamp questionDate);
	/**
	 * @author 连慧
	 * 修改工作日志，只能修改日志内容
	 * @param stuId学生编号，diaryDate日志提交时间（年月日），diaryContent日志内容
	 * @return >0修改成功，否则修改失败
	 */
	public Integer updateDiary(@Param("stuId")Integer stuId,@Param("diaryDate")String diaryDate,@Param("diaryContent")String diaryContent);
	/**
	 * @author 连慧
	 * 分配积分
	 * @param stuId获得积分的学生编号，giverId分配积分者编号，integrals积分数
	 * @return >0修改成功，否则修改失败
	 */
	public Integer insertIntegrals(@Param("stuId")Integer stuId,@Param("giverId")Integer giverId,@Param("integrals")Integer integrals);
	/**
	 * @author 连慧
	 * 计算积分
	 * @param stuId获得积分的学生编号
	 * @return >0修改成功，否则修改失败
	 */
	public Integer countIntegrals(@Param("stuId")Integer stuId);
	/**
	 * @author 连慧
	 * 查询本周最新的提问数
	 * @param stuId获得积分的学生编号
	 * @return >0修改成功，否则修改失败
	 *//*
	public Integer countQuestion();*/
	
}
