package com.demo.service;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * <h1>Rabbit2消费者服务</h1>
 *
 * <p>
 * createDate 2021/03/13 15:49:32
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Service
public class RabbitService2 {

    /**
     * 工作模型，至少2个消费者，默认平均分配<br>
     * RabbitListener可以直接写到方法上<br>
     * 消费者1
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiver(String message) {
        System.out.println("RabbitService2.receiver收到消息：" + message);
    }

    /**
     * 工作模型<br>
     * RabbitListener可以直接写到方法上<br>
     * 消费者2
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiver2(String message) {
        System.out.println("RabbitService2.receiver2收到消息：" + message);
    }

    /**
     * 广播模型<br>
     * 消费者1
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue,// 队列名称，临时队列，随机名称
            exchange = @Exchange(value = "fanout", type = "fanout")// 交换机，value名称，type类型
    )})
    public void receiver3(String message) {
        System.out.println("RabbitService2.receiver3收到消息：" + message);
    }

    /**
     * 广播模型<br>
     * 消费者2
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "fanout", type = "fanout"))})
    public void receiver4(String message) {
        System.out.println("RabbitService2.receiver4收到消息：" + message);
    }

    /**
     * 路由模型<br>
     * 消费者1
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue,// 队列名称，随机
            exchange = @Exchange(value = "direct", type = "direct"),// 交换机，value名称，type类型（默认direct）
            key = {"error", "warn", "info", "trace", "debug"}// 可接收的路由key
    )})
    public void receiver5(String message) {
        System.out.println("RabbitService2.receiver5收到消息：" + message);
    }

    /**
     * 路由模型<br>
     * 消费者2
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "direct"), key = {"error",
            "warn"})})
    public void receiver6(String message) {
        System.out.println("RabbitService2.receiver6收到消息：" + message);
    }

    /**
     * 动态路由模型<br>
     * 消费者1
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue,// 队列名称，随机
            exchange = @Exchange(value = "topic", type = "topic"),// 交换机，value名称，type类型
            key = {"user", "admin.*", "root.#"}// 可接收的路由key，*匹配1个单词，#匹配0及0个以上的单词（中间用.隔开）
    )})
    public void receiver7(String message) {
        System.out.println("RabbitService2.receiver7收到消息：" + message);
    }

    /**
     * 动态路由模型<br>
     * 消费者2
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "topic", type = "topic"),
            key = {"admin", "root.*"})})
    public void receiver8(String message) {
        System.out.println("RabbitService2.receiver8收到消息：" + message);
    }

}
