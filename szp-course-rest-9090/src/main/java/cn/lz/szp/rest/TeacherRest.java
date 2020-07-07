package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Chapter;
import cn.lz.szp.pojo.entity.Teacher;
import cn.lz.szp.pojo.query.ChapterQuery;
import cn.lz.szp.pojo.query.TeacherQuery;
import cn.lz.szp.service.ChapterService;
import cn.lz.szp.service.TeacherService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * quincey
 * Date 2020/7/3 17:50
 */

@RestController
@RequestMapping("/teacher")
public class TeacherRest {

    @Resource
    TeacherService teacherService;

    //插入
    @RequestMapping("/add")
    ResponseDTO add(@RequestBody Teacher teacher){
        teacher.setCreateTime(new Date());
        teacher.setUpdateTime(new Date());
        return teacherService.add(teacher);
    }

    //查询
    @RequestMapping("/ajaxTeacherList")
    PageDTO ajaxCourseList(TeacherQuery query){
        return teacherService.ajaxTeacherList(query);
    }

    //删除
    @RequestMapping("/delete/{userId}")
    ResponseDTO deleteCourseByCourseId(@PathVariable Long userId){
        return teacherService.delete(userId);
    }

    //修改
    @RequestMapping("/edit")
    ResponseDTO editCourseByCourseId(@RequestBody  Teacher teacher){
        teacher.setUpdateTime(new Date());
        return teacherService.edit(teacher);
    }



}




