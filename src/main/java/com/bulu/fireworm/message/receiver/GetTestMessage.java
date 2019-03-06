package com.bulu.fireworm.message.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 测试多个消费者同时接收数据
 */
@Component
public class GetTestMessage {

    @RabbitHandler
    @RabbitListener(queues = "fanoutQueue")
    public void receiverUserInfo3(Object message){
        System.out.println("订阅二" + message);
    }
}
