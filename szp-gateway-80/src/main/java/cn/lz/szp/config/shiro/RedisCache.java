package cn.lz.szp.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis自定义的缓存类
 *
 * @param <K>
 * @param <V>
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 自定义缓存类的构造方法
     *
     * @param redisTemplate
     */
    public RedisCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 通过key获取value
     *
     * @param k
     * @return
     * @throws CacheException
     */
    @Override
    public V get(K k) throws CacheException {
        return (V) this.redisTemplate.opsForValue().get(k.toString());
    }

    /**
     * 设置值
     *
     * @param k
     * @param v
     * @return
     * @throws CacheException
     */
    @Override
    public V put(K k, V v) throws CacheException {
        this.redisTemplate.opsForValue().set(k.toString(), v, 30, TimeUnit.MINUTES);// 三十分钟有效
        return v;
    }

    /**
     * 移除某个键值对
     *
     * @param k
     * @return
     * @throws CacheException
     */
    @Override
    public V remove(K k) throws CacheException {
        V val = this.get(k);
        this.redisTemplate.delete(k.toString());
        return val;
    }

    /**
     * 清空数据
     *
     * @throws CacheException
     */
    @Override
    public void clear() throws CacheException {
        this.redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();// 清空redis
                return true;
            }
        });
    }

    /**
     * 返回所有的key数量     * @return
     */
    @Override
    public int size() {
        return this.redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.keys("*".getBytes()).size();
            }
        });
    }

    /**
     * 返回所有的k的集合
     *
     * @return
     */
    @Override
    public Set<K> keys() {
        return this.redisTemplate.execute(new RedisCallback<Set<K>>() {
            @Override
            public Set<K> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Set<K> set = new HashSet<>();
                Set<byte[]> keys = redisConnection.keys("*".getBytes());
                Iterator<byte[]> iter = keys.iterator();
                while (iter.hasNext()) {
                    set.add((K) iter.next());
                }
                return set;

            }
        });
    }

    /**
     * 获取所有的值集合
     *
     * @return
     */
    @Override
    public Collection<V> values() {

        return this.redisTemplate.execute(new RedisCallback<Set<V>>() {
            @Override
            public Set<V> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Set<V> set = new HashSet<>();
                Set<byte[]> keys = redisConnection.keys("*".getBytes());
                Iterator<byte[]> iter = keys.iterator();
                while (iter.hasNext()) {
                    set.add((V) redisConnection.get(iter.next()));
                }
                return set;
            }
        });
    }
}
