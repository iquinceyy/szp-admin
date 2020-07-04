package cn.lz.szp.controller.pages.back.useraccount;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Withdrawal;
import cn.lz.szp.pojo.query.WithdrawalQuery;
import cn.lz.szp.service.WithdrawalServiceFeign;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/7/3 20:11
 */

@RestController
@RequestMapping("/back/useraccount/withdrawal/")
public class WithdrawalController {
    
    @Resource
    WithdrawalServiceFeign withdrawalServiceFeign;

    @RequestMapping("/insert")
    ResponseDTO insertWithdrawal(Withdrawal withdrawal) {
        return withdrawalServiceFeign.insertWithdrawal(withdrawal);
    }

    @RequestMapping("/ajaxList")
    PageDTO select(WithdrawalQuery query){

        return withdrawalServiceFeign.select(query);
    }
}
