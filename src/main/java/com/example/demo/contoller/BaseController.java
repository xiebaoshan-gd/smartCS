package com.example.demo.contoller;

import java.util.HashMap;
import java.util.Map;


/*
	通用的Controller, 为其他Controller提供了一些通用公共方法。
*/
public class BaseController {

	/**
	 * 自动构建返回的Map集合
	 * @param status 响应状态
	 * @param message 响应消息
	 * @return
	 */
	protected Map<String, Object> ajaxReturn(boolean status, String message) {
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", status);
		responseMap.put("message", message);
		return responseMap;
	}
	
}
