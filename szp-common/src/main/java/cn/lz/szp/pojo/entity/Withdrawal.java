package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * withdrawal
 * @author 
 */
@Data
public class Withdrawal implements Serializable {
    /**
     * 余额提现id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 提现金额
     */
    private BigDecimal money;

    /**
     * 申请提现时间
     */
    private Date createTime;

    /**
     * 提现状态：待审批，已到账
     */
    private String status;

    /**
     * 支付宝账户id
     */
    private String alipayAccount;

    /**
     * 收款人姓名
     */
    private String alipayAccountName;

    /**
     * 到账时间
     */
    private Date paymentTime;

    /**
     * 说明
     */
    private String note;

    /**
     * 提现之前的账户余额
     */
    private BigDecimal before;

    /**
     * 提现之后的账户余额
     */
    private BigDecimal after;

    /**
     * 对应的账户流水
     */
    private Long accountFlowId;

    /**
     * 转账凭证
     */
    private String voucher;

    /**
     * 微信账户
     */
    private String weixinAccount;

    /**
     * 微信收款者
     */
    private String weixinReceiver;

    /**
     * 付款方式：微信转账，支付宝转账，微信分账，支付宝分账，微信付款到银行卡
     */
    private String payType;

    private static final long serialVersionUID = 1L;
}