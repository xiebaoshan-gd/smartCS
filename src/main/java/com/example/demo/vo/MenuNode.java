package com.example.demo.vo;

import java.util.List;

import lombok.Data;


/*
	该实体类用于封装首页菜单的数据
*/
@Data
public class MenuNode {
	private String icon;
	private Integer menuid;
	private String menuname;
	private String url;
	private List<MenuNode> menus;
}
