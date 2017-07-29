package silence.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月13日 下午11:03:00
* 实体类：考勤记录类
*/
public class AttendanceRecord implements Serializable {
	/**
	 *生成序列化id 
	 */
	private static final long serialVersionUID = 8243505218871454088L;
	private Integer id;    //考勤记录Id
	private Integer stuId;   //学生Id
	private String stuNo;    //学号
	private String stuName;   //学生姓名
	private String className;  //班级名称
	private Timestamp attendanceComeTime;   //到校时间
	private Timestamp attendanceBackTime;   //离校时间
	private Date attendanceDate;   //签到时间
	private String attendanceStatus;   //签到状态
	

	public AttendanceRecord() {
	}
	
	public AttendanceRecord(Integer stuId, String stuNo, String stuName, String className, Timestamp attendanceComeTime,
			Timestamp attendanceBackTime,Date attendanceDate,String attendanceStatus) {
		this.stuId = stuId;
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.className = className;
		this.attendanceComeTime = attendanceComeTime;
		this.attendanceBackTime = attendanceBackTime;
		this.attendanceDate = attendanceDate;
		this.attendanceStatus = attendanceStatus;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}
	
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Timestamp getAttendanceComeTime() {
		return attendanceComeTime;
	}
	public void setAttendanceComeTime(Timestamp attendanceComeTime) {
		this.attendanceComeTime = attendanceComeTime;
	}
	public Timestamp getAttendanceBackTime() {
		return attendanceBackTime;
	}
	public void setAttendanceBackTime(Timestamp attendanceBackTime) {
		this.attendanceBackTime = attendanceBackTime;
	}
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "AttendanceRecord [id=" + id + ", stuId=" + stuId + ", stuNo=" + stuNo + ", stuName=" + stuName
				+ ", className=" + className + ", attendanceComeTime=" + attendanceComeTime + ", attendanceBackTime="
				+ attendanceBackTime + ", attendanceDate=" + attendanceDate + ", attendanceStatus=" + attendanceStatus
				+ "]";
	}

	
}
