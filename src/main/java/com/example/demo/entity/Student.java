package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Student implements Serializable {
	@Id
    private Integer id;

    private String name;

    private String num;

    private String rfid;

    private Integer state;

}