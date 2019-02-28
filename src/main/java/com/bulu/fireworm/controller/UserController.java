package com.bulu.fireworm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 用户登录，将用户名密码存入shiro
     */
    @RequestMapping(value = "login")
    public String userLogin(@RequestBody Map map)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "登录成功";
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
}
