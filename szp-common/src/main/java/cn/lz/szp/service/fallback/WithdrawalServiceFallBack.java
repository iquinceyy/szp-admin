package cn.lz.szp.service.fallback;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Withdrawal;
import cn.lz.szp.pojo.query.WithdrawalQuery;
import cn.lz.szp.service.WithdrawalServiceFeign;
import org.springframework.stereotype.Service;

/**
 * quincey
 * Date 2020/7/3 23:14
 */

@Service
public class WithdrawalServiceFallBack implements WithdrawalServiceFeign {
    @Override
    public ResponseDTO insertWithdrawal(Withdrawal withdrawal) {
        System.err.println("我是insertWithdrawal的服务降级");
        return null;
    }

    @Override
    public PageDTO select(WithdrawalQuery query) {
        System.err.println("我是select的服务降级");
        return null;
    }
}
