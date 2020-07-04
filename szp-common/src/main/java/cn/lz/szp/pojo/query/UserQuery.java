package cn.lz.szp.pojo.query;

import lombok.Data;

/**
 * csw
 * 2020/7/4
 */
@Data
public class UserQuery extends PageQuery {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户昵称
     */
    private String nickName;
}
