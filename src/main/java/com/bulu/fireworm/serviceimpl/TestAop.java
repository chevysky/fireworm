package com.bulu.fireworm.serviceimpl;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestAop {

    public void getTestInfo(Map map){
        System.out.println("调用测试get开头主方法");
    }

    public void setTestInfo(){
        System.out.println("调用测试set开头主方法");
    }

    public String getInfo(int num){
        System.out.println("get方法二");
        return "返回值就是我";
    }
}
