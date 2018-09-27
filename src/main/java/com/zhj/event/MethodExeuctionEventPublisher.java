package com.zhj.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * Created by zhj on 2018/9/25.
 */

public class MethodExeuctionEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public void methodToMonitor(){
        MethodExecutionEvent beginEvt = new MethodExecutionEvent(this,"methodToMonitor",MethodExecutionStatus.BEGIN);

        this.eventPublisher.publishEvent(beginEvt);

        // 执行实际方法逻辑
        // ...

        MethodExecutionEvent endEvt = new MethodExecutionEvent(this,"methodToMonitor",MethodExecutionStatus.END);
        this.eventPublisher.publishEvent(endEvt);
    }

}