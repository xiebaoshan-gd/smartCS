package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

import tk.mybatis.mapper.common.Mapper;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements
	UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Mapper<User> getMapper() {
		return this.userMapper;
	}
	
	@Override
	public User findUser(String userName, String userPass) {
		return userMapper.findUser(userName, userPass);
	}

	@Override
	public void updateUserPass(Integer id, String pass) {
		userMapper.updatePwd(id, pass);
	}

	@Override
	public void addUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public void changeUserPass(int id) {
		userMapper.updatePwd(id, "12345");
	}

	@Override
	public void saveUserRoles(String userId, String[] roleIds) {
		// 先清空用户的角色
		userMapper.deleteUserRoles(Integer.parseInt(userId));
		// 给用户添加新的角色
		for (String roleId : roleIds) {
			userMapper.saveUserRole(Integer.parseInt(userId), Integer.parseInt(roleId));
		}
	}
}
