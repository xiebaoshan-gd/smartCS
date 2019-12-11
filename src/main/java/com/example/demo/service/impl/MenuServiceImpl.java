package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Menu;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.IMenuService;
import com.example.demo.vo.MenuNode;
import com.example.demo.vo.TreeNode;

import tk.mybatis.mapper.common.Mapper;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Mapper<Menu> getMapper() {
		return this.menuMapper;
	}
	
	@Override
	public List<TreeNode> getMenuTreeNode(int roleId) {
		// 根据roleId把角色拥有的菜单id查询出来
		List<Integer> roleMenuIds = menuMapper.getRoleMenuIds(roleId);
		
		// 获取顶级菜单
		Menu menu = new Menu();
		menu.setPid(0);
		Menu rootMenu = menuMapper.selectOne(menu);
		
		List<TreeNode> treeNodes_1 = new ArrayList<>();
		
		// 获取一级菜单
		menu.setPid(rootMenu.getId());
		List<Menu> menus_1 = menuMapper.select(menu);
		for (Menu menu_1 : menus_1) {
			TreeNode treeNode_1 = new TreeNode();
			treeNode_1.setId(menu_1.getId());
			treeNode_1.setText(menu_1.getName());
			
			// 判断当前角色是否拥有该菜单权限，如果有，则设置checked属性为true。
			if (roleMenuIds.contains(menu_1.getId())) {
				treeNode_1.setChecked(true);
			}
			
			List<TreeNode> treeNodes_2 = new ArrayList<>();
			
			// 获取一级菜单下的二级菜单
			menu.setPid(menu_1.getId());
			List<Menu> menus_2 = menuMapper.select(menu);
			for (Menu menu_2 : menus_2) {
				TreeNode treeNode_2 = new TreeNode();
				treeNode_2.setId(menu_2.getId());
				treeNode_2.setText(menu_2.getName());
				
				// 判断当前角色是否拥有该菜单权限，如果有，则设置checked属性为true。
				if (roleMenuIds.contains(menu_2.getId())) {
					treeNode_2.setChecked(true);
				}
				
				treeNodes_2.add(treeNode_2);
			}
			treeNode_1.setChildren(treeNodes_2);
			treeNodes_1.add(treeNode_1);
		}
		return treeNodes_1;
	}

	@Override
	public MenuNode getMenuNode(int userId) {
		// 获取用户所拥有的菜单ID
		List<Integer> menuIds = menuMapper.getUserMenuIds(userId);
		
		// 获取顶级菜单（pid=0）
		Menu menu = new Menu();
		menu.setPid(0);
		Menu rootMenu = menuMapper.selectOne(menu);
		// 创建MenuNode对象
		MenuNode menuNode = new MenuNode();
		menuNode.setIcon("icon-sys");
		menuNode.setMenuid(rootMenu.getId());
		menuNode.setMenuname(rootMenu.getName());
		menuNode.setUrl(rootMenu.getUrl());
		// 该list集合用于封装所有子菜单节点
		List<MenuNode> childMenuNodes = new ArrayList<>();
		// 获取顶级菜单下的子菜单(一级菜单)
		menu.setPid(rootMenu.getId());
		List<Menu> childMenus = menuMapper.select(menu);
		for (Menu childMenu : childMenus) {
			if (menuIds.contains(childMenu.getId())) {
				MenuNode node = new MenuNode();
				node.setIcon("icon-sys");
				node.setMenuid(childMenu.getId());
				node.setMenuname(childMenu.getName());
				node.setUrl(childMenu.getUrl());
				// 该list集合用于封装所有二级菜单节点
				List<MenuNode> grandChildMenuNodes = new ArrayList<>();
				// 获取二级菜单
				menu.setPid(childMenu.getId());
				List<Menu> grandChildMenus = menuMapper.select(menu);
				for (Menu grandChildMenu : grandChildMenus) {
					MenuNode grandMenuNode = new MenuNode();
					grandMenuNode.setIcon("icon-sys");
					grandMenuNode.setMenuid(grandChildMenu.getId());
					grandMenuNode.setMenuname(grandChildMenu.getName());
					grandMenuNode.setUrl(grandChildMenu.getUrl());
					grandChildMenuNodes.add(grandMenuNode);
				}
				node.setMenus(grandChildMenuNodes);
				childMenuNodes.add(node);
			} else {
				// 该list集合用于封装所有二级菜单节点
				List<MenuNode> grandChildMenuNodes = new ArrayList<>();
				// 获取二级菜单
				menu.setPid(childMenu.getId());
				List<Menu> grandChildMenus = menuMapper.select(menu);
				for (Menu grandChildMenu : grandChildMenus) {
					// 判断用户是否拥有该二级菜单，如果有才添加到grandChildMenuNodes集合中
					if (menuIds.contains(grandChildMenu.getId())) {
						MenuNode grandMenuNode = new MenuNode();
						grandMenuNode.setIcon("icon-sys");
						grandMenuNode.setMenuid(grandChildMenu.getId());
						grandMenuNode.setMenuname(grandChildMenu.getName());
						grandMenuNode.setUrl(grandChildMenu.getUrl());
						grandChildMenuNodes.add(grandMenuNode);
					}
				}
				// 判断集合大小是否大于0，如果大于0代表用户拥有该一级菜单下的某些二级菜单
				if (grandChildMenuNodes.size() > 0) {
					MenuNode node = new MenuNode();
					node.setIcon("icon-sys");
					node.setMenuid(childMenu.getId());
					node.setMenuname(childMenu.getName());
					node.setUrl(childMenu.getUrl());
					node.setMenus(grandChildMenuNodes);
					childMenuNodes.add(node);
				}
			}
		}
		// 添加子菜单节点
		menuNode.setMenus(childMenuNodes);
		return menuNode;
	}

	@Override
	public TreeNode getMenuTreeNode() {
		// 查询顶级菜单
		Menu menu = new Menu();
		menu.setPid(0);
		Menu root = menuMapper.selectOne(menu);
		// 构建根节点
		TreeNode treeNode = new TreeNode();
		treeNode.setId(root.getId());
		treeNode.setText(root.getName());
		treeNode.setPid(root.getPid());
		// 该集合用于保存一级菜单的节点
		List<TreeNode> treeNodes_1 = new ArrayList<>();
		// 遍历顶级菜单下的一级菜单
		menu.setPid(root.getId());
		List<Menu> menus_1 = menuMapper.select(menu);
		for (Menu menu_1 : menus_1) {
			TreeNode node_1 = new TreeNode();
			node_1.setId(menu_1.getId());
			node_1.setText(menu_1.getName());
			node_1.setPid(menu_1.getPid());
			node_1.setRemark(menu_1.getRemark());
			treeNodes_1.add(node_1);
			// 该集合用于保存二级菜单的节点
			List<TreeNode> treeNodes_2 = new ArrayList<>();
			// 查询二级菜单
			menu.setPid(menu_1.getId());
			List<Menu> menus_2 = menuMapper.select(menu);
			for (Menu menu_2 : menus_2) {
				TreeNode node_2 = new TreeNode();
				node_2.setId(menu_2.getId());
				node_2.setText(menu_2.getName());
				node_2.setUrl(menu_2.getUrl());
				node_2.setPid(menu_2.getPid());
				node_2.setRemark(menu_2.getRemark());
				treeNodes_2.add(node_2);
			}
			node_1.setChildren(treeNodes_2);
		}
		// 把一级菜单节点的集合添加到根节点中
		treeNode.setChildren(treeNodes_1);
		return treeNode;
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteMenu(String id) {
		// 删除子菜单
		Menu menu = new Menu();
		menu.setPid(Integer.parseInt(id));
		menuMapper.delete(menu);
		// 删除当前菜单
		menuMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}

}
