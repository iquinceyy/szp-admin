package cn.lz.szp.service.fallback;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.service.PayLogServiceFeign;
import org.springframework.stereotype.Service;

/**
 * quincey
 * Date 2020/7/3 23:14
 */

@Service
public class PayLogServiceFallBack implements PayLogServiceFeign {

    @Override
    public ResponseDTO insertPayLog(PayLog payLog) {
        System.err.println("我是insertPayLog的服务降级");
        return null;
    }

    @Override
    public PageDTO select(PayLogQuery query) {
        System.err.println("我是insertPayLog的服务降级");
        return null;
    }
}
