package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.entity.Withdrawal;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.pojo.query.WithdrawalQuery;
import cn.lz.szp.service.PayLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * quincey
 * Date 2020/7/3 17:50
 */

@RestController
@RequestMapping("/payLog/")
public class PayLogRest {

    @Resource
    PayLogService payLogService;

    //插入
    @RequestMapping("/insert")
    ResponseDTO insertPayLog(PayLog payLog){
        payLog.setPayTime(new Date());
        return payLogService.insert(payLog);
    }

    //查询
    @RequestMapping("/ajaxList")
    PageDTO select(PayLogQuery query){
        return payLogService.selectPayLogList(query);
    }
}
