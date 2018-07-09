package com.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by zhj on 2018/7/9.
 */

public class Create_Node_Sample {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("47.95.120.7:2181",5000);
        System.out.println("Zookeeper session established.");
        String path = "/zk-book/c1";
        zkClient.createPersistent(path,true);
    }
}