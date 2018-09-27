package com.JavaSEEventListenerPublisher;

import java.util.EventListener;

/**
 * Created by zhj on 2018/9/25.
 */

public interface MethodExcutionEventListener extends EventListener{

    /*
    * 处理方法开始执行的时候发布的MethodExecutionEvent事件
    * */
    void onMethodBegin(MethodExecutionEvent evt);

    /*
     * 处理方法执行将结束时候发布的MethodExecutionEvent事件
     * */
    void onMethodEnd(MethodExecutionEvent evt);
}