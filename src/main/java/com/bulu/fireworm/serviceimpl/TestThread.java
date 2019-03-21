package com.bulu.fireworm.serviceimpl;

import com.bulu.fireworm.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Semaphore;

@Service
public class TestThread {

    public void threadTest(){
        Runnable thread1 = new Runnable() {
            private UserEntity user = new UserEntity();
            @Override
            public void run() {
                user.setWechat("线程一的数据");
                user.setNickname("线程一昵称");
                user.setMail("数据一");
                System.out.println(user);
            }
        };
        Runnable thread2 = new Runnable() {
            private UserEntity user = new UserEntity();
            @Override
            public void run() {
                user.setWechat("线程二的数据");
                user.setId(2);
                System.out.println(user);
            }
        };
       /* thread2.run();
        thread1.run();*/
       Thread t1 = new Thread(thread1);
       Thread t2 = new Thread(thread2);
       t2.start();
       t1.start();
    }

    public void testThreadWait(){

       controllerThread c = new controllerThread();


        Integer lock = new Integer(11);

        Runnable threadB = new Runnable() {
            @Override
            public void run() {
                c.setWait(lock);
                System.out.println("线程二");
            }
        };

        Runnable threadA = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程一");
                System.out.println("线程一");
                System.out.println("线程一");
                System.out.println("线程一");
                c.getNotify(lock);
            }
        };

        Thread a = new Thread(threadA);
        a.start();
        Thread b = new Thread(threadB);
        b.start();

    }

    public class controllerThread{

        public void setWait(Object obj){
            synchronized (obj){
                try{
                    obj.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        public void getNotify(Object obj){
            synchronized (obj){
                obj.notify();
            }
        }
    }

    private ThreadLocal local = new ThreadLocal();
    public void testThreadA(Object value){
       local.set(value);
       System.out.println(local.get());
    }

    public class TestA{
        @Getter
        @Setter
        private boolean mark = false;
    }

    public void testB(){
        TestA a = new TestA();
        a.setMark(true);
        System.out.println("输出一" + a.isMark());
        TestA b = new TestA();
        System.out.println("输出一" + b.isMark());
    }
}
