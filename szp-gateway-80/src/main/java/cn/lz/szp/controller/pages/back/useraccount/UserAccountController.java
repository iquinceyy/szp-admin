package cn.lz.szp.controller.pages.back.useraccount;

import cn.lz.szp.service.UserAccountServiceFeign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/7/3 20:11
 */

@RestController
@RequestMapping("/back/useraccount/useraccount/")
public class UserAccountController {
    
    @Resource
    UserAccountServiceFeign userAccountServiceFeign;
}
