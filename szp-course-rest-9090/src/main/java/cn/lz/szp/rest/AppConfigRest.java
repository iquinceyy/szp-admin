package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.AppConfig;
import cn.lz.szp.pojo.entity.Chapter;
import cn.lz.szp.pojo.query.AppConfigQuery;
import cn.lz.szp.pojo.query.ChapterQuery;
import cn.lz.szp.service.AppConfigService;
import cn.lz.szp.service.ChapterService;
import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
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
@RequestMapping("/config")
public class AppConfigRest {

    @Resource
    AppConfigService appConfigService;

    //插入
    @RequestMapping("/add")
    ResponseDTO add(@RequestBody AppConfig appConfig){
        appConfig.setCreateTime(new Date());
        appConfig.setUpdateTime(new Date());
            return appConfigService.add(appConfig);
    }

    //查询
    @RequestMapping("/ajaxAppConfigList")
    PageDTO ajaxCourseList(AppConfigQuery query){
        return appConfigService.ajaxAppConfigList(query);
    }

    //删除
    @RequestMapping("/delete/{configId}")
    ResponseDTO deleteCourseByCourseId(@PathVariable Integer configId){
        return appConfigService.delete(configId);
    }

    //修改
    @RequestMapping("/edit")
    ResponseDTO editCourseByCourseId(@RequestBody  AppConfig appConfig){
        appConfig.setUpdateTime(new Date());
        return appConfigService.edit(appConfig);
    }



}




