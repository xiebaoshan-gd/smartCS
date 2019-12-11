package com.example.demo.entity;

import javax.persistence.Id;

import lombok.Data;

/*
	教师的实体，对应teacher表
*/
@Data
public class Teacher {
	@Id
	private Integer id; // 对应表的id字段
	private String teachername; // 对应teacher表的name字段
	private String teachernum; // 对应teacher表的num字段
	private String sex; // 对应teacher表的sex字段
	private String birth; // 对应teacher表的birthday字段
	private String starttime;
	private String address;
	private String position;
	
}
