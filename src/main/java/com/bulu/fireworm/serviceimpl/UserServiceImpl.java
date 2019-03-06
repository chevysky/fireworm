package com.bulu.fireworm.serviceimpl;

import com.bulu.fireworm.entity.UserEntity;
import com.bulu.fireworm.mapper.UserDao;
import com.bulu.fireworm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //获取所有用户
    @Override
    @Cacheable(value = "users")
    public List<UserEntity> getAllUser() throws Exception {
        List<UserEntity> users = userDao.selectAllUser();
        return users;
    }

    //获取当前登录用户
    @Override
    public String getLoginUser() throws Exception {
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        return username;
    }
}
