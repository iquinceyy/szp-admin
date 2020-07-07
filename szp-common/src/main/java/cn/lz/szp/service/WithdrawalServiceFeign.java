package cn.lz.szp.service;

import cn.lz.szp.config.feign.FeignClientConfig;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Withdrawal;
import cn.lz.szp.pojo.query.WithdrawalQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * quincey
 * Date 2020/7/3 18:25
 */

@FeignClient(value = "useraccount-rest", path = "/withdrawal/", configuration = FeignClientConfig.class)
public interface WithdrawalServiceFeign {

    @RequestMapping("/insert")
    ResponseDTO insertWithdrawal(Withdrawal withdrawal);

    @RequestMapping("/ajaxList")
    PageDTO select(WithdrawalQuery query);
}
