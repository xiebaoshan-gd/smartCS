package com.example.demo.mapper;

import java.io.Console;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    @Select("select * from user where userName = #{userName} and userPass = #{userPass}")
	User findUser(@Param("userName")String userName, @Param("userPass")String userPass);

    @Update("update user set userPass = #{pass} where id = #{id}")
    void updatePwd(@Param("id")Integer id, @Param("pass")String pass);

}