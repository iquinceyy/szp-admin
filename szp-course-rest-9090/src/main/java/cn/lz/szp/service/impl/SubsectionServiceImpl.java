package cn.lz.szp.service.impl;

import cn.lz.szp.dao.SubsectionDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Subsection;
import cn.lz.szp.pojo.query.SubsectionQuery;
import cn.lz.szp.pojo.vo.SubsectionVO;
import cn.lz.szp.pojo.vo.TypeVO;
import cn.lz.szp.service.SubsectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * csw
 * 2020/7/7
 */

@Service
public class SubsectionServiceImpl implements SubsectionService {

    @Resource
    SubsectionDao subsectionDao;

    @Override
    public ResponseDTO add(Subsection subsection) {
        if (subsectionDao.insertSelective(subsection)==1){
            return ResponseDTO.ok("添加成功");
        }
        return ResponseDTO.fail("添加失败");
    }

    @Override
    public PageDTO ajaxSubsectionList(SubsectionQuery query) {
        List<SubsectionVO> vos = subsectionDao.ajaxCourseList(query);
        Integer count = subsectionDao.ajaxCourseListCount(query);
        return PageDTO.setPageData(count,vos);
    }

    @Override
    public ResponseDTO delete(Integer id) {
        if (subsectionDao.deleteByPrimaryKey(id)==1){
            return ResponseDTO.ok("删除成功");
        }
        return ResponseDTO.fail("删除失败");
    }

    @Override
    public ResponseDTO edit(Subsection subsection) {

        if (subsectionDao.updateByPrimaryKeySelective(subsection)==1){
            return ResponseDTO.ok("修改成功");
        }
        return ResponseDTO.fail("修改失败");
    }
}
