package com.zookeeper;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * ZooKeeper API创建节点，使用同步（Sync）接口
 */
public class ZooKeeper_GetChildren_API_Sync_Uasge implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws  Exception{
        zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new ZooKeeper_GetChildren_API_Sync_Uasge());
        String path = "/zk-book";

        connectedSemaphore.await();

        zooKeeper.create(path ,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.create(path+"/c1" ,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        List<String> childrenList = zooKeeper.getChildren(path,true);
        System.out.println(childrenList);

        zooKeeper.create(path+"/c2" ,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watch event: "+ watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if (Event.EventType.None == watchedEvent.getType() &&  null == watchedEvent.getPath())
                connectedSemaphore.countDown();
            else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("ReGet Child :" + zooKeeper.getChildren(watchedEvent.getPath() ,true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
