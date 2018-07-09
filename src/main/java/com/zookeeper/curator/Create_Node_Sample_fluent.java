package com.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 使用fluent风格的API接口来创建一个Zookeeper客户端
 */

public class Create_Node_Sample_fluent {
    static String path = "/zk-book/c1";
    static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
    static  CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("47.95.120.7:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(retryPolicy)
            .build();

    public static void main(String[] args) throws  Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());
        Thread.sleep(Integer.MAX_VALUE);
    }
}