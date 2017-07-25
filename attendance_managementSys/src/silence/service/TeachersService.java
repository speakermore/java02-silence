package silence.service;

import java.sql.Timestamp;
import java.util.List;

import silence.entity.Students;
import silence.entity.Teacher;
import silence.entity.AttendanceRecord;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月17日 上午10:04:39
* 老师接口
*/

public interface TeachersService {
	public Integer reg(Students stu);
	
	public Teacher getTeacherByName(String tecName);
	
	public Teacher getTecById(Integer id);

	public Integer tecUpdatePwd(Integer id,String newPwd); 
	
	public Students verifyStuExist(Integer id,String stuNo);
	
	public List<AttendanceRecord> lookAttendanceRecord(Timestamp attendanceTime1,Timestamp attendanceTime2,Integer id,String stuNo,String stuName);
	
}
