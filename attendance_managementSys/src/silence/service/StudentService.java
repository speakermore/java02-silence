package silence.service;

import org.apache.ibatis.annotations.Param;

import silence.entity.Students;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月17日 上午11:35:42
* 用户业务接口：学生接口
*/
public interface StudentService {
	//通过学号获得学生对象
	public Students getStudentByStuNo(String stuNumber);
	//修改学生密码
	public Integer updateStuPwd(String stuPwd,String stuNo);
}
