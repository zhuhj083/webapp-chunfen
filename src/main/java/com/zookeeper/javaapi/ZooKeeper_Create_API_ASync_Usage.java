package com.zookeeper.javaapi;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * ZooKeeper API创建节点，使用同步（Sync）接口
 */
public class ZooKeeper_Create_API_ASync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws  Exception{
        ZooKeeper zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new ZooKeeper_Create_API_ASync_Usage());
        connectedSemaphore.await();

        System.out.println("zookeeper connected");

        zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT ,new IStringCallback(),"I am context");

        zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,new IStringCallback(),"I am context");

        Thread.sleep(Integer.MAX_VALUE);

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watch event: "+ watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}

class IStringCallback implements AsyncCallback.StringCallback{
    @Override
    public void processResult(int rc ,String path , Object ctx , String name) {
        System.out.println("Create path result :["+ rc +","+path + ", " + ctx + " ,real path name:"+name + "]");
    }
}
