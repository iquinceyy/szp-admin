package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Course;
import cn.lz.szp.pojo.entity.Type;
import cn.lz.szp.pojo.query.CourseQuery;
import cn.lz.szp.pojo.query.TypeQuery;
import cn.lz.szp.service.CourseService;
import cn.lz.szp.service.TypeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * csw
 * 2020/7/7
 */
@RestController
@RequestMapping("/type")
public class TypeRest {

    @Resource
    TypeService typeService;

    //插入
    @RequestMapping("/add")
    ResponseDTO add(@RequestBody Type type){
        type.setCreateTime(new Date());
        type.setUpdateTime(new Date());
        return typeService.add(type);
    }

    //查询
    @RequestMapping("/ajaxCourseList")
    PageDTO ajaxTypeServiceList(TypeQuery query){

        return typeService.ajaxCourseList(query);
    }

    //删除
    @RequestMapping("/delete/{typeId}")
    ResponseDTO deleteTypeIdByTypeId(@PathVariable Integer typeId){
        return typeService.delete(typeId);
    }

    //修改
    @RequestMapping("/edit")
    ResponseDTO editCourseByCourseId(@RequestBody  Type type){
        type.setUpdateTime(new Date());
        return typeService.edit(type);
    }

}
