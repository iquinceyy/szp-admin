package cn.lz.szp.config.shiro;

import cn.lz.szp.config.shiro.filter.ShiroAuthFilter;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

// 这样springboot才会去读这个类
@SpringBootConfiguration
public class ShiroConfig {
    // 的SessionId CookieName
    public static final String SESSION_ID = "szp-session-id";

    @Bean
    public UserRealm getRealm() {// 1、获取配置的Realm，之所以没使用注解配置，是因为此处需要考虑到加密处理
        UserRealm realm = new UserRealm();// 创建自定义的realm
//        realm.setCredentialsMatcher(new CustomerCredentialsMatcher());// 密码匹配认证器
        return realm;
    }

    // 生命周期不能删，删除会造成死循环
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public SessionIdGenerator getSessionIdGenerator() { // 3 设置sessionID生成器
        return new JavaUuidSessionIdGenerator();// UUID来做的
    }

    // 会话SessionDao,RedisSessionDAO是需要自己去写的
    @Bean
    public SessionDAO getSessionDAO(SessionIdGenerator sessionIdGenerator) { // 4
        RedisSessionDAO sessionDAO = new RedisSessionDAO();// 自定义的sessionDao
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache"); // 配置缓存的名字
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);// 设置SessionId生成器
        return sessionDAO;
    }

    // 定时调度检测Session是否过期的一个调度器
    @Bean
    public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        sessionValidationScheduler.setInterval(30 * 60 * 1000);// 设置循环调度的时间30分钟
        sessionValidationScheduler.disableSessionValidation();// 关闭验证Session
        return sessionValidationScheduler;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    //解决redis同一个请求被读取了很多次，要用自己的,自定义，加强操作
    @Bean
    public ShiroWebSessionManager getShiroWebSessionManager(SessionDAO sessionDAO, ExecutorServiceSessionValidationScheduler sessionValidationScheduler) { // 6
        ShiroWebSessionManager sessionManager = new ShiroWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800 * 1000); //设置全局Session超时时间30分钟
        sessionManager.setDeleteInvalidSessions(true); //过期删除session
        sessionManager.setSessionValidationScheduler(sessionValidationScheduler); //session的定时检验器
        sessionManager.setSessionValidationSchedulerEnabled(false); // 能够启动定时session检验（关闭）
        sessionManager.setDeleteInvalidSessions(false);// 不要删除过期的Session,Redis自己删除!!（到期自动删除）
        sessionManager.setSessionDAO(sessionDAO);
        SimpleCookie sessionIdCookie = new SimpleCookie(SESSION_ID);
        sessionIdCookie.setHttpOnly(true); // Cookie只能通过http获取，不能通过js获取
        sessionIdCookie.setMaxAge(-1);//  设置Cookie过期时间，永不过期
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdCookieEnabled(true);// /开启Cookie的使用
        //网址后不加jsessionid
        sessionManager.setSessionIdUrlRewritingEnabled(true);//浏览器之中会出现SessionId
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(UserRealm userRealm, RedisCacheManager redisCacheManager,
                                                        SessionManager sessionManager) {// 7
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setCacheManager(redisCacheManager);// 不要导错包，是Shiro的
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    // 自定义登录过滤器
    public ShiroAuthFilter getLoginFilter(RedisTemplate<String, Object> redisTemplate) { // 在ShiroFilterFactoryBean中使用
        ShiroAuthFilter filter = new ShiroAuthFilter(redisTemplate);// 设置自定义的登录过滤器
        return filter;
    }

    public LogoutFilter getLogoutFilter() { // 在ShiroFilterFactoryBean中使用
        LogoutFilter logoutFilter = new LogoutFilter();// 自定义的退出登录过滤器
        logoutFilter.setRedirectUrl("/logoutMy");    // 首页路径，登录注销后回到的页面,此时执行方法路径
        return logoutFilter;
    }

    @Bean
    @DependsOn("redisTemplate") // 依赖于在redisTemplate被实例化之后才创建
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, RedisTemplate redisTemplate) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/loginPage");        // 登陆错误之后，去访问登录页--都是controller的方法
        shiroFilterFactoryBean.setSuccessUrl("/pages/back/loginSuccess");    // 设置登陆成功执行方法的路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthUrl");    // 没有授权，后执行的方法，此时是方法

        Map<String, Filter> filters = new HashMap<>();// 创建过滤链
        filters.put("checkLogin", getLoginFilter(redisTemplate));// 自定义名称为 检测登录
        filters.put("logout", this.getLogoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/login", "user");    // 定义内置登录处理,表单需要加method=post
//        filterChainDefinitionMap.put("/back/**", "checkLogin");//pages/back/**这样的路径需要登陆检测
        filterChainDefinitionMap.put("/*", "anon");// 所有不用检测
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


}
