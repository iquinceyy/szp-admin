package cn.lz.szp.config.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * quincey
 * Date 2020/7/3 16:40
 * redis一个配置文件类
 */
@SpringBootConfiguration
public class RedisConfig {
    Logger logger = LoggerFactory.getLogger(RedisConfig.class);
    @Resource
    Environment environment;

    /**
     * redis的集群配置
     *
     * @return
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        String HOST = "120.25.223.121";// 本地hosts文件中配置 dev环境填写公网ip

        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 0) {
            if ("pro".equals(activeProfiles[0])) {
                HOST = "172.18.25.51";// 正式环境填写内网ip
            }
        }
        logger.info("RRRRRRRRRRRRR=========Redis地址：" + HOST);
        // redis集群配置
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

        redisClusterConfiguration.setPassword("java");// 设置集群密码
        redisClusterConfiguration.clusterNode(HOST, 6371);// 第一个节点
        redisClusterConfiguration.clusterNode(HOST, 6372);
        redisClusterConfiguration.clusterNode(HOST, 6373);
        redisClusterConfiguration.clusterNode(HOST, 6374);
        redisClusterConfiguration.clusterNode(HOST, 6375);
        redisClusterConfiguration.clusterNode(HOST, 6376);


        //redis连接池配置
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();

        genericObjectPoolConfig.setMaxIdle(8);// 最大空闲连接数量

        genericObjectPoolConfig.setMaxTotal(100);// 连接总数 100个


        genericObjectPoolConfig.setMinIdle(1);// 最小空闲连接数

        genericObjectPoolConfig.setMaxWaitMillis(-1);// 最大等待时间（-1就表示不限制）
        //redis客户端配置（jedis在这里不够号，我们用Lettuce：相当于是对jedis的一个封装）
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
                builder = LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofSeconds(60));// 默认就是60秒命令超时
//        builder.shutdownTimeout(Duration.ofMillis(shutdownTimeOut));
        builder.poolConfig(genericObjectPoolConfig);// 设置连接吃配置
        LettuceClientConfiguration lettuceClientConfiguration = builder.build();// 创建客户端
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
        return lettuceConnectionFactory;
    }

    /**
     * 跟http通信模板是：restTemplate
     * 跟redis通信的是redisTemplate
     * string=>key
     * Objet=>value
     * 就是增删改查redis的模板（dao）
     *
     * @param lettuceConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory lettuceConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory); // 设置redis连接工厂
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 设置key序列化对象类型String redis
        // redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());// 设置 value的序列化器，就会保存二进制数据
        return redisTemplate;
    }

    /**
     * 这个可以其实不用配置：
     * 监听redis的key删除的时候，触发一个监听器。还有比如订单下单15分钟超时未支付，我们就去修改订单付款状态。
     * 监听这个key:不可靠，有的时候由于网络等原因，他可能无法正常监听key删除。所以是不可靠的消息推送。（了解）
     * mq消息组件（activeMQ(性能差，老技术)\RabbitMQ（知名度广，性能高，功能稍微没有RocketMQ强）\RocketMQ(阿里的专门用来做电商的消息推送)\kafka（主要是做大数据日志处理，性能最佳，高并发最佳）这个也不难（确实不会））
     *
     * @param lettuceConnectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory);
        return container;
    }

}
