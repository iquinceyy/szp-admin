package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.Subsection;
import cn.lz.szp.pojo.query.SubsectionQuery;
import cn.lz.szp.pojo.vo.SubsectionVO;

import java.util.List;

public interface SubsectionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Subsection record);

    int insertSelective(Subsection record);

    Subsection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Subsection record);

    int updateByPrimaryKey(Subsection record);

    List<SubsectionVO> ajaxCourseList(SubsectionQuery query);

    Integer ajaxCourseListCount(SubsectionQuery query);
}