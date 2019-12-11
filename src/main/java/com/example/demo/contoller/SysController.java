package com.example.demo.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/*
	该控制器用于处理系统相关的请求
*/
@RestController
@RequestMapping("/sys")
public class SysController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/changePass.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> changePwd(String pass) {
		try {
//			User user = (User) session.getAttribute("user");
			
			User user = (User) SecurityUtils.getSubject().getPrincipal();
			userService.updateUserPass(user.getId(), pass);
			return ajaxReturn(true, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "修改密码失败！");
	}
	
	@RequestMapping(value="/logout.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> logout() {
		// 清空session
//		session.invalidate();
		
		// 注销Subject
		SecurityUtils.getSubject().logout();
		
		return ajaxReturn(true, "");
	}
	
	@RequestMapping(value="/getName.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> getName() {
		// Object o = session.getAttribute("user");
		
		Object o = SecurityUtils.getSubject().getPrincipal();
		
		// 判断o是否为空，如果为空代表当前用户没有登录
		if (o == null) {
			return ajaxReturn(false, "请先登录");
		} else {
			User user = (User) o;
			return ajaxReturn(true, user.getRealName()); 
		}
	}
	
	
	@RequestMapping("/getCookie.do")
	public String getCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				if ("user".equals(cookieName)) {
					System.out.println(cookieName + "=" + cookieValue);
					return cookieValue;
				}
			}
		}
		return "";
	}

	@RequestMapping(value="/login.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> login(String username, String userpass
			, boolean rem, HttpServletResponse response) {
		// 如果勾选了记住我，那么就需要把用户名和密码保存在cookie
		if (rem) {
			Cookie c = new Cookie("user", username + "#" + userpass);
			c.setMaxAge(60 * 60 * 24); // 设置cookie有效时间，以秒为单位
			response.addCookie(c);
		} 
		// 如果没有勾选记住我，那么就把用户名和密码从cookie中删除
		else {
			Cookie c = new Cookie("user", "");
			c.setMaxAge(0); // 设置cookie有效时间，以秒为单位
			response.addCookie(c);
		}
		
		// 使用shiro实现身份认证
		UsernamePasswordToken token = new UsernamePasswordToken(username, userpass);
		// 获取主体Subject
		Subject subject = SecurityUtils.getSubject();
		// 调用subject对象login方法执行登录认证
		try {
			subject.login(token);
			return ajaxReturn(true, "登录成功");
		} catch (AuthenticationException e) {
			// 认证失败
			e.printStackTrace();
		}
		return ajaxReturn(false, "用户名或密码不正确");
	}

}
