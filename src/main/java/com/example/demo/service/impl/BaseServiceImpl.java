package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.service.IBaseService;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/*
	通过业务接口的实现类
*/
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	// 该抽象方法返回一个Mapper对象。该Mapper对象的具体类型是由该方法的返回值来决定的。
	public abstract Mapper<T> getMapper();
	
	@Override
	public List<T> findByPage(int page, int rows) {
		// 设置分页
		PageHelper.startPage(page, rows);
		// 执行查询操作
		return findAll();
	}
	
	@Override
	public List<T> findByPage(Example example, int page, int rows) {
		// 设置分页
		PageHelper.startPage(page, rows);
		// 执行高级条件查询
		return getMapper().selectByExample(example);
	}

	@Override
	public void add(T t) {
		getMapper().insertSelective(t);
	}

	@Override
	public void update(T t) {
		getMapper().updateByPrimaryKeySelective(t);
	}

	@Override
	public void delete(int id) {
		getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public List<T> findAll() {
		return getMapper().selectAll();
	}

	@Override
	public T findById(int id) {
		return getMapper().selectByPrimaryKey(id);
	}
	
}
