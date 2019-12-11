package com.example.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Course;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.service.ICourseService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;



@Service
@Transactional(readOnly=true)
public class CourseServiceImpl extends BaseServiceImpl<Course> implements ICourseService {
	@Autowired
	private CourseMapper courseMapper;
	private TeacherServiceImpl teacherServiceImpl;
	private TeacherMapper teacherMapper;
	
	@Override
	public Mapper<Course> getMapper() {
		return this.courseMapper;
	}

	/*
	 * 判断课程编号和课程名字是否存在
	 */
	@Override
	public boolean checkcourseidAndcoursename(String courseid, String coursename) {
		Example example = new Example(Course.class);
		Criteria c = example.createCriteria();
		c.orEqualTo("courseid", courseid);
		c.orEqualTo("coursename", coursename);
		List<Course> list = courseMapper.selectByExample(example);
		return list.size() == 0; 
	}
	/*
	 * 判断课程编号是否存在
	 */
	@Override
	public boolean checkcourseid(String courseid) {
		Example example = new Example(Course.class);
		Criteria c = example.createCriteria();
		c.orEqualTo("courseid", courseid);
		List<Course> list = courseMapper.selectByExample(example);
		return list.size() == 0; 
	}
	
	/*
	 * 判断课程编号和课程名字是否存在
	 */
	@Override
	public boolean checkcourseidAndcoursename(int id, String courseid, String coursename){
		Example example = new Example(Course.class);
		Criteria c = example.createCriteria();
		Criteria c2 = example.createCriteria();
		c.andNotEqualTo("id", id);
		
		c2.orEqualTo("courseid", courseid);
		c2.orEqualTo("coursename", coursename);
		
		example.and(c2);
		
		List<Course> list = courseMapper.selectByExample(example);
		return list.size() == 0; 
	}
	

	/*
	 * 判断任课教师是否存在
	 */
	@Override
	public boolean checkcourseteacher(String courseteacher) {
		Example example = new Example(Teacher.class);
		Criteria c = example.createCriteria();
//		c.orEqualTo("teachernum", teachernum);
		c.andEqualTo("teachername", courseteacher);
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

}
