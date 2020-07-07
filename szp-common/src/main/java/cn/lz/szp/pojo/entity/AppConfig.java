package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * app_config
 * @author 
 */
@Data
public class AppConfig implements Serializable {
    /**
     * 配置id
     */
    private Integer configId;

    /**
     * 配置的键名
     */
    private String key;

    /**
     * 配置的值：可以是url地址，可以是json字符串对象，可以是基本类型，详见配置说明
     */
    private String value;

    /**
     * 配置说明
     */
    private String note;

    /**
     * 是否是json字符串？
     */
    private Boolean isJson;

    /**
     * 反射类的全路径包名称？
     */
    private String className;

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