package com.example.demo.service;

import java.util.List;


import tk.mybatis.mapper.entity.Example;

/*
	通用的业务接口
*/
public interface IBaseService<T> {
	
	/**
	 * 分页查询
	 * @param page 第几页
	 * @param rows 每页结果数
	 * @return
	 */
	List<T> findByPage(int page, int rows);
	
	/**
	 * 分页查询
	 * @param example 封装查询条件对象
	 * @param page 第几页
	 * @param rows 每页结果数
	 * @return
	 */
	List<T> findByPage(Example example, int page, int rows);
	
	/**
	 * 添加操作
	 * @param t
	 */
	void add(T t);
	
	/**
	 * 修改操作
	 * @param t
	 */
	void update(T t);
	
	/**
	 * 根据ID执行删除操作
	 * @param id
	 */
	void delete(int id);
	
	
	/**
	 * 查询所有记录
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 根据ID执行查询
	 * @param id
	 * @return 返回单条记录
	 */
	T findById(int id);
	
}
