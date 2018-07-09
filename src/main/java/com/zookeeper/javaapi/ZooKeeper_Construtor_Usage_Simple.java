package com.zookeeper.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZooKeeper_Construtor_Usage_Simple implements Watcher  {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws  Exception{
        ZooKeeper zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new ZooKeeper_Construtor_Usage_Simple());
        System.out.println(zooKeeper.getState());
        try{
            connectedSemaphore.await();
        }catch (InterruptedException ex){
        }
        System.out.println("Zookeeper session 连接已建立");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watch event: "+ watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            connectedSemaphore.countDown();
        }
    }
}
