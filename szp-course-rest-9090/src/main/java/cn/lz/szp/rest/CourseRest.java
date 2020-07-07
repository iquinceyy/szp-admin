package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Course;
import cn.lz.szp.pojo.query.CourseQuery;
import cn.lz.szp.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * quincey
 * Date 2020/7/3 17:50
 */

@RestController
@RequestMapping("/course")
public class CourseRest {

    @Resource
    CourseService courseService;

    //插入
    @RequestMapping("/add")
    ResponseDTO add(@RequestBody Course course){
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        course.setReleaseTime(new Date());
        return courseService.add(course);
    }

    //查询
    @RequestMapping("/ajaxCourseList")
    PageDTO ajaxCourseList(CourseQuery query){

        return courseService.ajaxCourseList(query);
    }

    //查询
//    @RequestMapping("/ajaxCourseList2")
//    PageDTO ajaxCourseList2(
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer size,
//            @RequestBody Course course){
//
//        /**
//         * 模糊查询所有课程
//         */
//        Map<String,Object> map = courseService.ajaxCourseList2(page,size,course);
//        Course data = (Course) map.get("data");
//        Integer count = (Integer) map.get("count");
//
//        return PageDTO.setPageData(count,data);
//    }

    //删除
    @RequestMapping("/delete/{courseId}")
    ResponseDTO deleteCourseByCourseId(@PathVariable Long courseId){
        return courseService.delete(courseId);
    }

    //修改
    @RequestMapping("/edit")
    ResponseDTO editCourseByCourseId(@RequestBody  Course course){
        course.setUpdateTime(new Date());
        return courseService.edit(course);
    }



}




