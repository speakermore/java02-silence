package silence.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import silence.dao.StudentMapper;
import silence.entity.AttendanceRecord;
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
	public Integer insertComeTime(Integer stuId,Date attDate,Timestamp comeTime,String attStatus) {
		return studentMapper.insertComeTime(stuId,attDate,comeTime,attStatus);
	}

	@Override
	public List<AttendanceRecord> selectStuAttendanceReocrd(Integer id, Integer classId,Integer pageIndex) {
		return studentMapper.selectStuAttendanceReocrd(id, classId,pageIndex);
	}

	@Override
	public Integer getMaxStuAttendanceReocrd(Integer id, Integer classId) {
		return studentMapper.getMaxStuAttendanceReocrd(id, classId);
	}

	@Override
	public List<AttendanceRecord> selectStuAttendanceRecordByTime(Integer id, Integer classId, Integer choice,Integer pageIndex) {
		return studentMapper.selectStuAttendanceRecordByTime(id, classId, choice,pageIndex);
	}

	@Override
	public Integer getMaxAttendanceRecordByTime(Integer id, Integer classId, Integer choice) {
		return studentMapper.getMaxAttendanceRecordByTime(id, classId, choice);
	}

	@Override
	public AttendanceRecord selectStuAttRecord(Integer stuId, String attendanceDate) {
		return studentMapper.selectStuAttRecord(stuId, attendanceDate);
	}

	@Override
	public Integer insertBackTime(Integer stuId, Date attDate, Timestamp backTime, String attStatus) {
		return studentMapper.insertBackTime(stuId, attDate, backTime, attStatus);
	}

	@Override
	public Integer updateBackTime(Integer stuId, String attDate, Timestamp backTime, String attStatus) {
		return studentMapper.updateBackTime(stuId, attDate, backTime, attStatus);
	}
}
