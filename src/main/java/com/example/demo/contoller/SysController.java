package com.example.demo.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping("/getCookie.do")
	public String getCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			String cookieName = c.getName();
			String cookieValue = c.getValue();
			if ("user".equals(cookieName)) {
				return cookieValue;
			}
		}
		return "";
	}

	@RequestMapping(value="/login.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> login(String username, String userpass, boolean rem
			, HttpSession session, HttpServletResponse response) {
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

		// 定义一个Map集合，用于封装响应的结果
		Map<String, Object> responseMap = new HashMap<>();
		// 根据用户名和密码查询数据库
		// 如果数据库中有相关的用户记录，代表登录成功，否则登录失败
		User user = userService.findUser(username, userpass);
		if (user != null) {
			// 登录成功后，把用户信息保存在session对象中。
			session.setAttribute("user", user);
			responseMap.put("status", true);
			responseMap.put("message", "");
		} else {
			responseMap.put("status", false);
			responseMap.put("message", "用户名或密码不正确");
		}
		// 最后向浏览器返回登录的结果
		// {status: false, message: '用户名或密码不正确'}
		return responseMap;
	}
	
	// 获取登录用户名
	@RequestMapping(value="/getName.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> getName(HttpSession session) {
		Map<String, Object> responseMap = new HashMap<>();
		Object o = session.getAttribute("user");
		// 判断o是否为空，如果为空代表当前用户没有登录
		if (o == null) {
			responseMap.put("status", false);
			responseMap.put("message", "请先登录");
		} else {
			User user = (User) o;
			responseMap.put("status", true);
			responseMap.put("message", user.getRealname());
		}
		return responseMap;
	}
	
	// 注销登录
	@RequestMapping(value="/logout.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> logout(HttpSession session) {
		// 清空session
		session.invalidate();
		return ajaxReturn(true, "");
	}
	
	// 修改密码
	@RequestMapping(value="/changePass.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> changePwd(String pass, HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			userService.updateUserPass(user.getId(), pass);
			return ajaxReturn(true, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "修改密码失败！");
	}

}
