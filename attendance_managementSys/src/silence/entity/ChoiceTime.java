package silence.entity;

import java.io.Serializable;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月27日 下午2:17:31
*/
public class ChoiceTime implements Serializable{

	/**
	 * 生成序列化ID
	 */
	private static final long serialVersionUID = 6871543624910019164L;
	private Integer id;
	private String times;
	public ChoiceTime() {
	}
	public ChoiceTime(Integer id, String times) {
		this.id = id;
		this.times = times;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "ChoiceTime [id=" + id + ", times=" + times + "]";
	}
}
