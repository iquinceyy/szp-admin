package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * subsection
 * @author 
 */
@Data
public class Subsection implements Serializable {
    /**
     * 小节id
     */
    private Long id;

    /**
     * 章节id
     */
    private Long chapterId;

    /**
     * 目录名称
     */
    private String name;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 小节编号
     */
    private Integer sort;

    /**
     * 视频路径
     */
    private String src;

    /**
     * 视频时长
     */
    private String time;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}