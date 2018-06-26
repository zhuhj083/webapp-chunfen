package com.practice.concurrency.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class MutexTest {
    static  Lock lock = new Mutex();
    static int count = 0 ;
    static void add(){
        lock.lock();
        try {
            count ++ ;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        add();
                    }
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("count="+count);
    }
}
