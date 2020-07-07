package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * type
 * @author 
 */
@Data
public class Type implements Serializable {
    /**
     * 课程类型id,顶级的parent_id为0
     */
    private Integer typeId;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 父级类型id（顶级类型应该是0）
     */
    private Integer parentId;

    /**
     * 排序字段，越小越在前面
     */
    private Integer sort;

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