package silence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月13日 下午10:48:53
* 实体类：点赞类
*/
public class Likes implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -5153742526333198011L;
	private int id; // 点赞id
	private int stuId; // 被点赞学生id
	private int likeStuId; // 点赞学生id
	private Timestamp likeTime; // 点赞时间
	
	public Likes() {
	}
	
	public Likes(int stuId, int likeStuId, Timestamp likeTime) {
		this.stuId = stuId;
		this.likeStuId = likeStuId;
		this.likeTime = likeTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public int getLikeStuId() {
		return likeStuId;
	}

	public void setLikeStuId(int likeStuId) {
		this.likeStuId = likeStuId;
	}

	public Timestamp getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}

	@Override
	public String toString() {
		return "Likes [id=" + id + ", stuId=" + stuId + ", likeStuId=" + likeStuId + ", likeTime=" + likeTime + "]";
	}
}
