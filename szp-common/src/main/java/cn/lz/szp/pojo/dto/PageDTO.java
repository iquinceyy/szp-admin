package cn.lz.szp.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * quincey
 * Date 2020/7/3 16:34
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    private Integer code = 0;

    private String msg = "";

    private Integer count = 0;

    private Object data;
    private Object moreData;

    public static PageDTO setPageData(Integer count, Object data) {
        return new PageDTO(0, "成功", count, data,null);
    }
    public static PageDTO setPageData(Integer count, Object data, Object moreData) {
        return new PageDTO(0, "成功", count, data,moreData);
    }
}
