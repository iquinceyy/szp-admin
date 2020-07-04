package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.pojo.vo.PayLogVO;

import java.util.List;

public interface PayLogDao {
    int deleteByPrimaryKey(Long payLogId);

    int insert(PayLog record);

    int insertSelective(PayLog record);

    PayLog selectByPrimaryKey(Long payLogId);

    int updateByPrimaryKeySelective(PayLog record);

    int updateByPrimaryKey(PayLog record);

    List<PayLogVO> ajaxList(PayLogQuery query);

    Integer ajaxListCount(PayLogQuery query);
}