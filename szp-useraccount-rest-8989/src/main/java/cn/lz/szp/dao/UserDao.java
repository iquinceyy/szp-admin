package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.User;
import cn.lz.szp.pojo.query.UserQuery;
import cn.lz.szp.pojo.vo.UserVO;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserVO> ajaxUserList(UserQuery query);

    Integer ajaxUserListCount(UserQuery query);
}