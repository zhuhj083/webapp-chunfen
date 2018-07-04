package com.practice.algorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 用链表实现符号表
 * 顺序查找，基于无序链表
 * 顺序查找是非常低效的
 */
public class SequentialSearchST<Key,Value>{

    private int N;
    private Node first;

    /**
     * 查找给定的键，返回相关联的值
     */
    public Value get(Key key){
        for (Node x = first ; x != null ; x = x.next){
            if (key.equals(x.key)){
                return x.value; //命中
            }
        }
        return null ; //未命中
    }

    /**
     * 查找给定的键，找到则更新其值，否则在表中新建结点
     */
    public void put(Key key , Value value){
        for (Node x = first ; x != null ; x = x.next){
            if (key.equals(x.key)){
               x.value = value;
               return;
            }
        }
        first = new Node(key,value,first);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for(Node x = this.first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        } else {
            return this.get(key) != null;
        }
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        } else {
            this.first = this.delete(first, key);
        }
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        } else if (key.equals(x.key)) {
            --N ;
            return x.next;
        } else {
            x.next = delete(x.next, key);
            return x;
        }
    }

    /**
     * 链表结点的定义
     */
    private class Node{
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value ,Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST();

        String s;
        for(int i = 0; !StdIn.isEmpty(); ++i) {
            s = StdIn.readString();
            st.put(s, i);
        }

        Iterator var4 = st.keys().iterator();

        while(var4.hasNext()) {
            s = (String)var4.next();
            StdOut.println(s + " " + st.get(s));
        }

    }
}
