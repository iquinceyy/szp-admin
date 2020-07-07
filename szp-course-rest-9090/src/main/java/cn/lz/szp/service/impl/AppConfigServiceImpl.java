package cn.lz.szp.service.impl;

import cn.lz.szp.dao.AppConfigDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.AppConfig;
import cn.lz.szp.pojo.query.AppConfigQuery;
import cn.lz.szp.pojo.vo.AppConfigVO;
import cn.lz.szp.pojo.vo.ChapterVO;
import cn.lz.szp.service.AppConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * csw
 * 2020/7/7
 */


@Service
public class AppConfigServiceImpl implements AppConfigService {
    @Resource
    AppConfigDao appConfigDao;

    @Override
    public ResponseDTO add(AppConfig appConfig) {
        if (appConfigDao.insertSelective(appConfig)==1){
            return ResponseDTO.ok("添加成功");
        }
        return ResponseDTO.fail("添加失败");
    }

    @Override
    public PageDTO ajaxAppConfigList(AppConfigQuery query) {
        List<AppConfigVO> vos = appConfigDao.ajaxCourseList(query);
        Integer count = appConfigDao.ajaxCourseListCount(query);
        return PageDTO.setPageData(count,vos);
    }

    @Override
    public ResponseDTO delete(Integer configId) {
        if (appConfigDao.deleteByPrimaryKey(configId)==1){
            return ResponseDTO.ok("删除成功");
        }
        return ResponseDTO.fail("删除失败");
    }

    @Override
    public ResponseDTO edit(AppConfig appConfig) {
        if (appConfigDao.updateByPrimaryKeySelective(appConfig)==1){
            return ResponseDTO.ok("修改成功");
        }
        return ResponseDTO.fail("修改失败");
    }
}
