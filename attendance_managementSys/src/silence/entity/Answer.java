package silence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月13日 下午10:46:30
* 实体类：答复类
*/
public class Answer implements Serializable {

	/**
	 * 生成序列化id
	 */
	private static final long serialVersionUID = 3964610698269798419L;
	private Integer id;    //答复表id
	private Integer questionId;   //提问id
	private Integer answerId;   //答复者id
	private String answerContent;   //答复内容
	private Timestamp answerTime;   //答复时间
	private String stuNo;  //学号
	private String stuName;  //学生姓名
	private String className; //班级名称
	private String tecName; //老师姓名
	
	public Answer() {
	}

	public Answer(Integer questionId, Integer answerId, String answerContent, Timestamp answerTime, String stuNo,
			String stuName, String className, String tecName) {
		this.questionId = questionId;
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.answerTime = answerTime;
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.className = className;
		this.tecName = tecName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Timestamp getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Timestamp answerTime) {
		this.answerTime = answerTime;
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
	
	public String getTecName() {
		return tecName;
	}

	public void setTecName(String tecName) {
		this.tecName = tecName;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", questionId=" + questionId + ", answerId=" + answerId + ", answerContent="
				+ answerContent + ", answerTime=" + answerTime + ", stuNo=" + stuNo + ", stuName=" + stuName
				+ ", className=" + className + ", tecName=" + tecName + "]";
	}

}
