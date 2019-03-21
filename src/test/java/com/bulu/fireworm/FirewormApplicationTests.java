package com.bulu.fireworm;

import com.bulu.fireworm.entity.UserEntity;
import com.bulu.fireworm.message.sender.reveal.UserMessageSend;
import com.bulu.fireworm.serviceimpl.TestAop;
import com.bulu.fireworm.serviceimpl.TestThread;
import com.bulu.fireworm.serviceimpl.UserServiceImpl;
import com.bulu.fireworm.toolkit.Kit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirewormApplicationTests {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMessageSend userMessageSend;
    @Autowired
    private TestAop aop;
    @Autowired
    private TestThread thread;

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

    @Test
    public void testAop(){
        Map map = new HashMap();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        aop.getTestInfo(map);
        System.out.println("方法结束");
        aop.setTestInfo();
        aop.getInfo(100);
    }

    @Test
    public void testThread(){
        thread.threadTest();
    }

    @Test
    public void testThreadWait(){
        thread.testThreadWait();
    }

    @Test
    public void testThreadLocal(){
        Runnable threadA = new Runnable() {
            @Override
            public void run() {
                thread.testThreadA("打屎你");
            }
        };
        Thread a = new Thread(threadA);
        Runnable threadB = new Runnable() {
            @Override
            public void run() {
                thread.testThreadA("打我呀");
            }
        };
        Thread b = new Thread(threadB);
        a.start();
        b.start();
    }

    @Test
    public void testA(){
        thread.testB();
        //查看读写锁
        ReadWriteLock lock;
    }

    @Test
    public void testB(){
        AtomicInteger cas = new AtomicInteger(2) ;
        System.out.println(cas.compareAndSet(1,2));
    }

}
