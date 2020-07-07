package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.Teacher;
import cn.lz.szp.pojo.query.TeacherQuery;
import cn.lz.szp.pojo.vo.TeacherVO;

import java.util.List;

public interface TeacherDao {
    int deleteByPrimaryKey(Long userId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<TeacherVO> ajaxTeacherList(TeacherQuery query);

    Integer ajaxTeacherListCount(TeacherQuery query);
}