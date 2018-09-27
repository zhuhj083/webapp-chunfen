package com.JavaSEEventListenerPublisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhj on 2018/9/25.
 */

public class MethodExecutionEventPublisher {
    private List<MethodExcutionEventListener> listeners = new ArrayList<MethodExcutionEventListener>();

    public void methodToMonitor(){
        MethodExecutionEvent event2Publish = new MethodExecutionEvent(this,"methodToMonitor");
        publishEvent(MethodExecutionStatus.BEGIN,event2Publish);

        // 执行实际的方法逻辑
        // ...
        publishEvent(MethodExecutionStatus.END,event2Publish);
    }


    protected void publishEvent(MethodExecutionStatus status , MethodExecutionEvent methodExecutionEvent ){
        List<MethodExcutionEventListener> copyListeners = new ArrayList<MethodExcutionEventListener>(listeners);
        for (MethodExcutionEventListener listener : copyListeners){
            if (MethodExecutionStatus.BEGIN.equals(status)){
                listener.onMethodBegin(methodExecutionEvent);
            }else {
                listener.onMethodEnd(methodExecutionEvent);
            }
        }
    }

    protected  void addMethodExecutionEventListener(MethodExcutionEventListener listener){
        this.listeners.add(listener);
    }

    protected void removeListener(MethodExcutionEventListener listener){
        if (this.listeners.contains(listener)){
            this.listeners.remove(listener);
        }
    }

    protected void removeAllListeners(){
        this.listeners.clear();
    }


    public static void main(String[] args) {
        MethodExecutionEventPublisher eventPublisher = new MethodExecutionEventPublisher();

        eventPublisher.addMethodExecutionEventListener(new  SimpleMethodExecutionEventListener());
        eventPublisher.methodToMonitor();

    }
}