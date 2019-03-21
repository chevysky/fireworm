package com.bulu.fireworm.controller;

import com.bulu.fireworm.entity.UserEntity;
import com.bulu.fireworm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.RandomAccessFile;
import java.util.HashMap;
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
    public Map userLogin(@RequestBody Map map)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        boolean remember = (map.containsKey("remember"))?true:false;
        token.setRememberMe(remember);
        Map mark = new HashMap();
        try {
            subject.login(token);
            mark.put("message",username);
        }catch (UnknownAccountException e){
            mark.put("message","用户不存在");
        }catch (IncorrectCredentialsException e){
            mark.put("message","密码错误");
        }catch (LockedAccountException e){
            mark.put("message","该账户被锁定");
        }catch (DisabledAccountException e){
            mark.put("message","该账户被禁用");
        }finally {
            return mark;
        }
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register")
    public Map userRegister(@RequestBody Map map)throws Exception{
        Map response = new HashMap();
        response.put("mess",userService.getAllUser());
        return response;
    }

    /**
     * 获取当前登录用户
     */
    @RequestMapping(value = "/loginUser")
    public Map getLoginUser()throws Exception{
        Map map = new HashMap();
        map.put("username",userService.getLoginUser());
        return map;
    }

    /**
     * 获取前端传入的图片数据
     */
    @PostMapping(value = "/file")
   /* public Map getFile(@RequestBody MultipartFile file)throws Exception{
         byte[] bytes = file.getBytes();
         String img = "data:image/png;base64," + Base64.encodeToString(bytes);
         Map mark = new HashMap();
         mark.put("mess",img);
         return mark;
    }*/
   public Map getFile(@RequestBody RandomAccessFile file)throws Exception{
        Map mark = new HashMap();
        mark.put("mess","啦啦啦德玛西亚");
        return mark;
    }
}
