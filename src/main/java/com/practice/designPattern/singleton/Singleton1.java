package com.practice.designPattern.singleton;

/**
 * 恶汉模式
 *
 * 饿汉模式是最简单的一种实现方式
 * 饿汉模式在类加载的时候就对实例进行创建，实例在整个程序周期都存在。
 *
 * 优点：是只在类加载的时候创建一次实例，不会存在多个线程创建多个实例的情况，避免了多线程同步的问题。
 * 缺点：即使这个单例没有用到也会被创建，而且在类加载之后就被创建，内存就被浪费了
 */
public class Singleton1 {

    /**
     * 类的构造函数定义为private的，保证其他类不能实例化此类
     */
    private Singleton1() {
    }

    private static Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance(){
        return instance;
    }
}
