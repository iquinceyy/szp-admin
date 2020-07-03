package cn.lz.szp.controller.pages.back.user;

import cn.lz.szp.pojo.dto.ResponseDTO;
import cn.lz.szp.service.UserServiceFeign;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * creator：杜夫人
 * date: 2020/7/3
 */
@RestController
@RequestMapping("/back/user")
public class UserController {

    @Resource
    UserServiceFeign userServiceFeign;

    @RequestMapping("findUserById/{id}")
    ResponseDTO findUserById(@PathVariable Long id) {
        return userServiceFeign.findUserById(id);
    }

}
