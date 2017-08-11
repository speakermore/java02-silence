package silence.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import silence.entity.Answer;
import silence.entity.AttendanceRecord;
import silence.entity.Diary;
import silence.entity.Question;
import silence.entity.Students;
import silence.entity.Teacher;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月17日 上午10:00:43
* 老师dao
*/
public interface TeachersMapper {
	/**
	 * 袁云：老师添加学生用户，添加内容为学号，学生所在班级，学生姓名，密码，学生初始积分，学生性别
	 * @param stu 封装了学生信息的实体对象
	 * @return 大于0表示添加成功，否则失败
	 */
	public Integer reg(Students stu);
	
	/**
	 * @author 袁云
	 * 查询所有老师，用于对老师登录信息的验证
	 * @return 老师对象（返回值的意义）
	 */
	public Teacher getTeacherByName(@Param("tecName")String tecName);
	
	/**
	 * @author 袁云
	 * 根据id获得老师
	 * @return 老师对象（返回值的意义）
	 */
	public Teacher getTecById(@Param("id")Integer id);
	
	/**
	 * @author 袁云
	 * 跟新修改的密码到数据库
	 * @return 大于0表示添加成功，否则失败
	 */
	public Integer tecUpdatePwd(@Param("id")Integer id,@Param("newPwd")String newPwd);
	
	/**
	 * @author 袁云
	 * 根据页面传过来的班级id和学号验证该班级是否存在该学生
	 * @return 学生对象（返回值的意义）
	 */
	public Students verifyStuExist(@Param("id")Integer id,@Param("stuNo")String stuNo);
	
	/**
	 * @author 袁云
	 * 根据页面传过来的时间验证改时间段是否存在考勤记录
	 * @return 考勤记录对象（返回值的意义）
	 */
	public ArrayList<AttendanceRecord> verifyStuExist2(@Param("attendanceTime1")Date attendanceTime1,@Param("attendanceTime2")Date attendanceTime2);
	
	/**
	 * @author 袁云
	 * 老师根据查询条件查看考勤记录
	 * @return 考勤记录集合
	 */
	public List<AttendanceRecord> lookAttendanceRecord(@Param("attendanceTime1")Date attendanceTime1,@Param("attendanceTime2")Date attendanceTime2,@Param("id")Integer id,@Param("stuNo")String stuNo,@Param("stuName")String stuName,@Param("pageIndex")Integer pageIndex);
	/**
	 * @author 袁云
	 * 获得考勤的最大记录数
	 * @return 考勤最大记录数
	 */
	public Integer getMaxRecord();
	/**
	 * @author 袁云
	 * 老师查看考勤记录
	 * @return 考勤记录集合
	 */
	public List<AttendanceRecord> findAll(@Param("pageIndex") Integer pageIndex);
	
	/**
	 * @author 袁云
	 * 老师按查询条件查看考勤记录获得记录总数
	 * @return 符合条件的考勤记录总数
	 */
	public Integer getMaxRecordByCondition(@Param("attendanceTime1")Date attendanceTime1, @Param("attendanceTime2")Date attendanceTime2,@Param("stuClass") Integer stuClass,@Param("stuNo") String stuNo,@Param("stuName") String stuName);
	
	/**
	 * @author 袁云
	 * 查询某个同学在一段时间内的出勤状态为y（出勤）的数量
	 * @return 出勤状态为y（出勤）的数量
	 */
	public Integer getStuStatusIsYNo(@Param("date1") Date date1,@Param("date2") Date date2,@Param("stuClass")Integer stuClass,@Param("stuNo")String stuNo,@Param("stuName")String stuName);
	
	/**
	 * @author 袁云
	 * 查询某个班级在一段时间内的出勤状态为y（出勤）的数量
	 * @return 出勤状态为y（出勤）的数量
	 */
	public Integer getClassStatusIsYNo(@Param("date1") Date date1,@Param("date2") Date date2,@Param("stuClass")Integer stuClass);
	
	/**
	 * @author 袁云
	 * 查看某个班学生总数
	 * @return 学生数量
	 */
	public Integer getClassStuCount(@Param("stuClass")Integer stuClass);
	
	/**
	 * @author 袁云
	 * 查看某个班在一段时间内的考勤记录总数
	 * @return 考勤记录数
	 */
	public Integer getMaxRecordByClass(@Param("date1") Date date1,@Param("date2") Date date2,@Param("stuClass")Integer stuClass);
	
	/**
	 * @author 袁云
	 * 获得所有学生工作日志的最大记录数
	 * @return 工作日志最大记录数
	 */
	public Integer getMaxDiaryRecord();
	
	/**
	 * @author 袁云
	 * 老师查看所有学生所有工作日志
	 * @return 工作日志集合
	 */
	public List<Diary> findAllDiary(@Param("pageIndex") Integer pageIndex);
	
	/**
	 * @author 袁云
	 * 老师按查询条件查看工作日志获得日志总数
	 * @return 符合条件的工作日志总数
	 */
	public Integer getDiaryMaxByCondition(@Param("diaryCommitTime1")Timestamp diaryCommitTime1, @Param("diaryCommitTime2")Timestamp diaryCommitTime2,@Param("stuClass") Integer stuClass,@Param("stuNo") String stuNo,@Param("stuName") String stuName);
	
	/**
	 * @author 袁云
	 * 老师根据查询条件查看工作日志
	 * @return 包含所有查出来的工作日志实体对象的集合
	 */
	public List<Diary> lookDiary(@Param("diaryCommitTime1")Timestamp diaryCommitTime1,@Param("diaryCommitTime2")Timestamp diaryCommitTime2,@Param("id")Integer id,@Param("stuNo")String stuNo,@Param("stuName")String stuName,@Param("pageIndex")Integer pageIndex);
	
	/**
	 * @author 袁云
	 * 根据页面传过来的时间验证改时间段是否存在工作日志
	 * @return 工作日志对象集合（返回值的意义）
	 */
	public ArrayList<Diary> verifyDiaryExists(@Param("diaryCommitTime1")Timestamp diaryCommitTime1,@Param("diaryCommitTime2")Timestamp diaryCommitTime2);
	
	/**
	 * @author 袁云
	 * 根据页面传过来的学生id和提问时间查询满足条件的提问内容
	 * @return 提问内容（返回值的意义）
	 */
	public Question queryQuestionByStuIdAndTime(@Param("diaryCommitTime")Timestamp diaryCommitTime,@Param("stuId")Integer stuId);
	
	/**
	 * @author 袁云
	 * 根据页面传过来的学生id和日志提交时间查询满足条件的日志对象
	 * @return 日志对象（返回值的意义）
	 */
	public Diary queryDiaryByStuIdAndTime(@Param("diaryCommitTime")Timestamp diaryCommitTime,@Param("stuId")Integer stuId);
	
	/**
	 * @author 袁云
	 * 查询本周新提问
	 * @return 提问集合
	 */
	public List<Question> findWeekQuestion();
	
	/**
	 * @author 袁云
	 * 查询本周前十条新提问
	 * @return 提问集合
	 */
	public List<Question> findTenQuestion();
	
	/**
	 * @author 袁云
	 * 查询某一个问题id对应的回答
	 * @return 回答集合
	 */
	public List<Answer> findStuAnswerByQuestionId(@Param("questionId") Integer questionId);
	
	/**
	 * @author 袁云
	 * 查询某一个问题id对应的回答
	 * @return 回答集合
	 */
	public List<Answer> findTecAnswerByQuestionId(@Param("questionId") Integer questionId);
	
	/**
	 * @author 袁云
	 * 根据页面传过来的提问id查询满足条件的提问
	 * @return 提问对象（返回值的意义）
	 */
	public Question queryQuestionByQuestionId(@Param("questionId")Integer questionId);
	
	/**
	 * 袁云：将提交的回答保存到数据库的answer表里
	 * @param  封装了回答信息的实体对象
	 * @return 大于0表示添加成功，否则失败
	 */
	public Integer insertAnswer(Answer answer);
	
	/**
	 * 袁云：提交回答后将给出的积分插入到积分记录表里
	 * @param  给出的积分数和封装了回答信息的实体对象
	 * @return 大于0表示添加成功，否则失败
	 */
	public Integer insertIntegrals(@Param("questionId")Integer questionId,@Param("answerId")Integer answerId,@Param("integrals")Integer integrals);
	
	
}