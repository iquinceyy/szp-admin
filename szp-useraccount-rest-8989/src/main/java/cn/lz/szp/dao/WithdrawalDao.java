package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.Withdrawal;

public interface WithdrawalDao {
    int deleteByPrimaryKey(Long id);

    int insert(Withdrawal record);

    int insertSelective(Withdrawal record);

    Withdrawal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Withdrawal record);

    int updateByPrimaryKey(Withdrawal record);
}