package silence.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import silence.dao.StudentMapper;
import silence.entity.Students;
import silence.service.StudentService;

/**
* @author 作者：连慧
* @date 创作时间：2017年7月17日 上午11:41:28
* Service层的实现类：学生类
*/

//@Service注解表明此类为业务层，写在实现类的前面
@Service
public class StudentServiceImpl implements StudentService{
	//@Resource为自动注入的注解，表明此类要通过Spring容器完成注入
	@Resource
	private StudentMapper studentMapper;

	@Override
	public Students getStudentByStuNo(String stuNumber) {
		return studentMapper.getStudentByStuNo(stuNumber);
	}

	@Override
	public Integer updateStuPwd(String stuPwd,Integer id) {
		return studentMapper.updateStuPwd(stuPwd,id);
	}

	@Override
	public String getStudentPwd(Integer id) {
		return studentMapper.getStudentPwd(id);
	}

	@Override
	public Integer insertComeTime(Integer stuId, Timestamp comeTime) {
		return studentMapper.insertComeTime(stuId, comeTime);
	}

}
