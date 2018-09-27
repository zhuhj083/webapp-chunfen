package com.zhj.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by zhj on 2018/9/27.
 */

public class TestBeanDiscountMethodInterceptor implements MethodInterceptor {
    private Integer discount = 90 ;

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object returnValue = invocation.proceed();

        System.out.println("TestBeanDiscountMethodInterceptor returnValue="+returnValue);

        if ( (double) returnValue > 100){
            return ((double) returnValue ) * getDiscount() / 100 ;
        }

        return returnValue ;
    }
}