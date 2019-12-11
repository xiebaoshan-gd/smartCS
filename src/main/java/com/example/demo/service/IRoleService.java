package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Role;
import com.example.demo.vo.TreeNode;



public interface IRoleService extends IBaseService<Role> {
	/**
	 * 给角色授权
	 * @param roleId 角色ID
	 * @param menuIds 菜单ID
	 */
	void saveRoleMenu(String roleId, String[] menuIds);

	/**
	 * 获取用户角色
	 * @param userId 
	 * @return
	 */
	List<TreeNode> getRoles(int userId);
}
