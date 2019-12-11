package com.example.demo.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.example.demo.entity.Course;
import com.example.demo.service.ICourseService;
import com.example.demo.service.ITeacherService;
import com.example.demo.utils.Pagination;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/course")
public class CourseController extends BaseController {
	@Autowired
	private ICourseService courseService;
	//@Autowired 每个依赖都要写一遍，这个地方卡了我一天
	@Autowired
	private ITeacherService teacherService;
	
	/*@RequestMapping(value="/getData.do", produces="application/json;charset=utf-8")
	public List<Course> getData() {
		return courseService.findAll();
	}*/
	
	@RequestMapping(value="/deleteAll.do", produces="application/json;charset=utf-8")
	public Map<String, Object> deleteAllCourse(String ids) {
		try {
			String[] idArr = ids.split(",");
			courseService.deleteBatch(idArr);
			return ajaxReturn(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败！");
	}
	
	@RequestMapping(value="/delete.do", produces="application/json;charset=utf-8")
	public Map<String, Object> deleteCourse(String id) {
		try {
			courseService.delete(Integer.parseInt(id));
			return ajaxReturn(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "删除失败！");
	}
	
	@RequestMapping(value="/update.do", produces="application/json;charset=utf-8")
	public Map<String, Object> updateCourse(Course course) {
		System.out.println("course = " + course);
		try {
			// 查询学号和rfid是否存在，如果存在就执行保存操作
			boolean isOk = courseService.checkcourseidAndcoursename(course.getId(), course.getCourseid(), course.getCoursename());
			System.out.println("course = " + course);
			if (isOk) {
				courseService.update(course);
				
				return ajaxReturn(true, "修改成功");
			} else {
				return ajaxReturn(false, "课程编号或课程名称已经存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败");
	}
	
	@RequestMapping(value="/add.do", produces="application/json;charset=utf-8")
	public Map<String, Object> addCourse(Course course) {
		try {
			// 查询课程编号和课程名字是否存在，如果存在就执行保存操作
			boolean isnofind = courseService.checkcourseidAndcoursename(course.getCourseid(), course.getCoursename());
			
			// 查询课程编号是否存在
			boolean isnofind2 = courseService.checkcourseid(course.getCourseid());
			
			//查询任课教师的名字是否存在教师表中，存在则false，不存在则true
			String courseteacher = course.getCourseteacher();
			boolean isnofind3 = teacherService.checkTeachername(courseteacher);
			
			
			//判断是否有同名同编号的课程，存在则false，不存在则继续下一个判断
			if (isnofind2) {
				//判断是否存在这个教师名称
				if (!isnofind3) {
					courseService.add(course);

					return ajaxReturn(true, "添加成功");
				}else {
					return ajaxReturn(false, "不存在这个教师名称");
				}
			} else {
				return ajaxReturn(false, "课程编号已经存在！");
//				return ajaxReturn(false, "课程编号或课程名称已经存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败");
	}
	
	@RequestMapping(value="/getData.do", produces="application/json;charset=utf-8")
	public Pagination<Course> getData(String num, String name,String teacher, String page, String rows) {
		// 动态拼接查询条件
		Example example = new Example(Course.class);
		// 通过example对象创建一个Criteria对象
		Criteria c = example.createCriteria();
		if (name != null && !"".equals(name)) {
			c.andLike("coursename", "%" + name + "%");
		}
		if (teacher != null && !"".equals(teacher)) {
			c.andLike("courseteacher", "%" + teacher + "%");
		}
		//andLike是模糊查询
		if (num != null && !"".equals(num)) {
			c.andLike("courseid", "%" + num + "%");
		}
		//andEqualTo是准确的查询，相当于=
//		if (num != null && !"".equals(num)) {
//			c.andEqualTo("courseid", num);
//		}
		// 排序条件
		example.setOrderByClause("id asc");
		// 调用分页查询方法
		List list = courseService.findByPage(example
				, Integer.parseInt(page), Integer.parseInt(rows));
		// 创建PageInfo对象，该对象自动计算分页的信息
		PageInfo pageInfo = new PageInfo(list);
		Pagination<Course> pagination = new Pagination();
		pagination.setTotal(pageInfo.getTotal());
		pagination.setRows(list);
		return pagination;
	}
	
}
