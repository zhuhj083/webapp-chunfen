package com.practice.designPattern.singleton;

/**
 * 懒汉模式
 *
 * 懒汉模式中单例是在需要的时候才去创建的
 * 如果单例已经创建，再次调用获取接口将不会重新创建新的对象，而是直接返回之前创建的对象
 *
 * 但是这里的懒汉模式并没有考虑线程安全问题，
 * 在多个线程可能会并发调用它的getInstance()方法，导致创建多个实例，因此需要加锁解决线程同步问题
 *
 */
public class Singleton2 {
    private Singleton2(){
    }

    private static Singleton2 instance = null ;

    public static Singleton2 getInstance() {
        if (null == instance){
            instance = new Singleton2();
        }
        return instance;
    }
}
