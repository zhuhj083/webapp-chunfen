package com.test.concurrency.concurrencyUtils;

import com.test.util.SleepUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                try {
                    TimeUnit.MILLISECONDS.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await(100, TimeUnit.MILLISECONDS);
        System.out.println(3);
    }
}
