package com.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 使用Curator实现 另一种 分布式Barrier
 * 另一种线程自发触发Barrier的释放模式
 */

public class RecipesBarrier2 {
    static String barrier_path = "/curator_recipes_barrier_path";

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("47.95.120.7:2181")
                                .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
                        client.start();

                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client,barrier_path,5);

                        Thread.sleep(Math.round(Math.random())*3000);
                        System.out.println(Thread.currentThread().getName() +" 号进入barrier。");
                        barrier.enter();
                        System.err.println("启动....");
                        Thread.sleep(Math.round(Math.random())*3000);
                        barrier.leave();
                        System.out.println("退出....");
                        Thread.sleep(Math.round(Math.random())*3000);
                        barrier.enter();
                        System.err.println("再启动....");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(10000);

    }

}