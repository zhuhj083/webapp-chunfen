package com.zhj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhj on 2018/8/9.
 */

public class MyInvocationHandler implements InvocationHandler {

    //目标对象
    private Object target ;

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在目标对象的方法执行之前简单打印一下
        System.out.println("-------------------before--------------------");

        //执行目标对象的方法
        Object result = method.invoke(target,args);

        //在目标对象的方法执行之后简单打印一下
        System.out.println("-------------------end------------------------");

        return result ;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),target.getClass().getInterfaces(),this);
    }

}