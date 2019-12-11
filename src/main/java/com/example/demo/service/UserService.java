package com.example.demo.service;

import com.example.demo.entity.User;



public interface UserService extends IBaseService<User> {
	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param userPass
	 * @return
	 */
	User findUser(String userName, String userPass);

	/**
	 * 修改用户密码
	 * @param id 用户ID
	 * @param pass 新密码
	 */
	void updateUserPass(Integer id, String pass);

	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 重置用户密码为12345
	 * @param id 用户ID
	 */
	void changeUserPass(int id);

	/**
	 * 给用户分配角色
	 * @param userId 用户ID
	 * @param roleIds 角色ID
	 */
	void saveUserRoles(String userId, String[] roleIds);
	
	
}
