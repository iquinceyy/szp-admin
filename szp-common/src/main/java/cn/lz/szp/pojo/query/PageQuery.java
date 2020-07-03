package cn.lz.szp.pojo.query;

import lombok.Data;

/**
 * quincey
 * Date 2020/7/3 16:41
 */
@Data
public class PageQuery {
    private Integer page = 1;
    private Integer limit = 10;
    private Integer start = 0;// 偏移量应该计算出来

    public Integer getStart() {
        if (page != null && limit != null) {
            return (page - 1) * limit;
        }
        return start;
    }
}
