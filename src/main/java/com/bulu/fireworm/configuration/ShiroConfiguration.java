package com.bulu.fireworm.configuration;

import com.bulu.fireworm.serviceimpl.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 */
@SpringBootConfiguration
public class ShiroConfiguration {

    /**
     * 哈希密码比较器
     */
    @Bean
    public HashedCredentialsMatcher hashMatcher(){
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName("md5");//设置算法：散列算法 md5
        hashMatcher.setHashIterations(2);//设置迭代次数：如散列两次，相当于 md5( md5(""));
        return hashMatcher;
    }

    /**
     * 注入身份认证realm
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();//ShiroRealm是自己创建的Realm
        shiroRealm.setCredentialsMatcher(hashMatcher());
        return shiroRealm;
    }

    /**
     * 登录成功的时候，服务器生成了remember me 的Cookie,可以在下次访问的时候，
     * 服务器根据Cookie判断而不用再登录
     * 配置Cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(600);//记住我cookie生效时间,单位是秒
        return simpleCookie;
    }

    /**
     * cookie管理器;
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        rememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    /**
     * 注入安全事务管理器（SecurityManager）
     * 注：package org.apache.shiro.mgt.SecurityManager
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(shiroRealm());//配置自定义的realm
        webSecurityManager.setRememberMeManager(rememberMeManager());
        return webSecurityManager;
    }

   /**
    * 过滤器(shiroFilter)
    */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
       ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);//设置安全管理器

        shiroFilterFactoryBean.setLoginUrl("/index.html");//配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setSuccessUrl("/index");//登陆成功后的页面,或者前端自己设置
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon"); // 配置不会被拦截的链接 顺序判断 这里是放出静态资源
        filterChainDefinitionMap.put("/fireworm/**","anon");//.put("/**", "authc");设置拦截的接口

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
   }

    /**
     * 开启shiro aop注解支持
     * 使用代理方式;所以需要开启代码支持;否则@RequiresRoles等注解无法生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor attributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 自动创建代理 不然AOP注解不会生效
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
}
