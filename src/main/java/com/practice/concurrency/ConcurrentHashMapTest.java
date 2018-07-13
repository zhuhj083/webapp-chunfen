package com.practice.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhj on 2018/7/13.
 */

public class ConcurrentHashMapTest {

    static ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
    static Lock lock = new ReentrantLock();

    public static void plus(){
//        lock.lock();
        AtomicInteger ii = map.getOrDefault("key",new AtomicInteger(0));
        int a = ii.get();
        ii.compareAndSet(a,a+1);
        map.put("key",ii);
//        lock.unlock();
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                plus();
            }
        };

        for (int i = 0; i < 10000; i++) {
            new Thread(runnable).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map.get("key"));

    }




}