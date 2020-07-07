package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.Course;
import cn.lz.szp.pojo.query.CourseQuery;
import cn.lz.szp.pojo.vo.CourseVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    int deleteByPrimaryKey(Long courseId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Long courseId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<CourseVO> ajaxCourseList(CourseQuery query);

    Integer ajaxCourseListCount(CourseQuery query);

    List<Course> page(@Param("page") Integer page, @Param("size")Integer size, @Param("course")Course course);

    Integer countCourse(Course course);
}