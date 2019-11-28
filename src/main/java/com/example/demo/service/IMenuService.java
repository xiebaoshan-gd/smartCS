package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Menu;
import com.example.demo.vo.MenuNode;
import com.example.demo.vo.TreeNode;



public interface IMenuService extends IBaseService<Menu> {

	/**
	 * 获取菜单数据，并封装成MenuNode对象
	 * @return
	 */
	MenuNode getMenuNode(int userId);

	/**
	 * 查询菜单数据，并且把菜单数据转换TreeNode集合
	 * @return
	 */
	List<TreeNode> getMenuTreeNode(int roleId);

	/**
	 * 查询菜单，并把菜单数据封装成TreeNode对象。
	 * @return
	 */
	TreeNode getMenuTreeNode();

	/**
	 * 删除菜单
	 * @param id 菜单ID
	 */
	void deleteMenu(String id);

}
