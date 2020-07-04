package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Withdrawal;
import cn.lz.szp.pojo.query.WithdrawalQuery;
import cn.lz.szp.pojo.vo.WithdrawalVO;

import java.util.List;

/**
 * quincey
 * Date 2020/7/3 17:45
 */


public interface WithdrawalService {

    //插入日志
    ResponseDTO insert(Withdrawal withdrawal);

    //查询日志
    List<WithdrawalVO> selectWithdrawalList(WithdrawalQuery query);



}
