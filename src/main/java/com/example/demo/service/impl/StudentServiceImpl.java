package com.example.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.IStudentService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;



@Service
@Transactional(readOnly=true)
public class StudentServiceImpl extends BaseServiceImpl<Student> implements IStudentService {
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public Mapper<Student> getMapper() {
		return this.studentMapper;
	}

	@Override
	public boolean checkNumAndRfid(String num, String rfid) {
		Example example = new Example(Student.class);
		Criteria c = example.createCriteria();
		c.orEqualTo("num", num);
		c.orEqualTo("rfid", rfid);
		List<Student> list = studentMapper.selectByExample(example);
		return list.size() == 0; 
	}
	
	@Override
	public boolean checkNumAndRfid(int id, String num, String rfid) {
		Example example = new Example(Student.class);
		Criteria c = example.createCriteria();
		Criteria c2 = example.createCriteria();
		c.andNotEqualTo("id", id);
		
		c2.orEqualTo("num", num);
		c2.orEqualTo("rfid", rfid);
		
		example.and(c2);
		
		List<Student> list = studentMapper.selectByExample(example);
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
