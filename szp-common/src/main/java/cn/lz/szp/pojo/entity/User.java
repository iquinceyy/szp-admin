package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 用户主键id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 真名字
     */
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱可以为null
     */
    private String email;

    /**
     * 上次登录ip:检测用户异地登录
     */
    private String lastLoginIp;

    /**
     * 上次登录日期
     */
    private Date lastLoginTime;

    /**
     * 角色组，逗号分隔
     */
    private String roles;

    /**
     * 备注
     */
    private String note;

    /**
     * 用户头像地址
     */
    private String photo;

    /**
     * 微信号的open_id（可以授权登录以及付款）
     */
    private String openId;

    /**
     * 用户已经购买的课程ids：1,2,3
     */
    private String courseIds;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 最近更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}