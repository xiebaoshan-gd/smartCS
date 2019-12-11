package com.example.demo.contoller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.service.IRoleService;
import com.example.demo.vo.TreeNode;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value="/getData.do", produces={"application/json;charset=utf-8"})
	public List<Role> getData() {
		return roleService.findAll();
	}
	
	@RequestMapping(value="/deleteRole.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> deleteRole(String id) {
		try {
			roleService.delete(Integer.parseInt(id));
			return ajaxReturn(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败");
	}
	
	@RequestMapping(value="/updateRole.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> updateRole(Role role) {
		try {
			roleService.update(role);
			return ajaxReturn(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "修改失败");
	}
	
	@RequestMapping(value="/addRole.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> addRole(Role role) {
		try {
			roleService.add(role);
			return ajaxReturn(true, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "添加失败");
	}
	
	@RequestMapping(value="/saveRoleMenu.do", produces={"application/json;charset=utf-8"})
	public Map<String, Object> saveRoleMenu(String roleId, String menuIds) {
		try {
			roleService.saveRoleMenu(roleId, menuIds.split(","));
			return ajaxReturn(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(true, "保存失败");
	}
	
	@RequestMapping(value="/getTreeData.do", produces={"application/json;charset=utf-8"})
	public List<TreeNode> getTreeData(String userId) {
		return roleService.getRoles(Integer.parseInt(userId));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
