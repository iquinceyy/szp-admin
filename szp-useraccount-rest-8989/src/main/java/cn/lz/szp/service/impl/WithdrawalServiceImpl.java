package cn.lz.szp.service.impl;

import cn.lz.szp.dao.WithdrawalDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Withdrawal;
import cn.lz.szp.pojo.query.WithdrawalQuery;
import cn.lz.szp.pojo.vo.WithdrawalVO;
import cn.lz.szp.service.WithdrawalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * quincey
 * Date 2020/7/3 17:47
 */

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Resource
    WithdrawalDao withdrawalDao;
    @Override
    public ResponseDTO insert(Withdrawal withdrawal) {


            return  ResponseDTO.get(withdrawalDao.insertSelective(withdrawal)==1);
    }

    @Override
    public List<WithdrawalVO> selectWithdrawalList(WithdrawalQuery query) {
        List<WithdrawalVO> withdrawalVOS = withdrawalDao.ajaxList(query);
//        Map<Long, List<WithdrawalVO>> collect = withdrawalVOS.stream().collect(Collectors.groupingBy(WithdrawalVO::getUserId));
//        Integer count = withdrawalDao.ajaxListCount(query);
        return withdrawalVOS;
    }


}
