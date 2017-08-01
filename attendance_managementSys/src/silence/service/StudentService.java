package silence.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import silence.entity.AttendanceRecord;
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
}
