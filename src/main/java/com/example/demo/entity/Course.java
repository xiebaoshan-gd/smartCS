package com.example.demo.entity;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Course {
	@Id
    private Integer id;

    private String coursename;
    
    private String courseid;

    private String courseteacher;

    private String coursecredit;

	
}