package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.User;
import cn.lz.szp.pojo.query.UserQuery;

/**
 * csw
 * 2020/7/4
 */
public interface UserService {
    ResponseDTO add(User user);

    PageDTO ajaxUserList(UserQuery query);

    ResponseDTO delete(Long userId);

    ResponseDTO edit(User user);
}
