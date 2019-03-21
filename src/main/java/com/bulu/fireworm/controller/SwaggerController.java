package com.bulu.fireworm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/swa")
@Api("swagger 测试")
public class SwaggerController {

    @ApiOperation("方法一")
    @RequestMapping(value = "/test")
    public Map testSwagger(@RequestBody Map map)throws Exception{
        System.out.println("输出接收的参数" + map);
        map.put("user","user");
        map.put("name","name");
        return map;
    }
}
