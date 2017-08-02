package silence.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import silence.dao.TeachersMapper;
import silence.entity.AttendanceRecord;
import silence.entity.Diary;
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
	public ArrayList<AttendanceRecord> verifyStuExist2(Date attendanceTime1, Date attendanceTime2) {
		// TODO Auto-generated method stub
		return teachersMapper.verifyStuExist2(attendanceTime1, attendanceTime2);
	}

	@Override
	public List<AttendanceRecord> lookAttendanceRecord(Date attendanceTime1, Date attendanceTime2, Integer id,
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
	public Integer getMaxRecordByCondition(Date attendanceTime1, Date attendanceTime2, Integer stuClass,
			String stuNo, String stuName) {
		// TODO Auto-generated method stub
		return teachersMapper.getMaxRecordByCondition(attendanceTime1, attendanceTime2, stuClass, stuNo, stuName);
	}

	@Override
	public Integer getStuStatusIsYNo(Date date1, Date date2, Integer stuClass, String stuNo, String stuName) {
		// TODO Auto-generated method stub
		return teachersMapper.getStuStatusIsYNo(date1, date2, stuClass, stuNo, stuName);
	}

	@Override
	public Integer getClassStatusIsYNo(Date date1, Date date2, Integer stuClass) {
		// TODO Auto-generated method stub
		return teachersMapper.getClassStatusIsYNo(date1, date2, stuClass);
	}

	@Override
	public Integer getClassStuCount(Integer stuClass) {
		// TODO Auto-generated method stub
		return teachersMapper.getClassStuCount(stuClass);
	}

	@Override
	public Integer getMaxRecordByClass(Date date1, Date date2, Integer stuClass) {
		// TODO Auto-generated method stub
		return teachersMapper.getMaxRecordByClass(date1, date2, stuClass);
	}

	@Override
	public Integer getMaxDiaryRecord() {
		// TODO Auto-generated method stub
		return teachersMapper.getMaxDiaryRecord();
	}

	@Override
	public List<Diary> findAllDiary(Integer pageIndex) {
		// TODO Auto-generated method stub
		return teachersMapper.findAllDiary(pageIndex);
	}

	@Override
	public Integer getDiaryMaxByCondition(Timestamp diaryCommitTime1, Timestamp diaryCommitTime2, Integer stuClass, String stuNo,
			String stuName) {
		// TODO Auto-generated method stub
		return teachersMapper.getDiaryMaxByCondition(diaryCommitTime1, diaryCommitTime2, stuClass, stuNo, stuName);
	}

	@Override
	public List<Diary> lookDiary(Timestamp diaryCommitTime1, Timestamp diaryCommitTime2, Integer id, String stuNo, String stuName,
			Integer pageIndex) {
		// TODO Auto-generated method stub
		return teachersMapper.lookDiary(diaryCommitTime1, diaryCommitTime2, id, stuNo, stuName, pageIndex);
	}

	@Override
	public ArrayList<Diary> verifyDiaryExists(Timestamp diaryCommitTime1, Timestamp diaryCommitTime2) {
		// TODO Auto-generated method stub
		return teachersMapper.verifyDiaryExists(diaryCommitTime1, diaryCommitTime2);
	}

}
