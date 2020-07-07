package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.entity.User;
import cn.lz.szp.pojo.entity.UserAccount;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.pojo.query.UserQuery;
import cn.lz.szp.service.PayLogService;
import cn.lz.szp.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * quincey
 * Date 2020/7/3 17:50
 */

@RestController
@RequestMapping("/user/")
public class UserRest {

    @Resource
    UserService userService;

    //插入
    @RequestMapping("/add")
    ResponseDTO add(@RequestBody  User user){
        user.setCreatTime(new Date());
        return userService.add(user);
    }

    //查询
    @RequestMapping("/ajaxUserList")
    PageDTO ajaxUserList(UserQuery query){
        return userService.ajaxUserList(query);
    }

    //删除
    @RequestMapping("/delete/{userId}")
    ResponseDTO deleteUserByUserId(@PathVariable Long userId){
        return userService.delete(userId);
    }

    //修改
    @RequestMapping("/edit")
    ResponseDTO editByUserAccount(User user){
        user.setUpdateTime(new Date());
        return userService.edit(user);
    }



}




