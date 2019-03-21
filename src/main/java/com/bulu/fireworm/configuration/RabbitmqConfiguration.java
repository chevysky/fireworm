package com.bulu.fireworm.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootConfiguration
public class RabbitmqConfiguration {
    /**
     * 在使用队列的时候需要，先在配置类中申明,也可以在可视化管理界面去添加队列
     * 默认的交换机类型是 direct "先匹配, 再投送"
     */
    //申明队列
    @Bean(name = "queueHello")
    public Queue queueHello() {
        //Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
        return new Queue("hello",true,true,true);
    }

    //申明队列
    @Bean(name = "queue")
    public Queue queue() {
        //Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
        return new Queue("queue",true,true,true);
    }

    //申明队列给订阅用
    @Bean
    public Queue fanoutQueue(){
        return new Queue("fanoutQueue");
    }


    public static final String topicExchangeName = "topic";
    public static final String fanoutExchange = "fanout";
    public static final String headersExchange = "headers";

    //声明Topic交换机
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    //将队列与Topic交换机进行绑定，并指定路由键
    @Bean
    Binding topicBinding(@Qualifier("queueHello") Queue queueHello, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHello).to(topicExchange).with("org.cord.#");
    }

    //声明fanout交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    //将队列fanoutQueue与fanout交换机进行绑定
    @Bean
    Binding fanoutBinding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }
    //将队列queue与fanout交换机进行绑定
    @Bean
    Binding fanoutBind(Queue queue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    //声明Headers交换机
    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(headersExchange);
    }

    //将队列与headers交换机进行绑定
    @Bean
    Binding headersBinding(Queue queue, HeadersExchange headersExchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("First","A");
        map.put("Fourth","D");
        //whereAny表示部分匹配，whereAll表示全部匹配
//	        return BindingBuilder.bind(queue).to(headersExchange).whereAll(map).match();
        return BindingBuilder.bind(queue).to(headersExchange).whereAny(map).match();
    }
}
