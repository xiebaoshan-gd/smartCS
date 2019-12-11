package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Teacher;

import tk.mybatis.mapper.common.Mapper;

/*
相当于三层架构的DAO
*/
@Repository
public interface TeacherMapper extends Mapper<Teacher> {

}