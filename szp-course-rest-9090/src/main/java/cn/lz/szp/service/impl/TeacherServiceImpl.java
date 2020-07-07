package cn.lz.szp.service.impl;

import cn.lz.szp.dao.TeacherDao;
import cn.lz.szp.pojo.dto.PageDTO;
import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.pojo.entity.Teacher;
import cn.lz.szp.pojo.query.TeacherQuery;
import cn.lz.szp.pojo.vo.TeacherVO;
import cn.lz.szp.pojo.vo.TypeVO;
import cn.lz.szp.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * csw
 * 2020/7/7
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    TeacherDao teacherDao;


    @Override
    public ResponseDTO add(Teacher teacher) {
        if (teacherDao.insertSelective(teacher)==1){
            return ResponseDTO.ok("添加成功");
        }
        return ResponseDTO.fail("添加失败");
    }

    @Override
    public PageDTO ajaxTeacherList(TeacherQuery query) {
        List<TeacherVO> vos = teacherDao.ajaxTeacherList(query);
        Integer count = teacherDao.ajaxTeacherListCount(query);
        return PageDTO.setPageData(count,vos);
    }

    @Override
    public ResponseDTO delete(Long userId) {
        if (teacherDao.deleteByPrimaryKey(userId)==1){
            return ResponseDTO.ok("删除成功");
        }
        return ResponseDTO.fail("删除失败");
    }

    @Override
    public ResponseDTO edit(Teacher teacher) {
        if (teacherDao.updateByPrimaryKeySelective(teacher)==1){
            return ResponseDTO.ok("修改成功");
        }
        return ResponseDTO.fail("修改失败");
    }
}
