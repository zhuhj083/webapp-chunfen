package com.test.concurrency.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhj on 2018/6/25.
 */

public class ConcurrentHashMapTest {
    ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<String, String>();

    ConcurrentLinkedQueue<Object> clq = new ConcurrentLinkedQueue<Object>();

    private static int hash(int h){
        h += (h << 15 ) ^ 0xffffcd7d;
        h ^= (h >>> 10 );
        h += (h << 3 );
        h ^= ( h >>> 6 );
        h += (h << 2 ) + (h << 14);
        return h ^ (h >>> 16);
    }



    public static void main(String[] args) {
        System.out.println((hash(1)>>>28)&15);
        System.out.println((hash(2)>>>28)&15);
        System.out.println((hash(3)>>>28)&15);
        System.out.println((hash(4)>>>28)&15);
        System.out.println((hash(5)>>>28)&15);
        System.out.println((hash(6)>>>28)&15);
        System.out.println((hash(7)>>>28)&15);
        System.out.println((hash(8)>>>28)&15);
        System.out.println((hash(9)>>>28)&15);
        System.out.println((hash(10)>>>28)&15);
    }

}