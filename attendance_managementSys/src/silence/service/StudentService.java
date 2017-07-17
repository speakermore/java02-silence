package silence.service;

import org.apache.ibatis.annotations.Param;

import silence.entity.Students;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月17日 上午11:35:42
* 用户业务接口：学生接口
*/
public interface StudentService {
	public Students studentLogin(@Param("stuNumber")String stuNumber,@Param("stuPwd")String stuPwd);
}
