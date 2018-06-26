package com.practice.designPattern.singleton;

/**
 * 双重校验锁
 */
public class Singleton4 {

    private Singleton4(){
    }

    // 加volatile关键字，可以防止指令重排序。
    // 如果不加volatile，指令重排优化的存在，
    // 导致初始化Singleton和将对象地址赋给instance字段的顺序是不确定的。
    // 在某个线程创建单例对象时，在构造方法被调用之前，就为该对象分配了内存空间并将对象的字段设置为默认值
    // 此时就可以将分配的内存地址赋值给instance字段了，然而该对象可能还没有初始化
    private static volatile  Singleton4 instance = null ;

    public static Singleton4 getInstance(){
        if ( instance == null ){
            synchronized (Singleton4.class){
                if (instance == null){
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }


}
