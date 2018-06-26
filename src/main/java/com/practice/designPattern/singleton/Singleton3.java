package com.practice.designPattern.singleton;

/**
 * 加锁的懒汉模式
 *
 * 不过加锁是一个非常耗时的操作，应当避免
 */
public class Singleton3{
    private Singleton3(){
    }

    private static Singleton3 instance = null ;

    public synchronized static Singleton3 getInstance() {
        if (null == instance){
            instance = new Singleton3();
        }
        return instance;
    }
}
