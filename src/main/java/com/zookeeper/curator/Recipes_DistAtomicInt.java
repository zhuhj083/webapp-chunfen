package com.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * 使用Curator实现分布式计数器
 */

public class Recipes_DistAtomicInt {
    static String distatomint_path = "/curator_recipes_distatomint_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("47.95.120.7:2181")
            .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000,3)).build();


    public static void main(String[] args) throws Exception {
        client.start();

        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client,distatomint_path,new RetryNTimes(3,1000));
        AtomicValue<Integer> rc = atomicInteger.decrement();
        System.out.println("Result :"+rc.succeeded());
        System.out.println(rc.preValue());
        System.out.println(rc.postValue());
    }
}