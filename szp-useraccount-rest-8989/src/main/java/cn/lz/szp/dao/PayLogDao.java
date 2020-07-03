package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.PayLog;

public interface PayLogDao {
    int deleteByPrimaryKey(Long payLogId);

    int insert(PayLog record);

    int insertSelective(PayLog record);

    PayLog selectByPrimaryKey(Long payLogId);

    int updateByPrimaryKeySelective(PayLog record);

    int updateByPrimaryKey(PayLog record);
}