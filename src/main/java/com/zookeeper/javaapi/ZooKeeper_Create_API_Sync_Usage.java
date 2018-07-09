package com.zookeeper.javaapi;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * ZooKeeper API创建节点，使用同步（Sync）接口
 */
public class ZooKeeper_Create_API_Sync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws  Exception{
        ZooKeeper zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new ZooKeeper_Create_API_Sync_Usage());
        connectedSemaphore.await();

        String path1 = zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Success create znode:"+ path1);

        String path2 = zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode:"+ path2);

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watch event: "+ watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
