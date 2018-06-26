package com.practice.concurrency.producerConsumer.v2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhj on 2018/6/26.
 */

public class LockCondition {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();

        Resource resource = new Resource(lock,producerCondition,consumerCondition);

        //生产者线程
        ProducerThread p1 = new ProducerThread(resource,"producer1");

        //消费者线程
        ConsumerThread c1 = new ConsumerThread(resource,"consumer1");
        ConsumerThread c2 = new ConsumerThread(resource,"consumer2");
        ConsumerThread c3 = new ConsumerThread(resource,"consumer3");

        p1.start();
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
        setName(threadName);
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep( (long)(1000 * Math.random() ));
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
        setName(threadName);
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep((long)(1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}


/**
 * 公共资源类
 */
class Resource {
    private int num = 0 ; //当前资源数量
    private int size = 10 ; //资源池中允许存放的资源数量
    private Lock lock ;
    private Condition producerCondition ;
    private Condition consumerCondition ;

    public Resource(Lock lock, Condition producerCondition, Condition consumerCondition) {
        this.lock = lock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
    }

    /**
     * 向资源池中添加资源
     */
    public void add(){
        lock.lock();
        try {
            if (num < size ){
                num ++ ;
                System.out.println(Thread.currentThread().getName()+" 生产一件资源，当前资源池有"+num+"个");
                //唤醒等待的消费者
                consumerCondition.signalAll();
            }else{
                //让生产者线程等待
                try {
                    producerCondition.await();
                    System.out.println(Thread.currentThread().getName()+" 线程进入等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 从资源池取走资源
     */
    public void remove(){
        lock.lock();
        try {
            if (num > 0 ){
                num -- ;
                System.out.println("消费者 "+Thread.currentThread().getName() + " 消费了一件资源，当前资源池还有"+num+"个");
                producerCondition.signalAll();
            }else{
                try {
                    consumerCondition.await();
                    System.out.println(Thread.currentThread().getName()+" 线程进入等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
