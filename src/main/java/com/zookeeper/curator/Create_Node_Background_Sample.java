package com.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用curator的异步接口
 */

public class Create_Node_Background_Sample {
    static String path = "/zk-book";
    static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
    static  CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("47.95.120.7:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(retryPolicy)
            .build();
    static CountDownLatch semaphore = new CountDownLatch(2);
    static ExecutorService tp = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws  Exception {
        client.start();
        System.out.println("Main thread:"+Thread.currentThread().getName());

        //此处传入了自定义的Executor
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println("event[code"+curatorEvent.getResultCode()+",type:"+curatorEvent.getType()+"]");
                        System.out.println("Thread of processResult:"+Thread.currentThread().getName());
                        semaphore.countDown();
                    }
                },tp)
                .forPath(path,"init".getBytes());

        //此处没有传入自定义的executor
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println("event[code"+curatorEvent.getResultCode()+",type:"+curatorEvent.getType()+"]");
                        System.out.println("Thread of processResult:"+Thread.currentThread().getName());
                        semaphore.countDown();
                    }
                })
                .forPath(path,"init".getBytes());

        semaphore.await();
        tp.shutdown();
        System.out.println("end");
    }
}