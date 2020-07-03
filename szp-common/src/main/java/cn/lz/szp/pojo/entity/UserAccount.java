package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * user_account
 * @author 
 */
@Data
public class UserAccount implements Serializable {
    /**
     * 外键用户id
     */
    private Long userId;

    /**
     * 余额（可以提现的）
     */
    private BigDecimal balance;

    /**
     * 学币(只能拿来买课程)
     */
    private BigDecimal xuebi;

    /**
     * 佣金比例（分销课程佣金比例）
     */
    private BigDecimal commission;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}