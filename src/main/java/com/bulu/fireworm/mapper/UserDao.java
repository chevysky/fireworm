package com.bulu.fireworm.mapper;

import com.bulu.fireworm.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    //通过用户名查询用户信息
    UserEntity selectUserByUsername(String username);
    //获取所有用户
    List<UserEntity> selectAllUser();
}
