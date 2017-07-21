package silence.dao;

import org.apache.ibatis.annotations.Param;

import silence.entity.Students;
import silence.entity.Teacher;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月17日 上午10:00:43
* 老师dao
*/
public interface TeachersMapper {
	/**
	 * 袁云：老师添加学生用户，添加内容为学号，学生所在班级，学生姓名，密码，学生初始积分，学生性别
	 * @param stu 封装了学生信息的实体对象
	 * @return 大于0表示添加成功，否则失败
	 */
	public Integer reg(Students stu);
	
	/**
	 * @author 袁云
	 * 查询所有老师，用于对老师登录信息的验证
	 * @return 老师对象（返回值的意义）
	 */
	public Teacher getTeacherByName(@Param("tecName")String tecName);
	
	/**
	 * @author 袁云
	 * 根据id获得老师
	 * @return 老师对象（返回值的意义）
	 */
	public Teacher getTecById(@Param("id")Integer id);
	
	/**
	 * @author 袁云
	 * 跟新修改的密码到数据库
	 * @return 大于0表示添加成功，否则失败
	 */
	public Integer tecUpdatePwd(@Param("id")Integer id,@Param("newPwd")String newPwd);
	
	
	
}