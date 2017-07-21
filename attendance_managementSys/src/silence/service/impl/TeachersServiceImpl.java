package silence.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import silence.dao.TeachersMapper;
import silence.entity.Students;
import silence.entity.Teacher;
import silence.service.TeachersService;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月17日 上午11:37:48
*/
//@Service注解表明此类为业务类层，写在实现类的前面
@Service
public class TeachersServiceImpl implements TeachersService {
	//@Resource为自动注入的注解，表明此类要通过Spring容器完成注入
	@Resource
	private TeachersMapper teachersMapper;

	@Override
	public Integer reg(Students stu) {
		// TODO Auto-generated method stub
		return teachersMapper.reg(stu) ;
	}

	@Override
	public Teacher getTeacherByName(String tecName) {
		// TODO Auto-generated method stub
		return teachersMapper.getTeacherByName(tecName);
	}

	@Override
	public Teacher getTecById(Integer id) {
		// TODO Auto-generated method stub
		return teachersMapper.getTecById(id);
	}
	
	@Override
	public Integer tecUpdatePwd(Integer id,String newPwd) {
		// TODO Auto-generated method stub
		return teachersMapper.tecUpdatePwd(id,newPwd);
	}

}
