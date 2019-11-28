package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public User findUser(String userName, String userPass) {
		return userMapper.findUser(userName, userPass);
	}

	public void updateUserPass(Integer id, String pass) {
		userMapper.updatePwd(id, pass);
		
	}
}
