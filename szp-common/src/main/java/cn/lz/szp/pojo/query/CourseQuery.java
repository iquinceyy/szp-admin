package cn.lz.szp.pojo.query;

import lombok.Data;

/**
 * csw
 * 2020/7/7
 */
@Data
public class CourseQuery extends PageQuery {
    /**
     * 课程名字
     */
    private String title;


    /**
     * 课程简写
     */
    private String subtitle;

}
