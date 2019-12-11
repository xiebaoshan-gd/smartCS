package com.example.demo.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/*
	执行认证和权限控制的realm
*/
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	// 权限控制
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		return null;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		// 登录用户名
		String userName = t.getUsername();
		// 登录密码
		String userPass = new String(t.getPassword());
		// 根据用户名和密码查询用户信息
		User user = userService.findUser(userName, userPass);
		if (user != null) {
			// 登录成功
			return new SimpleAuthenticationInfo(user, userPass, userName);
		} 
		// 登录失败
		System.out.println("登录失败");
		return null;
		
	}

}
