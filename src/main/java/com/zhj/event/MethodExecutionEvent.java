package com.zhj.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by zhj on 2018/9/25.
 */

public class MethodExecutionEvent extends ApplicationEvent {

    private String methodName;
    private MethodExecutionStatus methodExecutionStatus;

    public MethodExecutionEvent(Object source) {
        super(source);
    }

    public MethodExecutionEvent(Object source,String methodName,MethodExecutionStatus methodExecutionStatus){
        super(source);
        this.methodName = methodName;
        this.methodExecutionStatus = methodExecutionStatus;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public MethodExecutionStatus getMethodExecutionStatus() {
        return methodExecutionStatus;
    }

    public void setMethodExecutionStatus(MethodExecutionStatus methodExecutionStatus) {
        this.methodExecutionStatus = methodExecutionStatus;
    }
}