package cn.lz.szp.pojo.query;

import lombok.Data;

/**
 * csw
 * 2020/7/7
 */
@Data
public class TypeQuery extends PageQuery {
    /**
     * 类型名称
     */
    private String name;
}
