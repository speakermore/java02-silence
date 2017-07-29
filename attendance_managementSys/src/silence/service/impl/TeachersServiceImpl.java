package silence.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import silence.dao.TeachersMapper;
import silence.entity.AttendanceRecord;
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

	@Override
	public Students verifyStuExist(Integer id, String stuNo) {
		// TODO Auto-generated method stub
		return teachersMapper.verifyStuExist(id, stuNo);
	}
	
	@Override
	public ArrayList<AttendanceRecord> verifyStuExist2(Timestamp attendanceTime1, Timestamp attendanceTime2) {
		// TODO Auto-generated method stub
		return teachersMapper.verifyStuExist2(attendanceTime1, attendanceTime2);
	}

	@Override
	public List<AttendanceRecord> lookAttendanceRecord(Timestamp attendanceTime1, Timestamp attendanceTime2, Integer id,
			String stuNo, String stuName,Integer pageIndex) {
		// TODO Auto-generated method stub
		return teachersMapper.lookAttendanceRecord(attendanceTime1, attendanceTime2, id, stuNo, stuName,pageIndex);
	}

	@Override
	public Integer getMaxRecord() {
		// TODO Auto-generated method stub
		return teachersMapper.getMaxRecord();
	}

	@Override
	public List<AttendanceRecord> findAll(Integer pageIndex) {
		// TODO Auto-generated method stub
		return teachersMapper.findAll(pageIndex);
	}

	@Override
	public Integer getMaxRecordByCondition(Timestamp attendanceTime1, Timestamp attendanceTime2, Integer stuClass,
			String stuNo, String stuName) {
		// TODO Auto-generated method stub
		return teachersMapper.getMaxRecordByCondition(attendanceTime1, attendanceTime2, stuClass, stuNo, stuName);
	}

}
