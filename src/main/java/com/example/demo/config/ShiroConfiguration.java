package com.example.demo.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.auth.ShiroRealm;

/*
	Shiro配置类
*/
@Configuration
public class ShiroConfiguration {
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		// 配置资源访问的规则
		Map<String, String> map = new LinkedHashMap<>(); // 注意：不要使用HashMap
		// 不需要认证的资源
		map.put("/login.html", "anon");
		map.put("/adminjs/**", "anon");
		map.put("/css/**", "anon");
		map.put("/easyui/**", "anon");
		map.put("/images/**", "anon");
		map.put("/sys/login.do", "anon");
		map.put("/sys/getCookie.do", "anon");
		// 需要认证的资源
		map.put("/**", "authc");
		factoryBean.setFilterChainDefinitionMap(map);
		// 认证失败后跳转的页面
		factoryBean.setLoginUrl("/login.html");
		return factoryBean;
	}
	
	@Bean
	public ShiroRealm shiroRealm() {
		return new ShiroRealm();
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		return securityManager;
	}

}
