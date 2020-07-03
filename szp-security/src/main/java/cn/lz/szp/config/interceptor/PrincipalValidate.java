package cn.lz.szp.config.interceptor;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * quincey
 * Date 2020/7/3 16:40
 */
@Component
public class PrincipalValidate implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 这里还应该用redis来做一个 IP访问错误次数的拦截啊。
        String requestURI = request.getRequestURI();
        Enumeration<String> headerNames = request.getHeaderNames();


        // 先看你是不是白名单。如果是白名单，才让你比较头信息，否则，直接拦截。
        StringBuffer requestURL = request.getRequestURL();
        String authorization = request.getHeader("authorization");// 从头信息里边取出加密字符串（传过来的）
        String s = "Basic " + Base64.encode(("qianfeng:java").getBytes(Charset.forName("ISO_8859_1")));
        // 这里有个空格的细节
        // 如果比较错误一次，我就记录你错误的次数，如果在短时间内达到三次或者错误，就认为你是恶意请求，拖入黑名单

        return s.equals(authorization);// 相等就放行，不相等就拦截
    }
}
