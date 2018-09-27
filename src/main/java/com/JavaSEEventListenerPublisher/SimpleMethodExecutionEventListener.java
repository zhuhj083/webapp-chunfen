package com.JavaSEEventListenerPublisher;

/**
 * Created by zhj on 2018/9/25.
 */

public class SimpleMethodExecutionEventListener implements MethodExcutionEventListener {

    @Override
    public void onMethodBegin(MethodExecutionEvent evt) {
        String methodName = evt.getMethodName();
        System.out.println("start to execute the method["+methodName+"]");
    }

    @Override
    public void onMethodEnd(MethodExecutionEvent evt) {
        String methodName = evt.getMethodName();
        System.out.println("finished to execute the method["+methodName+"]");
    }
}