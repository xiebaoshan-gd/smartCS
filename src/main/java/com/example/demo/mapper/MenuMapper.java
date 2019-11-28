package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Menu;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MenuMapper extends Mapper<Menu> {
	/*
	 * 
	 * @Select("select menuId from rolemenu where roleId = #{roleId}") List<Integer>
	 * getRoleMenuIds(@Param("roleId")int roleId);
	 * 
	 * @Select("select distinct menuId from rolemenu where roleId in (select roleId from userrole where userId = #{userId})"
	 * ) List<Integer> getUserMenuIds(@Param("userId")int userId);
	 */
	
	@Select("select menuId from rolemenu where roleId = #{roleId}")
	List<Integer> getRoleMenuIds(@Param("roleId")int roleId);
	
	@Select("select distinct menuId from rolemenu where roleId in (select roleId from userrole where userId = #{userId})")
	List<Integer> getUserMenuIds(@Param("userId")int userId);

}