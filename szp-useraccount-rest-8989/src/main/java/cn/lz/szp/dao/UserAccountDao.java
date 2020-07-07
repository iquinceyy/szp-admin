package cn.lz.szp.dao;

import cn.lz.szp.pojo.entity.UserAccount;
import cn.lz.szp.pojo.query.UserAccountQuery;
import cn.lz.szp.pojo.vo.UserAccountVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccountDao {
    int deleteByPrimaryKey(Long userId);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    UserAccountVO ajaxList(UserAccountQuery query);

    Integer ajaxListCount(UserAccountQuery query);

    Integer insertUserAccount(UserAccount account);
}
