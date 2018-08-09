package com.zhj.entity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by zhj on 2018/8/8.
 */

@Aspect
public class AspectJTest {

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
        System.out.println("before1");
        long start = System.currentTimeMillis();
        Object o = null ;
        try {
            o = p.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("cost="+ (System.currentTimeMillis() - start));
        System.out.println("after1");
        return o ;
    }

}