package silence.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import silence.entity.AttendanceRecord;
import silence.entity.Students;
import silence.entity.Teacher;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月17日 上午10:04:39
* 老师接口
*/

public interface TeachersService {
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
	public Teacher getTeacherByName(String tecName);
	/**
	 * @author 袁云
	 * 根据id获得老师
	 * @return 老师对象（返回值的意义）
	 */
	public Teacher getTecById(Integer id);
	/**
	 * @author 袁云
	 * 跟新修改的密码到数据库
	 * @return 大于0表示添加成功，否则失败
	 */
	public Integer tecUpdatePwd(Integer id,String newPwd); 
	/**
	 * @author 袁云
	 * 根据页面传过来的班级id和学号验证该班级是否存在该学生
	 * @return 学生对象（返回值的意义）
	 */
	public Students verifyStuExist(Integer id,String stuNo);
	/**
	 * @author 袁云
	 * 根据页面传过来的时间验证该时间段是否存在考勤记录
	 * @return 考勤记录对象（返回值的意义）
	 */
	public ArrayList<AttendanceRecord> verifyStuExist2(Date attendanceTime1,Date attendanceTime2);
	/**
	 * @author 袁云
	 * 老师根据查询条件查看考勤记录
	 * @return 包含所有查出来的考勤记录实体对象的集合
	 */
	public List<AttendanceRecord> lookAttendanceRecord(Date attendanceTime1,Date attendanceTime2,Integer id,String stuNo,String stuName, Integer pageIndex);
	/**
	 * @author 袁云
	 * 获得考勤的最大记录数
	 * @return 考勤最大记录数
	 */
	public Integer getMaxRecord();
	
	/**
	 * @author 袁云
	 * 老师查看考勤记录
	 * @return 包含所有考勤记录实体对象的集合
	 */
	public List<AttendanceRecord> findAll(Integer pageIndex);
	
	/**
	 * @author 袁云
	 * 老师按查询条件查看考勤记录获得记录总数
	 * @return 符合条件的考勤记录总数
	 */
	public Integer getMaxRecordByCondition(Date attendanceTime1, Date attendanceTime2, Integer stuClass, String stuNo, String stuName);
	
	/**
	 * @author 袁云
	 * 查询某个同学在一段时间内的出勤状态为y（出勤）的数量
	 * @return 出勤状态为y（出勤）的数量
	 */
	public Integer getStuStatusIsYNo(Date date1,Date date2,Integer stuClass,String stuNo,String stuName);
	
	/**
	 * @author 袁云
	 * 查询某个班级在一段时间内的出勤状态为y（出勤）的数量
	 * @return 出勤状态为y（出勤）的数量
	 */
	public Integer getClassStatusIsYNo(Date date1,Date date2,Integer stuClass);
	
	/**
	 * @author 袁云
	 * 查看某个班有多少个学生
	 * @return 学生数量
	 */
	public Integer getClassStuCount(Integer stuClass);
	
	/**
	 * @author 袁云
	 * 查看某个班在一段时间内的考勤记录总数
	 * @return 考勤记录数量
	 */
	public Integer getMaxRecordByClass(Date date1,Date date2,Integer stuClass);
}
