package silence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月13日 下午11:03:00
* 实体类：考勤记录类
*/
public class attendanceRecord implements Serializable {

	/**
	 *生成序列化id 
	 */
	private static final long serialVersionUID = 8243505218871454088L;
	private Integer id;    //考勤记录Id
	private Integer stuId;   //学生Id
	private Timestamp attendanceComeTime;   //到校时间
	private Timestamp attendanceBackTime;   //离校时间
	
	public attendanceRecord() {
	}
	public attendanceRecord(Integer stuId, Timestamp attendanceComeTime, Timestamp attendanceBackTime) {
		this.stuId = stuId;
		this.attendanceComeTime = attendanceComeTime;
		this.attendanceBackTime = attendanceBackTime;
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
	@Override
	public String toString() {
		return "attendanceRecord [id=" + id + ", stuId=" + stuId + ", attendanceComeTime=" + attendanceComeTime
				+ ", attendanceBackTime=" + attendanceBackTime + "]";
	}
	
	
}
