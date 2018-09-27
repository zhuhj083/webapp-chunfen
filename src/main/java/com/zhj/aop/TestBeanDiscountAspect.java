package com.zhj.aop;

import com.zhj.entity.TestIntroductionBean;
import com.zhj.entity.TestIntroductionBeanImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;

/**
 * Created by zhj on 2018/9/27.
 */
@Aspect
public class TestBeanDiscountAspect implements Ordered {

    //让TestBeanImpl拥有TestIntroductionBean接口的功能
    @DeclareParents(
            value = "com.zhj.entity.TestBeanImpl",
            defaultImpl = TestIntroductionBeanImpl.class
    )
    public TestIntroductionBean testIntroductionBean;


    @Pointcut("execution(public double com.zhj.entity.TestBean.test())")
    public void pointcutName(){}

    @Around("pointcutName()")
    public Object discount(ProceedingJoinPoint joinPoint) throws Throwable{
        Object returnValue = joinPoint.proceed();

        System.out.println("returnValue="+returnValue);

        if ( (double) returnValue > 100){
            return ((double) returnValue ) * 80  / 100 ;
        }

        return returnValue ;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}