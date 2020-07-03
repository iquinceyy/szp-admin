package cn.lz.szp.service;

import cn.lz.szp.config.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * quincey
 * Date 2020/7/3 18:25
 */

@FeignClient(value = "", path = "", configuration = FeignClientConfig.class)
public interface WithdrawalServiceFeign {
}
