package cn.lz.szp.service.impl;

import cn.lz.szp.dao.ChapterDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Chapter;
import cn.lz.szp.pojo.query.ChapterQuery;
import cn.lz.szp.pojo.vo.ChapterVO;
import cn.lz.szp.service.ChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * csw
 * 2020/7/7
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Resource
    ChapterDao chapterDao;

    @Override
    public ResponseDTO add(Chapter chapter) {
        if (chapterDao.insertSelective(chapter)==1){
            return ResponseDTO.ok("添加成功");
        }
        return ResponseDTO.fail("添加失败");
    }

    @Override
    public PageDTO ajaxChapterList(ChapterQuery query) {
        List<ChapterVO> vos = chapterDao.ajaxCourseList(query);
        Integer count = chapterDao.ajaxCourseListCount(query);
        return PageDTO.setPageData(count,vos);
    }

    @Override
    public ResponseDTO delete(Long chapterId) {
        if (chapterDao.deleteByPrimaryKey(chapterId)==1){
            return ResponseDTO.ok("删除成功");
        }
        return ResponseDTO.fail("删除失败");
    }

    @Override
    public ResponseDTO edit(Chapter chapter) {
        if (chapterDao.updateByPrimaryKeySelective(chapter)==1){
            return ResponseDTO.ok("修改成功");
        }
        return ResponseDTO.fail("修改失败");
    }
}
