package cn.lz.szp.service.impl;

import cn.lz.szp.dao.UserAccountDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.PayLog;
import cn.lz.szp.pojo.entity.UserAccount;
import cn.lz.szp.pojo.query.PayLogQuery;
import cn.lz.szp.pojo.query.UserAccountQuery;
import cn.lz.szp.pojo.vo.PayLogVO;
import cn.lz.szp.pojo.vo.UserAccountVO;
import cn.lz.szp.service.UserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * quincey
 * Date 2020/7/3 17:47
 */

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    UserAccountDao userAccountDao;

    @Override
    public ResponseDTO add(UserAccount account) {

        Long userId = account.getUserId();
        if (userId != null) {
            return ResponseDTO.get(userAccountDao.insertUserAccount(account) == 1);
        }
        return ResponseDTO.fail("用户id不能为空");
    }

//    @Override
//    public PageDTO ajaxList(UserAccountQuery query) {
//        List<UserAccountVO> userAccountVOS = userAccountDao.ajaxList(query);
//        Integer count = userAccountDao.ajaxListCount(query);
//        return PageDTO.setPageData(count, userAccountVOS);
//    }

    @Override
    public ResponseDTO select(UserAccountQuery query) {
//        query.setUserId(123L);
        UserAccountVO userAccountVO = userAccountDao.ajaxList(query);
//        System.out.println(query);
//        UserAccount userAccount = userAccountDao.selectByPrimaryKey(query.getUserId());
//        Integer count = userAccountDao.ajaxListCount(query);
        return ResponseDTO.get(userAccountVO);
    }

    @Override
    public ResponseDTO delete(Long userId) {

        return ResponseDTO.get(userAccountDao.deleteByPrimaryKey(userId) == 1);
    }

    @Override
    public ResponseDTO edit(UserAccount account) {
        Long userId = account.getUserId();
        if (userId != null) {
            return ResponseDTO.get(userAccountDao.updateByPrimaryKeySelective(account) == 1);
        }
        return ResponseDTO.fail("用户id不能为空");
    }
}
