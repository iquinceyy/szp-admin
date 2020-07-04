package cn.lz.szp.service.impl;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.service.PayLogService;
import org.springframework.stereotype.Service;

/**
 * quincey
 * Date 2020/7/3 17:46
 */

@Service
public class PayLogServiceImpl implements PayLogService {
    @Override
    public PageDTO selectPayLogList(PayLogQuery query) {
        return null;
    }

    @Override
    public ResponseDTO insert(PayLog payLog) {
        return null;
    }
}
