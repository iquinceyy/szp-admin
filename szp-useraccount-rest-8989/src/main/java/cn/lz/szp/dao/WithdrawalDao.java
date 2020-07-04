package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.Withdrawal;
import cn.lz.szp.pojo.query.WithdrawalQuery;
import cn.lz.szp.pojo.vo.WithdrawalVO;

import java.util.List;

public interface WithdrawalDao {
    int deleteByPrimaryKey(Long id);

    int insert(Withdrawal record);

    int insertSelective(Withdrawal record);

    Withdrawal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Withdrawal record);

    int updateByPrimaryKey(Withdrawal record);

    List<WithdrawalVO> ajaxList(WithdrawalQuery query);

    Integer ajaxListCount(WithdrawalQuery query);
}