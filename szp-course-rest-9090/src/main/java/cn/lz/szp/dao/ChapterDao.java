package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.Chapter;
import cn.lz.szp.pojo.query.ChapterQuery;
import cn.lz.szp.pojo.vo.ChapterVO;

import java.util.List;

public interface ChapterDao {
    int deleteByPrimaryKey(Long chapterId);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Long chapterId);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);


    List<ChapterVO> ajaxCourseList(ChapterQuery query);

    Integer ajaxCourseListCount(ChapterQuery query);
}