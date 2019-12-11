package com.example.demo.service;

import com.example.demo.entity.Teacher;

/*
	业务接口
*/
public interface ITeacherService extends IBaseService<Teacher> {

	/**
	 * 检查教师编号和教师名字是否已经存在
	 * @param num
	 * @param name
	 * @return 如果返回true，代表不存在，如果返回false，代表教师编号或教师名字已经存在
	 */
	boolean checkTeachernameAndTeacherNum(String teachernum, String teachername);
	
	
	/**
	 * 检查教师编号和教师名字是否已经存在
	 * @param id 
	 * @param num 
	 * @param name
	 * @return 如果返回true，代表不存在，如果返回false，代表教师编号或教师名字已经存在
	 */
	boolean checkTeachernameAndTeacherNum(int id, String teachernum, String teachername);
	
	/**
	 * 检查教师名字是否存在
	 * @param teachername 
	 * @return 如果返回true，代表不存在，如果返回false，代表教师名字存在
	 */
	boolean checkTeachername(String teachername);
	

	/**
	 * 批量删除
	 * @param ids 删除教师ID的数组
	 */
	void deleteBatch(String[] ids);
	
}
