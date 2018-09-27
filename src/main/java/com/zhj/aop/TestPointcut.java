package com.zhj.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Created by zhj on 2018/9/27.
 */

public class TestPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return targetClass.getCanonicalName().equals("com.zhj.entity.TestBeanImpl") && method.getName().equals("test");
    }

}