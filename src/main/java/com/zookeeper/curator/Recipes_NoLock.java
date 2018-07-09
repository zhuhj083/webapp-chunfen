package com.zookeeper.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 一个典型的时间戳生成的并发问题
 */

public class Recipes_NoLock {

    public static void main(String[] args) throws Exception {
        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        down.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.err.println("生成的订单号是："+orderNo);
                }
            }).start();
        }
        down.countDown();
    }


}