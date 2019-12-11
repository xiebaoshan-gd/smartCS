package com.example.demo.entity;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Role {
	@Id
    private Integer id;

    private String name;

    private String remark;

}