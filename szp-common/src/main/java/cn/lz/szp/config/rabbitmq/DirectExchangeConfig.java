package cn.lz.szp.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quincey
 * Date 2020/7/3 16:38
 * 直连交换机
 */

@Configuration
public class DirectExchangeConfig {
    // 声明一个队列，名字叫做 IANFENG_JAVA_QUEUE_1
    public static final String QIANFENG_JAVA_QUEUE_3 = "QIANFENG_JAVA_QUEUE_3";// 因为我后面要用（监听消息的时候，要指明队列的名称，绑定道交换机的时候也要用）
    // 声明一个队列，名字叫做 IANFENG_JAVA_QUEUE_2
    public static final String QIANFENG_JAVA_QUEUE_4 = "QIANFENG_JAVA_QUEUE_4";
    // 声明一个队列，名字叫做 QIANFENG_JAVA_FANOUT_EXCHANGE
    public static final String QIANFENG_JAVA_DIRECT_EXCHANGE = "QIANFENG_JAVA_DIRECT_EXCHANGE";//（发送消息的时候，要指明发送道哪个交换机）


    public static final String QIANFENG_JAVA_ROUTINGKEY_3 = "QIANFENG_JAVA_ROUTINGKEY_3";//  路由键就是跟队列有很强的关系

    public static final String QIANFENG_JAVA_ROUTINGKEY_4 = "QIANFENG_JAVA_ROUTINGKEY_4";//  路由键就是跟队列有很强的关系


    // 创建两个队列的目的：是要证明绑定道同一个广播交换机的队列都能收到同样的消息。

    @Bean// 给队列在容器中取了一个名字叫queue1
    public Queue queue3() {// 创建一个队列，交给spring容器管理
        return new Queue(QIANFENG_JAVA_QUEUE_3);// 默认是持久队列。持久队列和持久交换机，指的是在rabbitmq宕机之后，再次启动，会加载之前的消息。
    }

    @Bean("queue4")
    public Queue queue4() {
        return new Queue(QIANFENG_JAVA_QUEUE_4);
    }

    @Bean
    public DirectExchange directExchange() {  // 这个是一个广播模式的交换机
        return new DirectExchange(QIANFENG_JAVA_DIRECT_EXCHANGE);
    }


    // 将指定队列queue1绑定到指定交换机 fanoutExchange1
    @Bean
    public Binding queue3BindFanoutExchange(DirectExchange directExchange, Queue queue3) {  // 这个是一个广播模式的交换机 就好比这个队列的唯一标识
        return BindingBuilder.bind(queue3).to(directExchange).with(QIANFENG_JAVA_ROUTINGKEY_3); // 绑定队列到交换机,还需要一个routingKey(路由键)
    }

    // 将指定队列queue2绑定到指定交换机 fanoutExchange1
    @Bean
    public Binding queue4BindFanoutExchange(DirectExchange directExchange, Queue queue4) {  // 这个是一个广播模式的交换机
        return BindingBuilder.bind(queue4).to(directExchange).with(QIANFENG_JAVA_ROUTINGKEY_4); // 绑定队列到交换机
    }

}
