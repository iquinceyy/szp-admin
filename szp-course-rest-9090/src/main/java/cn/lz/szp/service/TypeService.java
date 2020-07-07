package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Type;
import cn.lz.szp.pojo.query.TypeQuery;

/**
 * csw
 * 2020/7/7
 */
public interface TypeService {
    ResponseDTO add(Type type);

    PageDTO ajaxCourseList(TypeQuery query);

    ResponseDTO delete(Integer typeId);

    ResponseDTO edit(Type type);
}
