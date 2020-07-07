package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.query.PayLogQuery;

/**
 * quincey
 * Date 2020/7/3 17:45
 */


public interface PayLogService {

    PageDTO selectPayLogList(PayLogQuery query);

    ResponseDTO insert(PayLog payLog);
}
