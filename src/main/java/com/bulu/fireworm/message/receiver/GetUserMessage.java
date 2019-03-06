package com.bulu.fireworm.message.receiver;

import com.bulu.fireworm.entity.UserEntity;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class GetUserMessage {

    @RabbitListener(queues = "hello")
    public void receiverUserInfo1(Object message){
        System.out.println("第一个消费者" + message);
    }

    @RabbitListener(queues = "hello")
    public void receiverUserInfo2(Object message){
        System.out.println("第二个消费者" + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "queue")
    public void receiverUserInfo(Object message){
        System.out.println("订阅一" + message);
    }


}
