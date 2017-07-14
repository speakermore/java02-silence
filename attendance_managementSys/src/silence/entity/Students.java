package silence.entity;

import java.io.Serializable;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月13日 下午11:08:52
* 实体类：学生类
*/
public class Students implements Serializable {

	/**
	 * 系列化id
	 */
	private static final long serialVersionUID = -6842260507279202866L;
	private Integer id; //学生id
	private String stuNo; //学号
	private String stuName; //学生姓名
	private Integer classId; //班级id
	private String stuPwd; // 学生密码
	private String stuGender; // 学生性别
	
	public Students() {
	}

	public Students(String stuNo, String stuName, Integer classId, String stuPwd, String stuGender) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.classId = classId;
		this.stuPwd = stuPwd;
		this.stuGender = stuGender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getStuPwd() {
		return stuPwd;
	}

	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}

	public String getStuGender() {
		return stuGender;
	}

	public void setStuGender(String stuGender) {
		this.stuGender = stuGender;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", stuNo=" + stuNo + ", stuName=" + stuName + ", classId=" + classId + ", stuPwd="
				+ stuPwd + ", stuGender=" + stuGender + "]";
	}
}
