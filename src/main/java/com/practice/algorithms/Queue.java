package com.practice.algorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by zhj on 2018/6/28.
 */

public class Queue<T> implements Iterable<T> {

    private class Node{
        T t ;
        Node next ;
    }

    private Node first ;
    private Node last ;
    private int N ;

    public boolean isEmpty(){
        return first == null ; //或 N==0

    }

    public int size(){
        return N;
    }

    public void enqueue(T t){
        //向队列尾添加元素
        Node oldLast = last ;
        last = new Node();
        last.t = t ;
        last.next = null ;
        if (isEmpty()){
            first = last;
        }else{
            oldLast.next = last;
        }
        N++;
    }

    public T dequeue(){
        //从队列头删除元素
        T t = first.t;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
        N -- ;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null ;
        }

        @Override
        public T next() {
            T t = current.t;
            current = current.next;
            return t;
        }
    }
    
    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (item.equals("eof")){
                break;
            }else if (!item.equals("-")){
                q.enqueue(item);
            }else if (!q.isEmpty()){
                StdOut.println(q.dequeue());
            }
        }

        StdOut.println(q.size()+" left on queue" );

        if (!q.isEmpty()){
            for (String str : q){
                StdOut.print(str+" ");
            }
        }

    }

}