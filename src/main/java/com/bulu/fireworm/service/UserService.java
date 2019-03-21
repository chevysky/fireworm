package com.bulu.fireworm.service;

import com.bulu.fireworm.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {

    //获取所有用户
    List<UserEntity> getAllUser()throws Exception;

    //获取当前用户名
    String getLoginUser()throws Exception;

    //NIO处理图片
    void disposeImgByNio(MultipartFile file)throws Exception;

}
