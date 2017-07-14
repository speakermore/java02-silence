package silence.entity;

import java.io.Serializable;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月13日 下午11:17:09
* 实体类：老师类
*/
public class Teacher implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -6290614473190720073L;
	private Integer id; // 老师id
	private String tecName; //老师姓名
	private String tecPwd; //老师密码
	
	public Teacher() {
	}

	public Teacher(String tecName, String tecPwd) {
		this.tecName = tecName;
		this.tecPwd = tecPwd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTecName() {
		return tecName;
	}

	public void setTecName(String tecName) {
		this.tecName = tecName;
	}

	public String getTecPwd() {
		return tecPwd;
	}

	public void setTecPwd(String tecPwd) {
		this.tecPwd = tecPwd;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", tecName=" + tecName + ", tecPwd=" + tecPwd + "]";
	}
}
