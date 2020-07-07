package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Course;
import cn.lz.szp.pojo.query.CourseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * csw
 * 2020/7/7
 */
public interface CourseService {
    ResponseDTO add(Course course);

    PageDTO ajaxCourseList(CourseQuery query);

    ResponseDTO delete(Long courseId);

    ResponseDTO edit(Course course);

//    Map<String,Object> ajaxCourseList2(Integer page, Integer size, Course course);
}
