package cn.lz.szp.service.impl;

import cn.lz.szp.dao.CourseDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Course;
import cn.lz.szp.pojo.query.CourseQuery;
import cn.lz.szp.pojo.query.PageQuery;
import cn.lz.szp.pojo.vo.CourseVO;
import cn.lz.szp.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * csw
 * 2020/7/7
 */


@Service
public class CourseServiceImpl implements CourseService {


    @Resource
    CourseDao courseDao;


    @Override
    public ResponseDTO add(Course course) {
        if (courseDao.insertSelective(course)==1){
            return ResponseDTO.ok("添加成功");
        }
        return ResponseDTO.fail("添加失败");
    }

    @Override
    public PageDTO ajaxCourseList(CourseQuery query) {
        List<CourseVO> vos = courseDao.ajaxCourseList(query);
        Integer count = courseDao.ajaxCourseListCount(query);
        return PageDTO.setPageData(count,vos);
    }

    @Override
    public ResponseDTO delete(Long courseId) {
        if (courseDao.deleteByPrimaryKey(courseId)==1){
            return ResponseDTO.ok("删除成功");
        }
        return ResponseDTO.fail("删除失败");
    }

    @Override
    public ResponseDTO edit(Course course) {

        if (courseDao.updateByPrimaryKeySelective(course)==1){
            return ResponseDTO.ok("修改成功");
        }
        return ResponseDTO.fail("修改失败");
    }

//    @Override
//    public Map<String,Object> ajaxCourseList2(Integer page, Integer size, Course course) {
//        //1. 得到当前页数据
//        List<Course> list = courseDao.page(page,size,course);
//        //2. 总数量
//        Integer num = courseDao.countCourse(course);
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data",list);
//        map.put("count",num);
//        return map;
//    }
}
