package com.zookeeper.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用异步API获取子结点列表
 */
public class ZooKeeper_GetChildren_API_ASync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws  Exception{
        zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new ZooKeeper_GetChildren_API_ASync_Usage());
        String path = "/zk-test";

        connectedSemaphore.await();

        zooKeeper.create(path ,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.create(path+"/c1" ,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zooKeeper.getChildren(path,true ,new IChildren2Callback(),"I am CTX");

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

class IChildren2Callback implements AsyncCallback.Children2Callback{
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println("Get Children znode result:[response code:"+rc+",param path:"+path+",ctx:"+ctx+",children list:"+children+",stat:"+stat);
    }
}