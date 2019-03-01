package com.bulu.fireworm.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理，支持的限定范围
 * 按注解：@ControllerAdvice(annotations = RestController.class)
 * 按包名：@ControllerAdvice("org.example.controllers")
 * 按类型：@ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler (value = Exception.class)//指定捕获的异常,多个异常value = {Exception.class,IOException.class}
    @ResponseBody//将内容转换成JSON格式
    public Map exceptionHandler(HttpServletRequest request,Exception e){
        //根据不同的异常做处理
        request.getRequestURL();//获取请求路径
        //也就是可以通过HTTP来获取前端传递的数据
        e.getMessage();//获取异常信息
        return new HashMap();//这里返回的map是自己需要去封装的返回数据类
    }
}
