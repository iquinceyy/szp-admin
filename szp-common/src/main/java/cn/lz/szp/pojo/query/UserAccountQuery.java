package cn.lz.szp.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * quincey
 * Date 2020/7/3 18:22
 */

@Data
public class UserAccountQuery extends PageQuery{

    /**
     * 外键用户id
     */
    private Long userId;
    /**
     * 支付宝账户
     */
    private String alipayAccount;

    /**
     * 支付宝账户人名称
     */
    private String alipayAccountName;

    /**
     * 微信账户
     */
    private String weixinAccount;

//    /**
//     * 创建时间
//     */
//    private Date createTime;
//
//    /**
//     * 更新时间
//     */
//    private Date updateTime;
}
