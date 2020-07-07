package cn.lz.szp.service.impl;

import cn.lz.szp.dao.PayLogDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.pojo.vo.PayLogVO;
import cn.lz.szp.pojo.vo.WithdrawalVO;
import cn.lz.szp.service.PayLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * quincey
 * Date 2020/7/3 17:46
 */

@Service
public class PayLogServiceImpl implements PayLogService {

    @Resource
    PayLogDao payLogDao;
    @Override
    public PageDTO selectPayLogList(PayLogQuery query) {

        List<PayLogVO> payLogVOS = payLogDao.ajaxList(query);
        Integer count = payLogDao.ajaxListCount(query);
        return PageDTO.setPageData(count,payLogVOS);
    }

    @Override
    public ResponseDTO insert(PayLog payLog) {
        return null;
    }
}
