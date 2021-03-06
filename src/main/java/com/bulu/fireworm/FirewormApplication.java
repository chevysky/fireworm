package com.bulu.fireworm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages = "com.bulu.fireworm.*")
@MapperScan(basePackages = "com.bulu.fireworm.mapper")
@EnableCaching
@EnableSwagger2
@SpringBootApplication
public class FirewormApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FirewormApplication.class, args);
    }

}
