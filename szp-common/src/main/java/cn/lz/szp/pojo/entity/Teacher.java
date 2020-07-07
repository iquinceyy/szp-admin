package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * teacher
 * @author 
 */
@Data
public class Teacher implements Serializable {
    /**
     * 作者id(就是userId)
     */
    private Long userId;

    /**
     * 讲师姓名
     */
    private String name;

    /**
     * 讲师简介
     */
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    private String career;

    /**
     * 头衔 1高级讲师 2首席讲师
     */
    private Integer level;

    /**
     * 讲师头像
     */
    private String userImg;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    private Byte isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 学生数量
     */
    private Integer studentCount;

    /**
     * 关注数量
     */
    private Integer fansCount;

    private static final long serialVersionUID = 1L;
}