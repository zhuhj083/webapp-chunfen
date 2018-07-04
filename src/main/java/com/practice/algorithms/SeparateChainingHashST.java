package com.practice.algorithms;

import java.util.Iterator;

/**
 * 基于拉链法的散列表
 */

public class SeparateChainingHashST<Key,Value> {
    private int N ; //键值对总数
    private int M ; //散列表的大小
    private SequentialSearchST<Key,Value>[] st;

    public SeparateChainingHashST(int m) {
        this.M = m;
        st = (SequentialSearchST<Key,Value>[])new SequentialSearchST[M];
        for (int i = 0; i < M ; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public SeparateChainingHashST() {
        this(997);
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M ;
    }

    public Value get(Key key){
        return (Value)st[hash(key)].get(key);
    }

    public void put(Key key , Value value){
        if (N >= M/2)
            resize(2 * M );

        st[hash(key)].put(key,value);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        } else {
            int i = hash(key);
            if (st[i].contains(key)) {
                --N;
            }
            st[i].delete(key);
            if ( M > 4 && N <= 2 * M) {
                resize(M / 2);
            }

        }
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        } else {
            return this.get(key) != null;
        }
    }


    private void resize(int cap) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST(cap);
        for(int i = 0 ; i < M ; ++i) {
            Iterator<Key> var4 = st[i].keys().iterator();
            while(var4.hasNext()) {
                Key key = var4.next();
                temp.put(key , this.st[i].get(key));
            }
        }

        this.M = temp.M;
        this.N = temp.N;
        this.st = temp.st;
    }

}