package com.bulu.fireworm.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 切面配置类
 */
@Component
@Aspect
public class AspectModel {

    @Pointcut("execution(public * com.bulu.fireworm.serviceimpl.TestAop.get*(..))")
    //参考配置:@Pointcut("execution(public * com.bulu.fireworm..*.*(..))")
    public void pointCut(){
       System.out.println("执行的切面主程序");
    }

    /**
     * 方法运行之前
     * @param joinPoint 封装了切面方法的信息
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("参数="+arg+" ");
        }
//        System.out.println("方法运行前" + joinPoint);
//        System.out.println("切面获取参数" + joinPoint.getArgs().toString());
//        System.out.println("切面getKind" + joinPoint.getKind());
//        System.out.println("1" + joinPoint.getSignature().getDeclaringType());
//        System.out.println("2" + joinPoint.getTarget());
//        System.out.println("3" + joinPoint.getThis());
    }

    //方法运行之后
    @After("pointCut() && args(num)")
    public void after(JoinPoint joinPoint ,int num)
    {
        System.out.println("方法运行之后j参数"+num);
    }

    //方法返回
    @AfterReturning("pointCut() && args(map)")
    public void afterReturn(JoinPoint joinPoint,Map map)
    {
        System.out.println("方法返回参数"+map);
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable
    {
        System.out.println("around start..");
        try {
            //Object proceed() throws Throwable //执行目标方法
            //Object proceed(Object[] var1) throws Throwable //传入的新的参数去执行目标方法
            Object returnArg = pjp.proceed();
            System.out.println("返回值" + returnArg);
        } catch (Throwable ex) {
            System.out.println("error in around");
            throw ex;
        }
        System.out.println("around end");
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error) {
        System.out.println("error:" + error + "jp" + jp);
    }
}
