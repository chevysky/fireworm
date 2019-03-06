package com.bulu.fireworm.controller;

import com.bulu.fireworm.entity.UserEntity;
import com.bulu.fireworm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录，将用户名密码存入shiro
     */
    @RequestMapping(value = "/login")
    public String userLogin(@RequestBody Map map)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        boolean remember = (map.containsKey("remember"))?true:false;
        token.setRememberMe(remember);
        try {
            subject.login(token);
            return username;
        }catch (UnknownAccountException e){
            return "用户不存在";
        }catch (IncorrectCredentialsException e){
            return "密码错误";
        }catch (LockedAccountException e){
            return "该账户被锁定";
        }catch (DisabledAccountException e){
            return "该账户被禁用";
        }
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register")
    public List<UserEntity> userRegister(@RequestBody Map map)throws Exception{
        return userService.getAllUser();
    }

    /**
     * 获取当前登录用户
     */
    @RequestMapping(value = "/loginUser")
    public String getLoginUser()throws Exception{
        return userService.getLoginUser();
    }
}
