package com.example.demo.service;

import com.example.demo.entity.Student;

/*
	业务接口
*/
public interface IStudentService extends IBaseService<Student> {

	/**
	 * 检查学号和rfid是否已经存在
	 * @param num
	 * @param rfid
	 * @return 如果返回true，代表不存在，如果返回false，代表学号或rfid已经存在
	 */
	boolean checkNumAndRfid(String num, String rfid);
	
	
	/**
	 * 检查学号和rfid是否已经存在
	 * @param id 学生ID
	 * @param num 
	 * @param rfid
	 * @return 如果返回true，代表不存在，如果返回false，代表学号或rfid已经存在
	 */
	boolean checkNumAndRfid(int id, String num, String rfid);

	/**
	 * 批量删除
	 * @param ids 删除学生ID的数组
	 */
	void deleteBatch(String[] ids);
	
}
