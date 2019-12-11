package com.example.demo.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Teacher;
import com.example.demo.service.ITeacherService;
import com.example.demo.utils.Pagination;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
	@Autowired
	private ITeacherService teacherService;
	
	
	@RequestMapping(value="/deleteAll.do", produces="application/json;charset=utf-8")
	public Map<String, Object> deleteAllTeacher(String ids) {
		try {
			String[] idArr = ids.split(",");
			teacherService.deleteBatch(idArr);
			return ajaxReturn(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败！");
	}
	
	@RequestMapping(value="/delete.do", produces="application/json;charset=utf-8")
	public Map<String, Object> deleteTeacher(String id) {
		try {
			teacherService.delete(Integer.parseInt(id));
			return ajaxReturn(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败！");
	}
	
	@RequestMapping(value="/update.do", produces="application/json;charset=utf-8")
	public Map<String, Object> updateTeacher(Teacher teacher) {
//		System.out.println("teacher = " + teacher);
//		try {
//			// 查询教师编号和教师名字是否存在，如果存在就执行保存操作
//			boolean isOk = teacherService.checkTeacherameAndTeacherNum(teacher.getId(), teacher.getTeachernum(), teacher.getTeachername());
//			if (isOk) {
				teacherService.update(teacher);
				return ajaxReturn(true, "修改成功");
//			} else {
//				return ajaxReturn(false, "教师编号或教师名字已经存在！");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ajaxReturn(false, "保存失败");
	}
	
	@RequestMapping(value="/add.do", produces="application/json;charset=utf-8")
	public Map<String, Object> addTeacher(Teacher teacher) {
		try {
			// 查询学号和rfid是否存在，如果存在就执行保存操作
			boolean isOk = teacherService.checkTeachernameAndTeacherNum(teacher.getTeachernum(), teacher.getTeachername());
			String teachernameString = teacher.getTeachername();
			boolean isOk2 = teacherService.checkTeachername(teachernameString);

			if (isOk) {
				teacherService.add(teacher);
				return ajaxReturn(true, "添加成功");
			} else {
				return ajaxReturn(false, "教师编号或教师名字已经存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败");
	}
	
	@RequestMapping(value="/getData.do", produces="application/json;charset=utf-8")
	public Pagination<Teacher> getData(String teachername, String teachernum, String position,String page, String rows) {
		// 动态拼接查询条件
		Example example = new Example(Teacher.class);
		// 通过example对象创建一个Criteria对象
		Criteria c = example.createCriteria();
		if (teachername != null && !"".equals(teachername)) {
			c.andLike("teachername", "%" + teachername + "%");
		}
		if (position != null && !"".equals(position)) {
			c.andLike("position", "%" + position + "%");
		}
		if (teachernum != null && !"".equals(teachernum)) {
			c.andLike("teachernum", "%" + teachernum + "%");
		}
//		if (position != null && !"".equals(position)) {
//			c.andEqualTo("position", "%" + position + "%");
//		}
//		if (teachernum != null && !"".equals(teachernum)) {
//			c.andEqualTo("teachernum", teachernum);
//		}
		// 排序条件 按照id排序
		example.setOrderByClause("id asc");
		// 调用分页查询方法
		List list = teacherService.findByPage(example
				, Integer.parseInt(page), Integer.parseInt(rows));
		// 创建PageInfo对象，该对象自动计算分页的信息
		PageInfo pageInfo = new PageInfo(list);
		Pagination<Teacher> pagination = new Pagination();
		pagination.setTotal(pageInfo.getTotal());
		pagination.setRows(list);
		return pagination;
	}
	
}
