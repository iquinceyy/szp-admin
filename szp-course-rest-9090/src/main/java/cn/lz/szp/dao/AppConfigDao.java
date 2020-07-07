package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.AppConfig;
import cn.lz.szp.pojo.query.AppConfigQuery;
import cn.lz.szp.pojo.vo.AppConfigVO;

import java.util.List;

public interface AppConfigDao {
    int deleteByPrimaryKey(Integer configId);

    int insert(AppConfig record);

    int insertSelective(AppConfig record);

    AppConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(AppConfig record);

    int updateByPrimaryKey(AppConfig record);

    List<AppConfigVO> ajaxCourseList(AppConfigQuery query);

    Integer ajaxCourseListCount(AppConfigQuery query);
}