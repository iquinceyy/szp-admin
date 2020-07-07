package cn.lz.szp.rest;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Chapter;
import cn.lz.szp.pojo.query.ChapterQuery;
import cn.lz.szp.service.ChapterService;
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
@RequestMapping("/chapter")
public class ChapterRest {

    @Resource
    ChapterService chapterService;

    //插入
    @RequestMapping("/add")
    ResponseDTO add(@RequestBody Chapter chapter){
        chapter.setCreateTime(new Date());
        chapter.setUpdateTime(new Date());
        return chapterService.add(chapter);
    }

    //查询
    @RequestMapping("/ajaxCourseList")
    PageDTO ajaxCourseList(ChapterQuery query){
        return chapterService.ajaxChapterList(query);
    }

    //删除
    @RequestMapping("/delete/{chapterId}")
    ResponseDTO deleteCourseByCourseId(@PathVariable Long chapterId){
        return chapterService.delete(chapterId);
    }

    //修改
    @RequestMapping("/edit")
    ResponseDTO editCourseByCourseId(@RequestBody  Chapter chapter){
        chapter.setUpdateTime(new Date());
        return chapterService.edit(chapter);
    }



}




