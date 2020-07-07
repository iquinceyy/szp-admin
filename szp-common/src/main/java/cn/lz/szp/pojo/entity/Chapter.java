package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * chapter
 * @author 
 */
@Data
public class Chapter implements Serializable {
    /**
     * 章节id
     */
    private Long chapterId;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节序号
     */
    private Integer sort;

    /**
     * 此章节是哪个课程id
     */
    private Long courseId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}