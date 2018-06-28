package com.practice.util;

/**
 * 返回自对象创建以来所经过的时间
 */
public class Stopwatch {
    private final long start ;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime(){
        long now = System.currentTimeMillis();
        return ( now - start ) / 1000.0;
    }

    public long elapsedTimeMillis(){
        return System.currentTimeMillis() - start ;
    }

}
