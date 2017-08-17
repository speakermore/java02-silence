package silence.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import silence.entity.AttendanceRecord;
import silence.entity.Diary;
import silence.entity.Question;
import silence.entity.Students;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月17日 上午11:35:42
* 用户业务接口：学生接口
*/
public interface StudentService {
	//通过学号获得学生对象
	public Students getStudentByStuNo(String stuNumber);
	//通过学生编号查询学生密码
	public String getStudentPwd(Integer id);
	//修改学生密码
	public Integer updateStuPwd(String stuPwd,Integer id);
	//查询是否有考勤记录，有考勤记录就不能签到/签退
	public AttendanceRecord selectStuAttRecord(Integer stuId,String attendanceDate);
	//插入学生到校时间和考勤状态
	public Integer insertComeTime(Integer stuId,Date attDate,Timestamp comeTime,String attStatus);
	//插入学生离校时间和考勤状态
	public Integer insertBackTime(Integer stuId,Date attDate,Timestamp backTime,String attStatus);
	//更新学生离校时间和考勤状态
	public Integer updateBackTime(Integer stuId,String attDate,Timestamp backTime,String attStatus);
	//查询学生考勤记录
	public Integer getMaxStuAttendanceReocrd(Integer id,Integer classId);
	public List<AttendanceRecord> selectStuAttendanceReocrd(Integer id,Integer classId,Integer pageIndex);
	//根据时间查询学生考勤记录
	public List<AttendanceRecord> selectStuAttendanceRecordByTime(Integer id,Integer classId,Integer choice,Integer pageIndex);
	public Integer getMaxAttendanceRecordByTime(Integer id,Integer classId,Integer choice);
	//查询某个学生某个时间内全勤的记录数
	public Integer countStuAttendanceRate(Integer stuId,String choiceTime1,String choiceTime2);
	//查询某个学生的工作日志最大记录数
	public Integer getMaxDiary(Integer id,Integer classId);
	//查询学生工作日志
	public List<Diary> selectDiary(Integer id,Integer classId,Integer pageIndex);
	//查询某个学生某个时间内的工作日志
	public List<Diary> selectDiaryByTime(Integer id,Integer classId,Integer choice,Integer pageIndex);
	//查询某个学生某个时间内工作日志的记录数
	public Integer getMaxDiaryByTime(Integer id,Integer classId,Integer choice);
	//插入工作日志信息
	public Integer insertDiary(Integer stuId,String diaryContent,String commitTime,String diaryDate);
	//插入提问信息
	public Integer insertQuestion(Integer stuId,String questionContent,String commitTime);
	//通过日志日期查询学生工作日志
	public Diary selectDiaryByDiaryDate(Integer stuId,String diaryDate);
	//通过问题时间查询学生提问
	public Question selectQuestionByDate(Integer stuId,Timestamp questionDate);
	//修改工作日志，只能修改日志内容
	public Integer updateDiary(Integer stuId,String diaryDate,String diaryContent);
	//分配积分
	public Integer insertIntegrals(Integer stuId,Integer giverId,Integer integrals);
	//计算积分
	public Integer countIntegrals(Integer stuId);
	/*//查询本周最新的提问数
	public Integer  countQuestion();*/
}
