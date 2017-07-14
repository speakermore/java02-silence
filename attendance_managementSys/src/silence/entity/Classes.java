package silence.entity;

import java.io.Serializable;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月13日 下午11:12:06
* 实体类：班级类
*/
public class Classes implements Serializable {

	/**
	 * 生成序列化id
	 */
	private static final long serialVersionUID = 7969272090950823563L;
	private Integer id;    //班级Id
	private String className;   //班级名称
	
	public Classes() {
	}
	public Classes(String className) {
		this.className = className;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "Classes [id=" + id + ", className=" + className + "]";
	}
	
}
