package silence.dao;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import silence.entity.Students;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月17日 上午9:53:47
* Dao层接口：学生接口
*/
public interface StudentMapper {
	/**
	 * @author 连慧
	 * 查询所有学生，用于JSP页面上进行学生信息的显示，进行页面学生信息的验证
	 * @return 符合条件的学生实体类对象（返回值的意义）
	 */
	public Students getStudentByStuNo(@Param("stuNumber")String stuNumber);
	
	/**
	 * @author 连慧
	 * 查询id对应的学生的密码
	 * @return 符合条件的学生密码（返回值的意义）
	 */
	public String getStudentPwd(@Param("id")Integer id);
	
	/**
	 * @author 连慧
	 * 修改学生密码
	 * @return >0表示修改成功，否则修改失败
	 */
	public Integer updateStuPwd(@Param("stuPwd")String stuPwd,@Param("id")Integer id);
	
	/**
	 * @author 连慧
	 * @param stuId学生编号，comeTime学生到校时间
	 * @return >0表示插入到校时间成功，否则失败
	 */
	public Integer insertComeTime(@Param("stuId")Integer stuId,@Param("comeTime")Timestamp comeTime);
}
