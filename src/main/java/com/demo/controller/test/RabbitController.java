package com.demo.controller.test;

import cn.z.clock.Clock;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <h1>Rabbit生产者api</h1>
 *
 * <p>
 * createDate 2021/03/13 15:13:30
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/rabbit")
@RestController
@AllArgsConstructor
public class RabbitController {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Hello World模型，点对点模型
     */
    @PostMapping("hello")
    public String hello() {
        // 队列名称，对象
        rabbitTemplate.convertAndSend("hello", "hello:" + Clock.timestamp());
        return "ok";
    }

    /**
     * Work模型，工作模型
     */
    @PostMapping("work")
    public String work() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work." + i + ":" + Clock.timestamp());
        }
        return "ok";
    }

    /**
     * Fanout模型，广播模型
     */
    @PostMapping("fanout")
    public String fanout() {
        // 交换机名称，路由key（广播不需要），对象
        rabbitTemplate.convertAndSend("fanout", "", "fanout:" + Clock.timestamp());
        return "ok";
    }

    /**
     * Direct模型，路由模型
     */
    @PostMapping("direct")
    public String direct(String key) {
        // 交换机名称，路由key，对象
        rabbitTemplate.convertAndSend("direct", key, "direct." + key + ":" + Clock.timestamp());
        return "ok";
    }

    /**
     * Topic模型，动态路由模型
     */
    @PostMapping("topic")
    public String topic(String key) {
        // 交换机名称，路由key，对象
        rabbitTemplate.convertAndSend("topic", key, "topic." + key + ":" + Clock.timestamp());
        return "ok";
    }


}
