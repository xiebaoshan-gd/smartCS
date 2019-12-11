package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class SmartDemo0Application {

	public static void main(String[] args) {
		SpringApplication.run(SmartDemo0Application.class, args);
	}

}
