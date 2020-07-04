package cn.lz.szp.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * quincey
 * Date 2020/7/3 18:21
 */

@Data
public class PayLogQuery extends PageQuery{
    /**
     * 付款用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 付款人昵称（冗余字段）
     */
    private String nickName;
}
