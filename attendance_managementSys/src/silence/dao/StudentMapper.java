package silence.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import silence.entity.AttendanceRecord;
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
}
