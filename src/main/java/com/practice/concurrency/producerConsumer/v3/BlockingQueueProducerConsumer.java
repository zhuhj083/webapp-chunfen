package com.practice.concurrency.producerConsumer.v3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhj on 2018/6/26.
 */

public class BlockingQueueProducerConsumer {

    public static void main(String[] args) {
        Resource resource = new Resource();

        //生产者线程
        ProducerThread p1 = new ProducerThread(resource,"p1");
        ProducerThread p2 = new ProducerThread(resource,"p2");
        ProducerThread p3 = new ProducerThread(resource,"p3");
        ProducerThread p4 = new ProducerThread(resource,"p4");


        ConsumerThread c1 = new ConsumerThread(resource,"consumer1");
        ConsumerThread c2 = new ConsumerThread(resource,"consumer2");
        ConsumerThread c3 = new ConsumerThread(resource,"consumer3");

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        c1.start();
        c2.start();
        c3.start();
    }
}

/**
 * 消费者线程
 */
class ConsumerThread extends Thread{
    private Resource resource;

    public ConsumerThread(Resource resource,String threadName) {
        this.resource = resource;
        super.setName(threadName);
    }

    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep((long)(1000*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}

/**
 * 生产者线程
 */
class ProducerThread extends Thread{
    private Resource resource;

    public ProducerThread(Resource resource,String threadName) {
        this.resource = resource;
        super.setName(threadName);
    }

    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep((long)(1000*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}

class Resource {
    private BlockingQueue resourceQueue = new LinkedBlockingQueue(10);

    /**
     * 向资源池中添加资源
     */
    public void add(){
        try {
            resourceQueue.put(1);
            System.out.println("生产者 "+Thread.currentThread().getName()+" 生产了一件资源，当前资源池中有"+resourceQueue.size() +" 个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 从资源池中移除资源
     */
    public void remove(){
        try {
            resourceQueue.take();
            System.out.println("消费者 "+ Thread.currentThread().getName() + " 消耗一件资源，当前资源池中有"+resourceQueue.size() +" 个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}