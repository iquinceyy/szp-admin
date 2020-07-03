package cn.lz.szp.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component// 创建自定义的缓存管理器
public class RedisCacheManager implements CacheManager {
    /**
     * 适合高并发的时候使用,ConcurrentMap 在高并发的时候，是安全的，
     */
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        Cache<K, V> cache = this.caches.get(s);
        if (cache == null) {
            cache = new RedisCache<>(this.redisTemplate);
            this.caches.put(s, cache);
        }
        return cache;
    }

}
