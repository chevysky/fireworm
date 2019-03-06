package com.bulu.fireworm.service;

import com.bulu.fireworm.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    //获取所有用户
    List<UserEntity> getAllUser()throws Exception;

    //获取当前用户名
    String getLoginUser()throws Exception;

}
