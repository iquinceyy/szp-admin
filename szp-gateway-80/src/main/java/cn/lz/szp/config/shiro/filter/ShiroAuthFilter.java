package cn.lz.szp.config.shiro.filter;

import cn.lz.szp.pojo.dto.ResponseDTO;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * creator：森林杜夫人
 */
public class ShiroAuthFilter extends FormAuthenticationFilter {
    private RedisTemplate<String, Object> redisTemplate;

    public ShiroAuthFilter(RedisTemplate<String, Object> redisTemplate) {// 从构造方法里边将redistemplate传进来
        this.redisTemplate = redisTemplate;

    }

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * preHandle 判断是否登录之前的具体判断方法
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;// 向下转型
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;// 向下转型
        boolean authenticated = SecurityUtils.getSubject().isAuthenticated();// 拿到是否登录
        // 预检请求就是先看服务器接不接受我传递的不安全的头信息。
        if (!authenticated) {// 没有登录，浏览器是否是预检请求（预检请求就是指的是POST请求中，带了一些危险（不安全的）的头信息）
            if ("OPTIONS".equals(((HttpServletRequest) request).getMethod())) {// 浏览器的跨域请求，预检请求，直接通过
                return true;// 放行，尽管来你传，我接
            }
            String token = httpServletRequest.getHeader("authToken");// 自定义的头信息，不会被认为是安全的，从头信息里边拿出authToken

            if (StringUtils.isEmpty(token)) {// 这里需要相应一下这个请求头
                token = httpServletRequest.getHeader("sec-websocket-protocol");
                httpServletResponse.setHeader("sec-websocket-protocol", token);// 设置允许的所有域名来访
            }

            if (StringUtils.isEmpty(token)) {// 还是空的，就返回JSON字符串，我在过滤器里边怎么返回JSON字符串？？
                httpServletResponse.setCharacterEncoding("UTF-8");// 回传数据编码
                httpServletResponse.setContentType("application/json");// 设置回传为json格式
                httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");// 设置允许的所有域名来访
                httpServletResponse.getWriter().write(JSONObject.toJSONString(ResponseDTO.fail("您还没有登录!", null, 401, 401)));// 直接返回JSON数据
                return false;
            }
            // 有token,就要验证token的真实有效性
            Object simpleSession = redisTemplate.opsForValue().get(token);// 从redis里边取出token
            if (simpleSession == null) {// 没有token去登录吧
                httpServletResponse.setCharacterEncoding("UTF-8");// 回传数据编码
                httpServletResponse.setContentType("application/json");// 设置回传为json格式
                httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");// 设置允许的所有域名来访
                httpServletResponse.getWriter().write(JSONObject.toJSONString(ResponseDTO.fail("您还没有登录!", null, 401, 401)));
                return false;
            }
        }
        // 不是空的，就表示登录过了，就放行
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestedWith = httpServletRequest.getHeader("X-Requested-With");// 判断是否是ajax请求

        if (!StringUtils.isEmpty(requestedWith) && "XMLHttpRequest".equals(requestedWith)) {//如果是ajax返回指定格式数据
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write("未授权");
        } else {//如果是普通请求进行重定向
            httpServletResponse.sendRedirect("/403");// 重定向
        }
        return false;
    }
}
