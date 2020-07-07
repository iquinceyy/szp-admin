package cn.lz.szp.service;

import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Subsection;
import cn.lz.szp.pojo.query.SubsectionQuery;

/**
 * csw
 * 2020/7/7
 */
public interface SubsectionService {
    ResponseDTO add(Subsection subsection);

    PageDTO ajaxSubsectionList(SubsectionQuery query);

    ResponseDTO delete(Integer id);

    ResponseDTO edit(Subsection subsection);
}
