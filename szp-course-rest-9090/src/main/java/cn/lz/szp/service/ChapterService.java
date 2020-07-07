package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Chapter;
import cn.lz.szp.pojo.query.ChapterQuery;

/**
 * csw
 * 2020/7/7
 */
public interface ChapterService {
    ResponseDTO add(Chapter chapter);

    PageDTO ajaxChapterList(ChapterQuery query);

    ResponseDTO delete(Long chapterId);

    ResponseDTO edit(Chapter chapter);
}
