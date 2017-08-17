package silence.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import silence.dao.StudentMapper;
import silence.entity.AttendanceRecord;
import silence.entity.Diary;
import silence.entity.Question;
import silence.entity.Students;
import silence.service.StudentService;

/**
 * @author 作者：连慧
 * @date 创作时间：2017年7月17日 上午11:41:28 Service层的实现类：学生类
 */

// @Service注解表明此类为业务层，写在实现类的前面
@Service
public class StudentServiceImpl implements StudentService {
	// @Resource为自动注入的注解，表明此类要通过Spring容器完成注入
	@Resource
	private StudentMapper studentMapper;

	@Override
	public Students getStudentByStuNo(String stuNumber) {
		return studentMapper.getStudentByStuNo(stuNumber);
	}

	@Override
	public Integer updateStuPwd(String stuPwd, Integer id) {
		return studentMapper.updateStuPwd(stuPwd, id);
	}

	@Override
	public String getStudentPwd(Integer id) {
		return studentMapper.getStudentPwd(id);
	}

	@Override
	public Integer insertComeTime(Integer stuId, Date attDate, Timestamp comeTime, String attStatus) {
		return studentMapper.insertComeTime(stuId, attDate, comeTime, attStatus);
	}

	@Override
	public List<AttendanceRecord> selectStuAttendanceReocrd(Integer id, Integer classId, Integer pageIndex) {
		return studentMapper.selectStuAttendanceReocrd(id, classId, pageIndex);
	}

	@Override
	public Integer getMaxStuAttendanceReocrd(Integer id, Integer classId) {
		return studentMapper.getMaxStuAttendanceReocrd(id, classId);
	}

	@Override
	public List<AttendanceRecord> selectStuAttendanceRecordByTime(Integer id, Integer classId, Integer choice,
			Integer pageIndex) {
		return studentMapper.selectStuAttendanceRecordByTime(id, classId, choice, pageIndex);
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

	@Override
	public Integer countStuAttendanceRate(Integer stuId, String choiceTime1, String choiceTime2) {
		return studentMapper.countStuAttendanceRate(stuId, choiceTime1, choiceTime2);
	}

	@Override
	public Integer getMaxDiary(Integer id, Integer classId) {
		return studentMapper.getMaxDiary(id, classId);
	}

	@Override
	public List<Diary> selectDiary(Integer id, Integer classId, Integer pageIndex) {
		return studentMapper.selectDiary(id, classId, pageIndex);
	}

	@Override
	public List<Diary> selectDiaryByTime(Integer id, Integer classId, Integer choice, Integer pageIndex) {
		return studentMapper.selectDiaryByTime(id, classId, choice, pageIndex);
	}

	@Override
	public Integer getMaxDiaryByTime(Integer id, Integer classId, Integer choice) {
		return studentMapper.getMaxDiaryByTime(id, classId, choice);
	}

	@Override
	public Integer insertDiary(Integer stuId, String diaryContent, String commitTime,String diaryDate) {
		return studentMapper.insertDiary(stuId, diaryContent, commitTime,diaryDate);
	}

	@Override
	public Integer insertQuestion(Integer stuId, String questionContent, String commitTime) {
		return studentMapper.insertQuestion(stuId, questionContent, commitTime);
	}

	@Override
	public Diary selectDiaryByDiaryDate(Integer stuId, String diaryDate) {
		return studentMapper.selectDiaryByDiaryDate(stuId, diaryDate);
	}

	@Override
	public Question selectQuestionByDate(Integer stuId, Timestamp questionDate) {
		return studentMapper.selectQuestionByDate(stuId, questionDate);
	}

	@Override
	public Integer updateDiary(Integer stuId, String diaryDate, String diaryContent) {
		return studentMapper.updateDiary(stuId, diaryDate, diaryContent);
	}

	@Override
	public Integer insertIntegrals(Integer stuId, Integer giverId, Integer integrals) {
		return studentMapper.insertIntegrals(stuId, giverId, integrals);
	}

	@Override
	public Integer countIntegrals(Integer stuId) {
		return studentMapper.countIntegrals(stuId);
	}

	/*@Override
	public Integer countQuestion() {
		return studentMapper.countQuestion();
	}*/

}
