package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.AppConfig;
import cn.lz.szp.pojo.query.AppConfigQuery;

/**
 * csw
 * 2020/7/7
 */
public interface AppConfigService {
    ResponseDTO add(AppConfig appConfig);

    PageDTO ajaxAppConfigList(AppConfigQuery query);

    ResponseDTO delete(Integer configId);

    ResponseDTO edit(AppConfig appConfig);
}
