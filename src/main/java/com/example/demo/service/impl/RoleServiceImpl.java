package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.IRoleService;
import com.example.demo.vo.TreeNode;

import tk.mybatis.mapper.common.Mapper;


@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		IRoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Mapper<Role> getMapper() {
		return this.roleMapper;
	}

	@Override
	@Transactional(readOnly=false)
	public void saveRoleMenu(String roleId, String[] menuIds) {
		// 把角色原有的菜单权限删除
		//roleMapper.deleteRoleMenu(Integer.parseInt(roleId));
		roleMapper.deleteRoleMenu(Integer.parseInt(roleId));
		// 重新添加新的菜单权限
		for (String menuId : menuIds) {
			roleMapper.addRoleMenu(Integer.parseInt(roleId), Integer.parseInt(menuId));
		}
	}

	@Override
	public List<TreeNode> getRoles(int userId) {
		// 查询用户拥有的角色ID
		List<Integer> roleIds = roleMapper.getUserRoleIds(userId);
		
		List<TreeNode> nodes = new ArrayList<>();
		// 查询所有角色
		List<Role> roles = roleMapper.selectAll();
		for (Role role : roles) {
			TreeNode tn = new TreeNode();
			tn.setId(role.getId());
			tn.setText(role.getName());
			
			if (roleIds.contains(role.getId())) {
				tn.setChecked(true);
			}
			
			nodes.add(tn);
		}
		return nodes;
	}

}
