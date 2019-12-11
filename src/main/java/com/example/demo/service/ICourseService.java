package com.example.demo.service;

import com.example.demo.entity.Course;

/*
	业务接口
*/
public interface ICourseService extends IBaseService<Course> {

	/**
	 * 检查课程编号和课程名字是否已经存在
	 * @param courseid
	 * @param coursename
	 * @return 如果返回true，代表不存在，如果返回false，代表课程编号或课程名字已经存在
	 */
	boolean checkcourseidAndcoursename(String courseid, String coursename);
	
	
	/**
	 * 检查课程编号和课程名字是否已经存在
	 * @param id 学生ID
	 * @param courseid 
	 * @param coursename
	 * @return 如果返回true，代表不存在，如果返回false，代表课程编号或课程名字已经存在
	 */
	boolean checkcourseidAndcoursename(int id, String courseid, String coursename);

	/**
	 * 批量删除
	 * @param ids 删除课程id的数组
	 */
	void deleteBatch(String[] ids);
	
	/**
	 * 检查课程编号是否已经存在
	 * @param courseid
	 * @return 如果返回true，代表不存在，如果返回false，代表课程编号已经存在
	 */
	boolean checkcourseid(String courseid);
	
	/**
	 * 检查教师名字是否存在
	 * @param courseteacher
	 * @return 如果返回true，代表不存在，如果返回false，代表课程编号或课程名字已经存在
	 */
	public boolean checkcourseteacher(String courseteacher) ;
}
