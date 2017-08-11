package silence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月13日 下午11:02:28
* 实体类：提问类
*/
public class Question implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 3545362144982446589L;
	private Integer id; // 提问id
	private Integer stuId; // 提问学生id
	private String questionContent; // 提问内容
	private Timestamp questionTime; // 提问时间
	private Integer questionGiveIntegral; //提问者给出的积分
	private String stuNo;  //学号
	private String stuName;  //学生姓名
	private String className; //班级名称
	
	public Question() {
	}

	public Question(Integer stuId, String questionContent, Timestamp questionTime, Integer questionGiveIntegral,
			String stuNo, String stuName, String className) {
		super();
		this.stuId = stuId;
		this.questionContent = questionContent;
		this.questionTime = questionTime;
		this.questionGiveIntegral = questionGiveIntegral;
		this.stuNo = stuNo;
		this.stuName = stuName;
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

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public Timestamp getQuestionTime() {
		return questionTime;
	}

	public void setQuestionTime(Timestamp questionTime) {
		this.questionTime = questionTime;
	}

	public Integer getQuestionGiveIntegral() {
		return questionGiveIntegral;
	}

	public void setQuestionGiveIntegral(Integer questionGiveIntegral) {
		this.questionGiveIntegral = questionGiveIntegral;
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
		return "Question [id=" + id + ", stuId=" + stuId + ", questionContent=" + questionContent + ", questionTime="
				+ questionTime + ", questionGiveIntegral=" + questionGiveIntegral + ", stuNo=" + stuNo + ", stuName="
				+ stuName + ", className=" + className + "]";
	}

}
