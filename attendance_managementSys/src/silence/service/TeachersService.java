package silence.service;

import silence.entity.Students;
import silence.entity.Teacher;

/** 
* @author  作者:袁云 
* @date 创建时间：2017年7月17日 上午10:04:39
* 老师接口
*/

public interface TeachersService {
	public Integer reg(Students stu);
	
	public Teacher getTeacherByName(String tecName);
	
	public Teacher getTecById(Integer id);

	public Integer tecUpdatePwd(Integer id,String newPwd); 
	
}
