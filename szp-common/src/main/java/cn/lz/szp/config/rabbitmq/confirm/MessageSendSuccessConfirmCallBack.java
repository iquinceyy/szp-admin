package cn.lz.szp.config.rabbitmq.confirm;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/7/3 16:37
 */
@Component// 注入到容器之中
public class MessageSendSuccessConfirmCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    // 匿名内部类
    // 匿名静态内部类
    // 类中接口
    // 类中枚举
    // 类中注解

    @Resource
    RabbitTemplate rabbitTemplate;


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {// 进入到这里，已经表明消息组件已经收到消息了
        if (ack) {  // 几乎不会丢失有业务 这个就叫目前比较好的一种解决分布式事务的方案，仍然不是完美
//            Object o = rabbitTemplate.getMessageConverter().fromMessage(correlationData.getReturnedMessage());
//            System.err.println(o);
            String id = correlationData.getId();
            if(id.startsWith("tb_user")){
                // 把用户id取出来，做事情
            }else if(id.startsWith("tb_order")){// 订单相关的业务消息




            }



            System.err.println(correlationData);// 取出消息id，就知道是哪个消息返回的结果， 然后做相应的处理
            System.err.println("消息发送成功");
            // 借鉴阿里巴巴 seata
            // 这里就应该提交我们之前的业务？
            // 这里需要框架支持，或者自定义支持，从消息id里边取出之前的消息，然后对它进行回滚或者提交
//            System.err.println(correlationData.getId());
            // 才在这里真正的做业务处理（上半游业务处理）
            // 一定会有日志的记录
            // 事务控制
            // 消息发送成功了，就不管
        } else {
            // 回滚
            // 把刚才那个订单？取消掉，如果已经付款，就退款。
            // 可以把之前的表数据结构(快照记录下来)（version）记录下来。。 跟随消息一起发送
            // 还原 ok
            // 比如自增订单。 seata的原理（实现很麻烦的。所以有现成框架。elastic-job\seata(只需要一个注解)）

            System.err.println("消息发送失败");
        }
    }

    @PostConstruct// 当实例创建之初，就执行此方法/
    public void init() {
        rabbitTemplate.setConfirmCallback(this);// 应该在这个类创建之后，初始化的时候，就要执行！
        rabbitTemplate.setReturnCallback(this);
    }

    // 几乎不会用到，交换机没有正确的将消息分配到队列的时候的消息回调函数
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.err.println(message);// 发送的消息是什么
        System.err.println(replyCode);//
        System.err.println(replyText);
        System.err.println(exchange);//
        System.err.println(routingKey);
    }
}
