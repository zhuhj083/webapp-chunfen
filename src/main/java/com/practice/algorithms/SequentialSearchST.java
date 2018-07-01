package com.practice.algorithms;

/**
 * 用链表实现符号表
 * 顺序查找，基于无序链表
 * 顺序查找是非常低效的
 */
public class SequentialSearchST<Key,Value> {

    private Node first ;

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

    /**
     * 链表结点的定义
     */
    private class Node{
        Key key;
        Value value;
        Node next ;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
