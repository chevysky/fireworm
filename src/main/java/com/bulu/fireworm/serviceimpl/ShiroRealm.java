package com.bulu.fireworm.serviceimpl;

import com.bulu.fireworm.entity.UserEntity;
import com.bulu.fireworm.mapper.UserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * shiro realm 自定义
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 获取前端登录的用户信息（通常是用户名和密码）
     * 之后去数据库查询当前用户的角色信息，权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String)principalCollection.getPrimaryPrincipal();
        //通过用户名去获取权限和角色信息
        Set<String> roles = null;
        authorizationInfo.setRoles(roles);//将角色名组成的set存入
        Set<String> permissions = null;
        authorizationInfo.setStringPermissions(permissions);//将权限名所组成的set存入
        //或者用循环然后一条条添加 authorizationInfo.addRole(role);
        //authorizationInfo.addStringPermission(permission);
        return authorizationInfo;
    }

    /**
     * 获取前端的登录信息，之后去数据库查询用户信息，进行比对认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //源码中用户名返回的是String类型
        String username = (String)authenticationToken.getPrincipal();
        //去数据库获取用户信息
        UserEntity user = userDao.selectUserByUsername(username);
        if (user == null){//用户不存在
            throw new UnknownAccountException();
        }else {//用户存在，判断密码
            //获取密码的时候，可以点击getCredentials()查看源码，源码中返回的char[]
            String password = new String((char[]) authenticationToken.getCredentials());
            if (user.getLoginpwd().equals(password)){//密码正确，判断用户锁定状态
                 if (user.getState() == 0){//用户被锁定
                     throw new LockedAccountException();
                 }else {//所有认证通过，账号、密码保存到登陆信息info中,可查看源码调整
                     SimpleAuthenticationInfo authenticationInfo =
                             new SimpleAuthenticationInfo(username,password,getName());
                     return authenticationInfo;
                 }
            }else {//密码错误，抛出认证错误异常
                throw new IncorrectCredentialsException();
            }
        }

    }
}
