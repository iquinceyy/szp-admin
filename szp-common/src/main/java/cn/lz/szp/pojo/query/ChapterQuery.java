package cn.lz.szp.pojo.query;

import lombok.Data;

/**
 * csw
 * 2020/7/7
 */
@Data
public class ChapterQuery extends PageQuery {
    /**
     * 章节名称
     */
    private String name;
}
