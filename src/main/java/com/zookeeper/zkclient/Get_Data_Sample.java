package com.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * 使用zkclient获取节点的内容
 */

public class Get_Data_Sample {
    public static void main(String[] args) throws  Exception {
        ZkClient zkClient = new ZkClient("47.95.120.7:2181",5000);
        String path = "/zk-book";
        zkClient.createEphemeral(path,"123");

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("Node " + s + " changed,new data:"+ o );
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("Node " + s + " deleted.");
            }
        });
        System.out.println(zkClient.exists(path));
        System.out.println(zkClient.readData(path).toString());
        zkClient.writeData(path,"456");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(1000);
        System.out.println(zkClient.exists(path));
        Thread.sleep(Integer.MAX_VALUE);
    }
}