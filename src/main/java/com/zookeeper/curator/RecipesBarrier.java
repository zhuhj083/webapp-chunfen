package com.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 使用Curator实现分布式Barrier
 * 主进程中释放
 */

public class RecipesBarrier {
    static String barrier_path = "/curator_recipes_barrier_path";
    static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework client = CuratorFrameworkFactory.builder().connectString("47.95.120.7:2181")
                            .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
                    client.start();

                    barrier = new DistributedBarrier(client,barrier_path);
                    System.out.println(Thread.currentThread().getName() +" 号barrier设置。");
                    try {
                        barrier.setBarrier();
                        barrier.waitOnBarrier();
                        System.err.println("启动....");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(10000);
        barrier.removeBarrier();

    }

}