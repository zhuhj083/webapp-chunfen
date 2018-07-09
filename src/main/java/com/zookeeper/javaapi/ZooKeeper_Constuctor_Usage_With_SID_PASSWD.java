package com.zookeeper.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZooKeeper_Constuctor_Usage_With_SID_PASSWD implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new ZooKeeper_Construtor_Usage_Simple());
        connectedSemaphore.await();

        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();

        //Use illegal sessionId and seesionPasswd
        zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new ZooKeeper_Construtor_Usage_Simple(),1l,"test".getBytes());

        //Use correct sessionId and seesionPasswd
        zooKeeper = new ZooKeeper("47.95.120.7:2181",5000,new ZooKeeper_Construtor_Usage_Simple(),sessionId,passwd);

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
