package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Role;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface RoleMapper extends Mapper<Role> {
	@Delete("delete from rolemenu where roleId = #{roleId}")
	void deleteRoleMenu(@Param("roleId")int roleId);
	
	@Insert("insert into rolemenu values(#{roleId}, #{menuId})")
	void addRoleMenu(@Param("roleId")int roleId, @Param("menuId")int menuId);

	@Select("select roleId from userrole where userId = #{userId}")
	List<Integer> getUserRoleIds(@Param("userId")int userId);
}