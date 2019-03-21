package com.bulu.fireworm.message.sender.realize;

import com.bulu.fireworm.configuration.RabbitmqConfiguration;
import com.bulu.fireworm.entity.UserEntity;
import com.bulu.fireworm.message.sender.reveal.UserMessageSend;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserMessageSendImpl implements UserMessageSend {

    @Autowired
    private AmqpTemplate amqp;

    /**
     * 发送用户信息
     */
    @Override
    public void sendUserInfo(UserEntity user) throws Exception {
          sendDirectMsg("hello","交换机模式：direct，队列名：hello");
          sendExchangeMsg("topic","org.cord.hello","交换机模式：topic，队列名：hello");
          sendExchangeMsg("topic","org.cord.a","交换机模式：topic，aaaa");
          sendExchangeMsg("topic","org.cord.b","交换机模式：topic，bbbbb");
          user.setId(1);
          user.setNickname("chevysky");
          user.setWechat("lalala");
          sendExchangeMsg("topic","org.cord.user",user);
           sendExchangeMsg(RabbitmqConfiguration.fanoutExchange,"hello","交换机模式：fanout，队列名：queue");
          Map map = new HashMap();
          map.put("First","A");
          sendHeadersMsg("headers","headers发送消息",map);
    }

    /**
     * @param routingKey 路由关键字,这里就是队列名
     * @param msg 消息体
     * 默认的交换机是direct
     */
    public void sendDirectMsg(String routingKey, Object msg) {
        amqp.convertAndSend(routingKey, msg);
    }

    /**
     * @param routingKey 路由关键字
     * @param msg 消息体
     * @param exchange 交换机 路由模式direct;通配符模式topic;发布订阅模式fanout;
     */
    public void sendExchangeMsg(String exchange, String routingKey, Object msg) {
        amqp.convertAndSend(exchange, routingKey, msg);
    }

    /**
     * @param map 消息headers属性
     * @param exchange 交换机
     * @param msg 消息体
     */
    public void sendHeadersMsg(String exchange, String msg, Map<String, Object> map) {
        amqp.convertAndSend(exchange, null, msg, message -> {
            message.getMessageProperties().getHeaders().putAll(map);
            return message;
        });
    }
}
