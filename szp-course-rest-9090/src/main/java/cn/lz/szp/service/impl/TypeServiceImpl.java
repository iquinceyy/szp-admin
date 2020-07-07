package cn.lz.szp.service.impl;

import cn.lz.szp.dao.TypeDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Type;
import cn.lz.szp.pojo.query.TypeQuery;
import cn.lz.szp.pojo.vo.CourseVO;
import cn.lz.szp.pojo.vo.TypeVO;
import cn.lz.szp.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * csw
 * 2020/7/7
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    TypeDao typeDao;


    @Override
    public ResponseDTO add(Type type) {
        if (typeDao.insertSelective(type)==1){
            return ResponseDTO.ok("添加成功");
        }
        return ResponseDTO.fail("添加失败");
    }

    @Override
    public PageDTO ajaxCourseList(TypeQuery query) {
        List<TypeVO> vos = typeDao.ajaxCourseList(query);
        Integer count = typeDao.ajaxCourseListCount(query);
        return PageDTO.setPageData(count,vos);
    }

    @Override
    public ResponseDTO delete(Integer typeId) {
        if (typeDao.deleteByPrimaryKey(typeId)==1){
            return ResponseDTO.ok("删除成功");
        }
        return ResponseDTO.fail("删除失败");
    }

    @Override
    public ResponseDTO edit(Type type) {

        if (typeDao.updateByPrimaryKeySelective(type)==1){
            return ResponseDTO.ok("修改成功");
        }
        return ResponseDTO.fail("修改失败");
    }
}
