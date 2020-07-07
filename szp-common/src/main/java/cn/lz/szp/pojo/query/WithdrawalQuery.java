package cn.lz.szp.pojo.query;

import lombok.Data;

/**
 * quincey
 * Date 2020/7/3 18:21
 */

@Data
public class WithdrawalQuery extends PageQuery{

    /**
     * 微信账户
     */
    private String weixinAccount;

    /**
     * 微信收款者
     */
    private String weixinReceiver;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 支付宝账户id
     */
    private String alipayAccount;

    /**
     * 收款人姓名
     */
    private String alipayAccountName;
}
