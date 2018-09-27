package com.zhj.aop;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;

/**
 * Created by zhj on 2018/8/8.
 */

@Aspect
public class AspectJTest implements Ordered {

    @Pointcut("execution(* *.test(..))")
    public void test(){
    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @After("test()")
    public void afterTest(){
        System.out.println("afterTest");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p ){
        System.out.println("before around");
        long start = System.currentTimeMillis();
        Object o = null ;
        try {
            o = p.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("cost="+ (System.currentTimeMillis() - start));
        System.out.println("after around");
        return o ;
    }

    @AfterThrowing(pointcut = "test()",throwing = "e")
    public void afterThrowing(RuntimeException e){
        final String exceptionMessage = ExceptionUtils.getStackTrace(e);
        System.out.println("exception="+exceptionMessage);
    }

    @AfterReturning(pointcut = "test()",returning = "retValue")
    public void afterReturning(JoinPoint joinPoint,Object retValue){
        Class clazz = joinPoint.getTarget().getClass();
        System.out.println("clazz="+clazz.getName() + " retValue="+retValue);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}