package silence.dao;

import org.apache.ibatis.annotations.Param;

import silence.entity.Students;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月17日 上午9:53:47
* Dao层接口：学生接口
*/
public interface StudentMapper {
	/**
	 * 查询所有学生，用于JSP页面上进行学生信息的显示，进行页面学生信息的验证
	 * @return 包含所有学生实体类对象的集合（返回值的意义）
	 */
	public Students studentLogin(@Param("stuNumber")String stuNumber,@Param("stuPwd")String stuPwd);
}
