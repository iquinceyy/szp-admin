package cn.lz.szp.config.webmvc;

import cn.lz.szp.config.interceptor.PrincipalValidate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/7/3 16:40
 * 秦程test合并111
 * 秦程test合并222
 * 第三次测试提交33333333周杰分支1
 * 第三次测试提交33333333周杰分支2  提交
 * 第4次测试提交44444444秦程分支3  提交
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    PrincipalValidate principalValidate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(principalValidate);
    }
}
