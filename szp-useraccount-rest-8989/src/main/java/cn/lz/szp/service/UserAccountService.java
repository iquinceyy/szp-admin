package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.entity.UserAccount;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.pojo.query.UserAccountQuery;
import cn.lz.szp.pojo.vo.UserAccountVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * quincey
 * Date 2020/7/3 17:45
 */


public interface UserAccountService {

    ResponseDTO add(UserAccount account);

//    PageDTO ajaxList(UserAccountQuery query);
    ResponseDTO select(UserAccountQuery query);

    ResponseDTO delete(Long userId);

    ResponseDTO edit(UserAccount account);
}
