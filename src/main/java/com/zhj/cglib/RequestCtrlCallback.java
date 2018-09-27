package com.zhj.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhj on 2018/9/26.
 */

public class RequestCtrlCallback implements MethodInterceptor {
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("request")){
            return methodProxy.invokeSuper(object,args);
        }
        return null ;
    }
}