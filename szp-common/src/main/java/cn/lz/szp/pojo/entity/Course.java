package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * course
 * @author 
 */
@Data
public class Course implements Serializable {
    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程主标题
     */
    private String title;

    /**
     * 课程附标题
     */
    private String subtitle;

    /**
     * 课程作者的user_id
     */
    private Long teacherId;

    /**
     * 发布时间
     */
    private Date releaseTime;

    /**
     * 课程版本
     */
    private String version;

    /**
     * 原价：单位是元
     */
    private BigDecimal originPrice;

    /**
     * 现价：单位是元
     */
    private BigDecimal nowPrice;

    /**
     * 课程主图
     */
    private String img;

    /**
     * 课程介绍详情（富文本）
     */
    private String detail;

    /**
     * 课程排序字段
     */
    private Integer sort;

    /**
     * 课程的观看次数
     */
    private Integer watchCount;

    /**
     * 是否在售在售1，否则0
     */
    private Boolean onSale;

    /**
     * 课程类型id
     */
    private Integer typeId;

    /**
     * 购买过此课程的用户ids，以逗号分割(平常情况下不需要查询)
     */
    private String boughtUsers;

    /**
     * 购买次数
     */
    private Integer boughtCount;

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