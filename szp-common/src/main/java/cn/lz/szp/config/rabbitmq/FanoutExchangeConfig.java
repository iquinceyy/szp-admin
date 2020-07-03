package cn.lz.szp.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quincey
 * Date 2020/7/3 16:39
 * 广播交换机
 */

@Configuration
public class FanoutExchangeConfig {
    // 声明一个队列，名字叫做 IANFENG_JAVA_QUEUE_1
    public static final String QIANFENG_JAVA_QUEUE_1 = "QIANFENG_JAVA_QUEUE_1";// 因为我后面要用（监听消息的时候，要指明队列的名称，绑定道交换机的时候也要用）
    // 声明一个队列，名字叫做 IANFENG_JAVA_QUEUE_2
    public static final String QIANFENG_JAVA_QUEUE_2 = "QIANFENG_JAVA_QUEUE_2";
    // 声明一个队列，名字叫做 QIANFENG_JAVA_FANOUT_EXCHANGE
    public static final String QIANFENG_JAVA_FANOUT_EXCHANGE = "QIANFENG_JAVA_FANOUTEXCHANGE";//（发送消息的时候，要指明发送道哪个交换机）

    public static final String TEST_FANOUTEXCHANGE = "test";

    // 创建两个队列的目的：是要证明绑定道同一个广播交换机的队列都能收到同样的消息。

    @Bean("queue1")// 给队列在容器中取了一个名字叫queue1
    public Queue queue1() {// 创建一个队列，交给spring容器管理
        return new Queue(QIANFENG_JAVA_QUEUE_1);// 默认是持久队列。持久队列和持久交换机，指的是在rabbitmq宕机之后，再次启动，会加载之前的消息。
    }

    @Bean("queue2")
    public Queue queue2() {
        return new Queue(QIANFENG_JAVA_QUEUE_2);
    }

    @Bean
    public FanoutExchange testFanoutExchange() {  // 这个是一个广播模式的交换机
        return new FanoutExchange(TEST_FANOUTEXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange1() {  // 这个是一个广播模式的交换机
        return new FanoutExchange(QIANFENG_JAVA_FANOUT_EXCHANGE);
    }

    // 将指定队列queue1绑定到指定交换机 fanoutExchange1
    @Bean
    public Binding queue1BindFanoutExchange(FanoutExchange fanoutExchange1, Queue queue1) {  // 这个是一个广播模式的交换机
        return BindingBuilder.bind(queue1).to(fanoutExchange1); // 绑定队列到交换机
    }
    // 将指定队列queue2绑定到指定交换机 fanoutExchange1
    @Bean
    public Binding queue2BindFanoutExchange(FanoutExchange fanoutExchange1, Queue queue2) {  // 这个是一个广播模式的交换机
        return BindingBuilder.bind(queue2).to(fanoutExchange1); // 绑定队列到交换机
    }

}
