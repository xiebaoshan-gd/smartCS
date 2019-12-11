package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

	@Select("select * from user where userName = #{userName} and userPass = #{userPass}")
	User findUser(@Param("userName")String userName, @Param("userPass")String userPass);

	@Update("update user set userPass = password(#{pass}) where id = #{id}")
	void updatePwd(@Param("id")Integer id, @Param("pass")String pass);
	
	@Insert("insert into user(userName, userPass, realName, gender, phone) "
			+ "values(#{userName}, password(#{userPass}), #{realName}, #{gender}, #{phone})")
	void insertUser(User user);

	@Delete("delete from userrole where userId = #{userId}")
	void deleteUserRoles(@Param("userId")int userId);

	@Insert("insert into userrole(userId, roleId) values(#{userId}, #{roleId})")
	void saveUserRole(@Param("userId")int userId, @Param("roleId")int roleId);
	
}