package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.UserAccount;

public interface UserAccountDao {
    int deleteByPrimaryKey(Long userId);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);
}