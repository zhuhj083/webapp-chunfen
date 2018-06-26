package com.practice.designPattern.singleton;

/**
 * 静态内部类实现
 *
 * 这种方式同样利用了类加载机制来保证只创建一个instance实例
 * 只要应用中不使用内部类，JVM就不会去加载这个单例类，也就不会创建单例对象
 * 从而实现懒汉式的延迟加载。也就是说这种方式可以同时保证延迟加载和线程安全
 */
public class Singleton5 {
    private Singleton5(){
    }

    private static class SingletonHolder{
        public static Singleton5 instance = new Singleton5();
    }

    public static Singleton5 getInstance(){
        return SingletonHolder.instance;
    }
}
