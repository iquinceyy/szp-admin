package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Teacher;
import cn.lz.szp.pojo.query.TeacherQuery;

/**
 * csw
 * 2020/7/7
 */
public interface TeacherService {
    ResponseDTO add(Teacher teacher);

    PageDTO ajaxTeacherList(TeacherQuery query);

    ResponseDTO delete(Long userId);

    ResponseDTO edit(Teacher teacher);
}
