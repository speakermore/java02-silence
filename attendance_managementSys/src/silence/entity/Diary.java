package silence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月13日 下午11:16:27
* 实体类：工作日志类
*/
public class Diary implements Serializable {

	/**
	 * 生成序列化id
	 */
	private static final long serialVersionUID = -2029487971597139748L;
	private Integer id;    //答复工作日志Id
	private Integer stuId;   //学生Id
	private Timestamp diaryCommitTime;   //工作日志提交时间
	private String diaryContent;   //工作日志内容
	private String stuNo;    //学号
	private String stuName;   //学生姓名
	private String className;  //班级名称
	
	public Diary() {
	}
	
	public Diary(Integer stuId, Timestamp diaryCommitTime, String diaryContent, String stuNo, String stuName,
			String className) {
		this.stuId = stuId;
		this.diaryCommitTime = diaryCommitTime;
		this.diaryContent = diaryContent;
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.className = className;
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
	public Timestamp getDiaryCommitTime() {
		return diaryCommitTime;
	}
	public void setDiaryCommitTime(Timestamp diaryCommitTime) {
		this.diaryCommitTime = diaryCommitTime;
	}
	public String getDiaryContent() {
		return diaryContent;
	}
	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Diary [id=" + id + ", stuId=" + stuId + ", diaryCommitTime=" + diaryCommitTime + ", diaryContent="
				+ diaryContent + ", stuNo=" + stuNo + ", stuName=" + stuName + ", className=" + className + "]";
	}
	
}
