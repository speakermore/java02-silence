package silence.entity;

import java.io.Serializable;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月13日 下午10:24:24
* 实体类：积分类
*/
public class Integral implements Serializable {
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -3732499318879223793L;
	private Integer id; //积分id
	private Integer integralsGetStuId;//获得积分的学生id
	private Integer integralsGiverId;//给积分者id
	private Integer integrals;//积分数
	
	public Integral() {
	}

	public Integral(Integer integralsGetStuId, Integer integralsGiverId, Integer integrals) {
		this.integralsGetStuId = integralsGetStuId;
		this.integralsGiverId = integralsGiverId;
		this.integrals = integrals;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIntegralsGetStuId() {
		return integralsGetStuId;
	}

	public void setIntegralsGetStuId(Integer integralsGetStuId) {
		this.integralsGetStuId = integralsGetStuId;
	}

	public Integer getIntegralsGiverId() {
		return integralsGiverId;
	}

	public void setIntegralsGiverId(Integer integralsGiverId) {
		this.integralsGiverId = integralsGiverId;
	}

	public Integer getIntegrals() {
		return integrals;
	}

	public void setIntegrals(Integer integrals) {
		this.integrals = integrals;
	}

	@Override
	public String toString() {
		return "Integral [id=" + id + ", integralsGetStuId=" + integralsGetStuId + ", integralsGiverId="
				+ integralsGiverId + ", Integrals=" + integrals + "]";
	}
}
