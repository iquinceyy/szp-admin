package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.entity.UserAccount;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.pojo.query.UserAccountQuery;
import cn.lz.szp.pojo.vo.UserAccountVO;
import cn.lz.szp.service.PayLogService;
import cn.lz.szp.service.UserAccountService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * quincey
 * Date 2020/7/3 17:50
 */

@RestController
@RequestMapping("/useraccount/")
public class UserAccountRest {

    @Resource
    UserAccountService userAccountService;

    //新增
    @RequestMapping("/add")
    ResponseDTO insertPayLog(UserAccount account){
        account.setCreateTime(new Date());
        return userAccountService.add(account);
    }

//    //查询
//    @RequestMapping("/ajaxList")
//    PageDTO select(UserAccountQuery query){
//        return userAccountService.ajaxList(query);
//    }

    //查询
    @RequestMapping("/select")
    ResponseDTO select(UserAccountQuery query){
        return userAccountService.select(query);
    }

    //删除
    @RequestMapping("/delete/{userId}")
    ResponseDTO deleteUserAccountByUserId(@PathVariable Long userId){
        return userAccountService.delete(userId);
    }

    //修改
    @RequestMapping("/edit")
    ResponseDTO editByUserAccount(UserAccount account){
        account.setUpdateTime(new Date());
        return userAccountService.edit(account);
    }
}
