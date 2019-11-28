package com.example.demo.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.example.demo.entity.Student;
import com.example.demo.service.IStudentService;
import com.example.demo.utils.Pagination;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {
	@Autowired
	private IStudentService studentService;
	
	/*@RequestMapping(value="/getData.do", produces="application/json;charset=utf-8")
	public List<Student> getData() {
		return studentService.findAll();
	}*/
	
	@RequestMapping(value="/deleteAll.do", produces="application/json;charset=utf-8")
	public Map<String, Object> deleteAllStudent(String ids) {
		try {
			String[] idArr = ids.split(",");
			studentService.deleteBatch(idArr);
			return ajaxReturn(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败！");
	}
	
	@RequestMapping(value="/delete.do", produces="application/json;charset=utf-8")
	public Map<String, Object> deleteStudent(String id) {
		try {
			studentService.delete(Integer.parseInt(id));
			return ajaxReturn(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败！");
	}
	
	@RequestMapping(value="/update.do", produces="application/json;charset=utf-8")
	public Map<String, Object> updateStudent(Student student) {
		System.out.println("student = " + student);
		try {
			// 查询学号和rfid是否存在，如果存在就执行保存操作
			boolean isOk = studentService.checkNumAndRfid(student.getId(), student.getNum(), student.getRfid());
			System.out.println("student = " + student);
			if (isOk) {
				studentService.update(student);
				
				return ajaxReturn(true, "修改成功");
			} else {
				return ajaxReturn(false, "学号或rfid已经存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败");
	}
	
	@RequestMapping(value="/add.do", produces="application/json;charset=utf-8")
	public Map<String, Object> addStudent(Student student) {
		try {
			// 查询学号和rfid是否存在，如果存在就执行保存操作
			boolean isOk = studentService.checkNumAndRfid(student.getNum(), student.getRfid());
			if (isOk) {
				studentService.add(student);

				return ajaxReturn(true, "添加成功");
			} else {
				return ajaxReturn(false, "学号或rfid已经存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败");
	}
	
	@RequestMapping(value="/getData.do", produces="application/json;charset=utf-8")
	public Pagination<Student> getData(String name, String num, String page, String rows) {
		// 动态拼接查询条件
		Example example = new Example(Student.class);
		// 通过example对象创建一个Criteria对象
		Criteria c = example.createCriteria();
		if (name != null && !"".equals(name)) {
			c.andLike("name", "%" + name + "%");
		}
		if (num != null && !"".equals(num)) {
			c.andEqualTo("num", num);
		}
		// 排序条件
		example.setOrderByClause("state asc");
		// 调用分页查询方法
		List list = studentService.findByPage(example
				, Integer.parseInt(page), Integer.parseInt(rows));
		// 创建PageInfo对象，该对象自动计算分页的信息
		PageInfo pageInfo = new PageInfo(list);
		Pagination<Student> pagination = new Pagination();
		pagination.setTotal(pageInfo.getTotal());
		pagination.setRows(list);
		return pagination;
	}
	
}
