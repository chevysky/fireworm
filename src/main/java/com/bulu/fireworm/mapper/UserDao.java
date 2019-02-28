package com.bulu.fireworm.mapper;

import com.bulu.fireworm.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    //通过用户名查询用户信息
    UserEntity selectUserByUsername(String username);
}
