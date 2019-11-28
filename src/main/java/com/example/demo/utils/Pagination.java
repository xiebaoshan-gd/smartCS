package com.example.demo.utils;

import java.util.List;

import lombok.Data;

/*
	分页工具类，用于封装分页的数据
*/
@Data
public class Pagination<T> {
	private long total;
	private List<T> rows;

}
