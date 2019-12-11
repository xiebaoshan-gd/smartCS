package com.example.demo.contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Menu;
import com.example.demo.entity.User;
import com.example.demo.service.IMenuService;
import com.example.demo.vo.MenuNode;
import com.example.demo.vo.TreeNode;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping(value="/getData.do", produces={"application/json;charset=utf-8"})
	public MenuNode getData() {
		//User user = (User) session.getAttribute("user");
		
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		
		return menuService.getMenuNode(user.getId());
	}
	
	@RequestMapping(value="/getTreeData.do", produces={"application/json;charset=utf-8"})
	public List<TreeNode> getTreeData(String roleId) {
		
		return menuService.getMenuTreeNode(Integer.parseInt(roleId));
	}
	
	@RequestMapping(value="/getMenus.do", produces={"application/json;charset=utf-8"})
	public List<TreeNode> getMenus() {
		TreeNode treeNode = menuService.getMenuTreeNode();
		List<TreeNode> list = new ArrayList<>();
		list.add(treeNode);
		return list;
	}
	
	@RequestMapping(value="/deleteMenu.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> deleteMenu(String id) {
		try {
			menuService.deleteMenu(id);
			return ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败");
	}
	
	@RequestMapping(value="/updateMenu.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> updateMenu(Menu menu) {
		try {
			menuService.update(menu);
			return ajaxReturn(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "修改失败");
	}
	
	@RequestMapping(value="/addMenu.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> addMenu(Menu menu) {
		try {
			menuService.add(menu);
			return ajaxReturn(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败");
	}
	
}
