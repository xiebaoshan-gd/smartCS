package com.example.demo.entity;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Menu {

	@Id
    private Integer id;

    private String name;

    private String url;

    private Integer pid;

    private String remark;

}