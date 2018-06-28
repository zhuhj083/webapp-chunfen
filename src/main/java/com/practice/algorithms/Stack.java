package com.practice.algorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 链表实现的栈
 */

public class Stack<T> implements Iterable<T> {

    private Node first;
    private int N;

    public boolean isEmpty(){
        return N == 0 ; //或者 fist == null;
    }

    public int size(){
        return N;
    }

    public void push(T t){
        Node oldFirst = first ;
        first = new Node();
        first.t = t ;
        first.next = oldFirst;
        N++;
    }

    public T pop(){
        T t = first.t;
        first = first.next;
        N--;
        return t ;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T>{

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

    private class Node{
        T t ;
        Node next ;
    }
    
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (item.equals("eof")){
                break;
            }else if (!item.equals("-")){
                stack.push(item);
            }else if (!stack.isEmpty()){
                StdOut.println(stack.pop());
            }
        }

        StdOut.println(stack.size()+" left on stack" );

        if (!stack.isEmpty()){
            for (String str : stack){
                StdOut.print(str+" ");
            }
        }

    }
    
}