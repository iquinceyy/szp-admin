package cn.lz.szp.config.rabbitmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quincey
 * Date 2020/7/3 16:39
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("120.25.223.121");// 设置rabbitmq的主机，单机版。（镜像rabbitmq集群：eureka）
        cachingConnectionFactory.setPort(5672);// 端口
        cachingConnectionFactory.setUsername("admin");// 账户名
        cachingConnectionFactory.setPassword("java");// 密码
        cachingConnectionFactory.setVirtualHost("/");// 设置虚拟主机路径
        cachingConnectionFactory.setConnectionTimeout(5000);// 设置超时时间
        cachingConnectionFactory.setPublisherReturns(true);// 开启发送消息回调
        // 当服务器收到消息之后，就会通知消息确认机制，这样就确保服务器收到了消息。// 啄木鸟用的阿里云的付费版
        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);// 设置消息确认机制（确保消息的可靠性）
        return cachingConnectionFactory;
    }

}
