package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class User {

	@Id
	private Integer id; // 用户编号
	@Column(name="userName")
	private String userName; // 用户名
	@Column(name="userPass")
	private String userPass; // 密码
	@Column(name="realName")
	private String realName; // 姓名
	private Boolean gender; // 性别 false代表男 true代表女
	private String phone; // 联系电话

}