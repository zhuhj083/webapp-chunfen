package com.zookeeper.javaapi;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * 使用包含权限信息的Zookeeper会话创建数据节点
 */
public class AuthSample {
    final static String PATH = "/zk-book-auth-test";

    public static void main(String[] args) throws  Exception{
        ZooKeeper zookeeper = new ZooKeeper("47.95.120.7:2181",5000,null);
        zookeeper.addAuthInfo("digest","foo:true".getBytes());
        zookeeper.create(PATH,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }

}