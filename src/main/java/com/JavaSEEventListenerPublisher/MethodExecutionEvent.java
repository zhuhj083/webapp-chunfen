package com.JavaSEEventListenerPublisher;

import java.util.EventObject;

/**
 * Created by zhj on 2018/9/25.
 */

public class MethodExecutionEvent extends EventObject{

    private String methodName;

    public MethodExecutionEvent(Object source) {
        super(source);
    }

    public MethodExecutionEvent(Object source , String methodName){
        super(source);
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}