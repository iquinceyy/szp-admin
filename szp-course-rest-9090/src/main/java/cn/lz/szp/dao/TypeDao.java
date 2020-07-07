package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.Type;
import cn.lz.szp.pojo.query.TypeQuery;
import cn.lz.szp.pojo.vo.CourseVO;
import cn.lz.szp.pojo.vo.TypeVO;

import java.util.List;

public interface TypeDao {
    int deleteByPrimaryKey(Integer typeId);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<TypeVO> ajaxCourseList(TypeQuery query);

    Integer ajaxCourseListCount(TypeQuery query);
}