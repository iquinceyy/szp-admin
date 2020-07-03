package cn.lz.szp.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * pay_log
 * @author 
 */
@Data
public class PayLog implements Serializable {
    /**
     * 付款日志id
     */
    private Long payLogId;

    /**
     * 付款内容
     */
    private String content;

    /**
     * 付款用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 付款金额
     */
    private BigDecimal money;

    /**
     * 付款人昵称（冗余字段）
     */
    private String nickName;

    /**
     * 付款人头像（冗余字段）
     */
    private String userImg;

    private static final long serialVersionUID = 1L;
}