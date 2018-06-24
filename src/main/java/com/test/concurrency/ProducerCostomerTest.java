package com.test.concurrency;

import com.test.util.SleepUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ProducerCostomerTest {
    static Queue<Integer> products = new LinkedList();
    static Object lock = new Object();

    public static void main(String[] args) {
         new Thread(new Producer(),"producer").start();
        new Thread(new Costomer(),"costomer").start();
    }

    static class Costomer implements Runnable{
        public void run() {
                while (true){
                    if(!products.isEmpty()){
                        Integer p = products.remove();
                        try {
                            TimeUnit.MILLISECONDS.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("消费了产品："+ p +" ，还有产品："+products.size());
                        SleepUtils.second(2);
                        synchronized (lock){
                            lock.notifyAll();
                        }
                    }else{
                        try {
                            synchronized (lock){
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }
    }

    static class Producer implements Runnable{
        public void run() {
                for (int i = 1; i <= 100; i++) {

                    if(products.size() >= 5){
                        synchronized (lock) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        try {
                            TimeUnit.MILLISECONDS.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        products.add(i);
                        System.out.println("生产了产品："+i +" 一共有产品："+products.size());
                        synchronized (lock) {
                            lock.notifyAll();
                        }
                    }
            }
        }
    }
}
