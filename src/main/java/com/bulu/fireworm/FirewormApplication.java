package com.bulu.fireworm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bulu.fireworm.*"})
/*没在这里加扫描mapper则需要在配置数据源的时候配置*/
@MapperScan("com.bulu.fireworm.mapper")
public class FirewormApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FirewormApplication.class, args);
    }

}
