package cn.lz.szp.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quincey
 * Date 2020/7/3 16:39
 * 主题（路由）交换机
 */

@Configuration
public class TopicExchangeConfig {// 主题模式，也叫做路由模式
    // 声明一个队列，名字叫做 IANFENG_JAVA_QUEUE_1
    public static final String QIANFENG_JAVA_QUEUE_5 = "QIANFENG_JAVA_QUEUE_5";// 因为我后面要用（监听消息的时候，要指明队列的名称，绑定道交换机的时候也要用）
    // 声明一个队列，名字叫做 IANFENG_JAVA_QUEUE_2
    public static final String QIANFENG_JAVA_QUEUE_6 = "QIANFENG_JAVA_QUEUE_6";
    public static final String QIANFENG_JAVA_QUEUE_7 = "QIANFENG_JAVA_QUEUE_7";

    // 声明一个队列，名字叫做 QIANFENG_JAVA_FANOUT_EXCHANGE
    public static final String QIANFENG_JAVA_TOPIC_EXCHANGE = "QIANFENG_JAVA_TOPIC_EXCHANGE";//（发送消息的时候，要指明发送道哪个交换机）


    public static final String QIANFENG_JAVA_ROUTINGKEY_5 = "qianfeng.java.h5";//  路由键就是跟队列有很强的关系

    public static final String QIANFENG_JAVA_ROUTINGKEY_6 = "qianfeng.java.java";//  路由键就是跟队列有很强的关系

    public static final String QIANFENG_JAVA_ROUTINGKEY_7 = "qianfeng.h5.java";//  路由键就是跟队列有很强的关系
    public static final String topicRotingKey = "qianfeng.*";//  路由键就是跟队列有很强的关系


    // 创建两个队列的目的：是要证明绑定道同一个广播交换机的队列都能收到同样的消息。

    @Bean// 给队列在容器中取了一个名字叫queue1
    public Queue queue5() {// 创建一个队列，交给spring容器管理
        return new Queue(QIANFENG_JAVA_QUEUE_5);// 默认是持久队列。持久队列和持久交换机，指的是在rabbitmq宕机之后，再次启动，会加载之前的消息。
    }

    @Bean
    public Queue queue6() {
        return new Queue(QIANFENG_JAVA_QUEUE_6);
    }

    @Bean
    public Queue queue7() {
        return new Queue(QIANFENG_JAVA_QUEUE_7);
    }

    @Bean
    public TopicExchange topicExchange() {  // 这个是一个广播模式的交换机
        return new TopicExchange(QIANFENG_JAVA_TOPIC_EXCHANGE);
    }


    // 将指定队列queue1绑定到指定交换机 fanoutExchange1
    @Bean
    public Binding queue5BindFanoutExchange(TopicExchange topicExchange, Queue queue5) {  // 这个是一个广播模式的交换机 就好比这个队列的唯一标识
        return BindingBuilder.bind(queue5).to(topicExchange).with(QIANFENG_JAVA_ROUTINGKEY_5); // 绑定队列到交换机,还需要一个routingKey(路由键)
    }

    // 将指定队列queue2绑定到指定交换机 fanoutExchange1
    @Bean
    public Binding queue6BindFanoutExchange(TopicExchange topicExchange, Queue queue6) {  // 这个是一个广播模式的交换机
        return BindingBuilder.bind(queue6).to(topicExchange).with(QIANFENG_JAVA_ROUTINGKEY_6); // 绑定队列到交换机
    }

    // 将指定队列queue2绑定到指定交换机 fanoutExchange1
    @Bean
    public Binding queue7BindFanoutExchange(TopicExchange topicExchange, Queue queue7) {  // 这个是一个广播模式的交换机
        return BindingBuilder.bind(queue7).to(topicExchange).with(QIANFENG_JAVA_ROUTINGKEY_7); // 绑定队列到交换机
    }


}
