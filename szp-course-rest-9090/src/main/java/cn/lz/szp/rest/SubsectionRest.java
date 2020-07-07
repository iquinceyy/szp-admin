package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Course;
import cn.lz.szp.pojo.entity.Subsection;
import cn.lz.szp.pojo.query.CourseQuery;
import cn.lz.szp.pojo.query.SubsectionQuery;
import cn.lz.szp.service.CourseService;
import cn.lz.szp.service.SubsectionService;
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
@RequestMapping("/subsection")
public class SubsectionRest {

    @Resource
    SubsectionService subsectionService;

    //插入
    @RequestMapping("/add")
    ResponseDTO add(@RequestBody Subsection subsection){
        subsection.setCreateTime(new Date());
        subsection.setUpdateTime(new Date());
        return subsectionService.add(subsection);
    }

    //查询
    @RequestMapping("/ajaxSubsectionList")
    PageDTO ajaxCourseList(SubsectionQuery query){

        return subsectionService.ajaxSubsectionList(query);
    }

    //删除
    @RequestMapping("/delete/{id}")
    ResponseDTO deleteCourseByCourseId(@PathVariable Integer id){
        return subsectionService.delete(id);
    }

    //修改
    @RequestMapping("/edit")
    ResponseDTO editCourseByCourseId(@RequestBody  Subsection subsection){
        subsection.setUpdateTime(new Date());
        return subsectionService.edit(subsection);
    }



}




