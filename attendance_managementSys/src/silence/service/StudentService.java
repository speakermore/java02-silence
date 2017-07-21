package silence.service;

import java.sql.Timestamp;

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
	//通过学生编号查询学生密码
	public String getStudentPwd(Integer id);
	//修改学生密码
	public Integer updateStuPwd(String stuPwd,Integer id);
	//插入学生到校时间
	public Integer insertComeTime(Integer stuId,Timestamp comeTime);
}
