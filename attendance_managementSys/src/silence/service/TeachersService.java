package silence.service;

import silence.entity.Students;

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
}
