package com.example.demo.contoller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getData.do", produces={"application/json;charset=utf-8"})
	public List<User> getData() {
		return userService.findAll();
	}
	
	@RequestMapping(value="/updateUser.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> updateUser(User user) {
		try {
			userService.update(user);
			return ajaxReturn(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "修改失败");
	}
	
	@RequestMapping(value="/saveUser.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> saveUser(User user) {
		try {
			user.setUserPass("123456"); // 设置默认密码
			userService.addUser(user);
			return ajaxReturn(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败");
	}
	
	@RequestMapping(value="/deleteUser.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> deleteUser(String id) {
		try {
			userService.delete(Integer.parseInt(id));
			return ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败");
	}
	
	@RequestMapping(value="/resetUserPass.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> resetUserPass(String id) {
		try {
			userService.changeUserPass(Integer.parseInt(id));
			return ajaxReturn(true, "密码重置成功，新密码为12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "密码重置失败");
	}
	
	@RequestMapping(value="/saveUserRole.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> saveUserRole(String userId, String roleIds) {
		try {
			userService.saveUserRoles(userId, roleIds.split(","));
			return ajaxReturn(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败");
	}
	
}
