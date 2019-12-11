package com.example.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Teacher;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.service.ITeacherService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;



@Service
@Transactional(readOnly=true)
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements ITeacherService {
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public Mapper<Teacher> getMapper() {
		return this.teacherMapper;
	}

	@Override
	public boolean checkTeachernameAndTeacherNum(String teachernum, String teachername) {
		Example example = new Example(Teacher.class);
		Criteria c = example.createCriteria();
		c.orEqualTo("teachernum", teachernum);
		c.orEqualTo("teachername", teachername);
		List<Teacher> list = teacherMapper.selectByExample(example);
		return list.size() == 0; 
	}
	
	@Override
	public boolean checkTeachernameAndTeacherNum(int id, String teachernum, String teachername) {
		Example example = new Example(Teacher.class);
		Criteria c = example.createCriteria();
		Criteria c2 = example.createCriteria();
		c.andNotEqualTo("id", id);
		
		c2.orEqualTo("teachernum", teachernum);
		c.orEqualTo("teachername", teachername);
		example.and(c2);
		
		List<Teacher> list = teacherMapper.selectByExample(example);
		return list.size() == 0; 
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteBatch(String[] ids) {
		for (String id : ids) {
			delete(Integer.parseInt(id));
		}
	}

	@Override
	public boolean checkTeachername(String teachername) {
		Example example = new Example(Teacher.class);
		Criteria c = example.createCriteria();
//		c.orEqualTo("teachernum", teachernum);
		c.orEqualTo("teachername", teachername);
		List<Teacher> list = teacherMapper.selectByExample(example);
		return list.size() == 0; 
	}

}
