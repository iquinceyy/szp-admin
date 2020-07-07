package cn.lz.szp.service;

import cn.lz.szp.config.feign.FeignClientConfig;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.query.PayLogQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * quincey
 * Date 2020/7/3 18:24
 */

@FeignClient(value = "useraccount-rest", configuration = FeignClientConfig.class)
public interface PayLogServiceFeign {

//    @RequestParam
    @RequestMapping("/payLog/insert")
    ResponseDTO insertPayLog( PayLog payLog);

    @RequestMapping("/payLog/ajaxList")
    PageDTO select(PayLogQuery query);
}
