package com.zhj.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by zhj on 2018/9/25.
 */

public class MethodExecutionEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MethodExecutionEvent){
            //执行处理逻辑
        }
    }
}