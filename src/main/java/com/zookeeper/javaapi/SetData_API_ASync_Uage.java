package com.zookeeper.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * 使用异步API获取节点数据内容
 */

public class SetData_API_ASync_Uage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws  Exception{
        zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new SetData_API_ASync_Uage());
        String path = "/zk-test";

        connectedSemaphore.await();

        zooKeeper.create(path ,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zooKeeper.setData(path ,"456".getBytes(),-1,new IStatCallback(),null);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watch event: "+ watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if (Event.EventType.None == watchedEvent.getType() &&  null == watchedEvent.getPath())
                connectedSemaphore.countDown();
        }
    }
}

class IStatCallback implements AsyncCallback.StatCallback{
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (rc==0){
            System.out.println("SUCCESS");
        }
    }
}