package cn.lz.szp.rest;

import cn.lz.szp.dao.WithdrawalDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Withdrawal;
import cn.lz.szp.pojo.query.WithdrawalQuery;
import cn.lz.szp.pojo.vo.WithdrawalVO;
import cn.lz.szp.service.WithdrawalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * quincey
 * Date 2020/7/3 17:51
 */
@RestController
@RequestMapping("/withdrawal/")
public class WithdrawalRest {

    @Resource
    WithdrawalService withdrawalService;

    //插入
    @RequestMapping("/insert")
    ResponseDTO insertWithdrawal(Withdrawal withdrawal){
//        withdrawal.setMoney(new BigDecimal(100));
//        withdrawal.setUserId(1L);
//        withdrawal.setAlipayAccount("测试");
//        withdrawal.setWeixinAccount("测试");
        return withdrawalService.insert(withdrawal);
    }

    //查询
    @RequestMapping("/ajaxList")
    PageDTO select(WithdrawalQuery query){
        return withdrawalService.selectWithdrawalList(query);
    }


}
