package cn.lz.szp.config.shiro;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义的webSessionManger 因为如果不自定，shiro会每次都从redis直接更新，会导致redis会频繁更新，浪费性能
 */
public class ShiroWebSessionManager extends DefaultWebSessionManager {
    /**
     * 定义的请求头中使用的标记key，用来传递 token
     */
    private static final String AUTH_TOKEN = "authToken";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /**
     * 15      * 获取session
     * 16      * 优化单次请求需要多次访问redis的问题
     * 17      * @param sessionKey
     * 18      * @return
     * 19      * @throws UnknownSessionException
     * 20
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);// 拿到session
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();// 拿到请求对象
        }
        if (request != null && null != sessionId) {// 如果当前的Requet不是null,那么就直接从Request把Session取出来，这样就可以避免多次（好几十次读取redis）
            Object sessionObj = request.getAttribute(sessionId.toString());// 从request对象里边获取session
            if (sessionObj != null) {
                return (Session) sessionObj;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
    /**
     * 覆写读取SessionId的方法
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 用户登录过后，请求头里边必须加上
        //获取请求头中的 AUTH_TOKEN 的值，如果请求头中有 AUTH_TOKEN 则其值为sessionId。shiro就是通过sessionId 来控制的
        String sessionId = WebUtils.toHttp(request).getHeader(AUTH_TOKEN);// 可能拿不到，是因为被shiro拦截了，也可能是因为浏览器的预检请求
        if (StringUtils.isEmpty(sessionId)) {// 如果是null，就调用父类的获取SessionId的方法
            //如果没有携带id参数则按照父类的方式在cookie进行获取sessionId
            return super.getSessionId(request, response);
        } else {
            //请求头中如果有 authToken, 则其值为sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            //sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }
    }

}
