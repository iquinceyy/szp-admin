package cn.lz.szp.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * creator：Administrator
 * date:2019/11/26
 */
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {// 继承了企业级的缓存会话Dao
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    public static final String SESSION_PREFIX = "SZP_SESSION_";// 统一给sessionId加前缀

    // 到Redis里边去增删改查
    @Override
    protected Serializable doCreate(Session session) {// 当前的这个session对象，将它保存到redis里边去
//        Serializable sessionid = super.doCreate(session);
        Serializable sessionId = SESSION_PREFIX + UUID.randomUUID();// 给前端的token: SZP_SESSION_UUID
        this.assignSessionId(session, sessionId);
        // 创建session之后，将session存入redis,并且指定时长为30分钟；
        this.redisTemplate.opsForValue().set(sessionId.toString(), session, 30, TimeUnit.MINUTES);// 保存三十分钟
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {// 从redis读取
        Session session = super.doReadSession(sessionId);// 从父类读取session
        if (session == null) {// 读取不到，就从redis读取
            return (Session) this.redisTemplate.opsForValue().get(sessionId.toString());
        }
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {// 更新
//        super.doUpdate(session);
        this.redisTemplate.opsForValue().set(session.getId().toString(), session, 30, TimeUnit.MINUTES);// 刷新session的过期时间
    }

    @Override
    public void delete(Session session) {// 验证Session是否过期之后，不能删除Session,必须配置在sessionManager.setDeleteInvalidSessions(false);// 不要删除过期的Session,Redis自己删除!!
//        super.doDelete(session);
        this.redisTemplate.delete(session.getId().toString());
    }

    @Override // 获取当前所有激活的session,也就是拿到当前程序中的所有在线用户
    public Collection<Session> getActiveSessions() { // 获取所有在线的Session
        Set<String> keys = redisTemplate.keys(SESSION_PREFIX + "*");// 找到以什么开头的键
        Set<Session> sessions = new HashSet<>();
        if (!CollectionUtils.isEmpty(keys)) {
            List<Object> objects = redisTemplate.opsForValue().multiGet(keys);// 一次多取出key
            if (!CollectionUtils.isEmpty(objects)) {
                for (Object object : objects) {
                    if (object instanceof Session) {
                        sessions.add((Session) object);
                    }
                }
            }
        }
        return sessions;
    }
}
