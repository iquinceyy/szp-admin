package cn.lz.szp.service.impl;

import cn.lz.szp.dao.UserDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.User;
import cn.lz.szp.pojo.query.UserQuery;
import cn.lz.szp.pojo.vo.UserVO;
import cn.lz.szp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * csw
 * 2020/7/4
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;


    @Override
    public ResponseDTO add(User user) {
        userDao.insertSelective(user);
        if (userDao.insert(user) == 1) {
            return ResponseDTO.ok("用户添加成功");
        } else {
            return ResponseDTO.fail("用户添加失败");
        }
    }

    @Override
    public PageDTO ajaxUserList(UserQuery query) {
        List<UserVO> vos = userDao.ajaxUserList(query);
        Integer count =userDao.ajaxUserListCount(query);
        return PageDTO.setPageData(count,vos);
    }

    @Override
    public ResponseDTO delete(Long userId) {
        return ResponseDTO.get(userDao.deleteByPrimaryKey(userId) == 1);
    }

    @Override
    public ResponseDTO edit(User user) {
        return ResponseDTO.get(userDao.updateByPrimaryKeySelective(user) == 1);
    }


}
