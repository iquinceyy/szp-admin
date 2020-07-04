package cn.lz.szp.controller.pages.back.useraccount;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.service.PayLogServiceFeign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/7/3 20:11
 */

@RestController
@RequestMapping("/back/useraccount/payLog")
public class PayLogController {

    @Resource
    PayLogServiceFeign payLogServiceFeign;

    @RequestMapping("/insert")
    ResponseDTO insertPayLog(PayLog payLog){

        return payLogServiceFeign.insertPayLog(payLog);
    }

    @RequestMapping("/ajaxList")
    PageDTO select(PayLogQuery query){
        return payLogServiceFeign.select(query);
    }


}
