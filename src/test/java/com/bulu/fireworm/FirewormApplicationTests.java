package com.bulu.fireworm;

import com.bulu.fireworm.entity.UserEntity;
import com.bulu.fireworm.message.sender.reveal.UserMessageSend;
import com.bulu.fireworm.serviceimpl.UserServiceImpl;
import com.bulu.fireworm.toolkit.Kit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirewormApplicationTests {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMessageSend userMessageSend;

    @Test
    public void ehcacheTest() throws Exception{
        LocalDateTime time1 = LocalDateTime.now();
        userService.getAllUser();
        LocalDateTime time2 = LocalDateTime.now();
        System.out.println("第一次：");
        System.out.println(time2.toInstant(ZoneOffset.of("+8")).toEpochMilli()
                - time1.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        LocalDateTime time3 = LocalDateTime.now();
        userService.getAllUser();
        LocalDateTime time4 = LocalDateTime.now();
        System.out.println("第二次：");
        System.out.println(time4.toInstant(ZoneOffset.of("+8")).toEpochMilli()
                - time3.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    @Test
    public void testGetCipherKey(){
        new Kit().creatCipherKey();
    }

    @Test
    public void testRabbitMQ()throws Exception{
        userMessageSend.sendUserInfo(new UserEntity());
    }

}
