package com.practice.algorithms;

import java.util.Iterator;

/**
 * 可以动态调整数组大小的实现 栈
 */

public class ResizingArrayStack<T> implements Iterable<T>{

    private T[] a = (T[]) new Object[1];  //栈元素，初始大小为1
    private int N = 0 ;

    public  boolean isEmpty(){
        return N == 0 ;
    }

    public int size(){
        return N ;
    }

    public void push(T t){
        if (N == a.length){
            resize( 2 * N );
        }
        a[N++] = t ;
    }

    public T pop(){
        T t = a[--N] ;
        a[N] = null ; //避免对象游离()
        if (N > 0 && N == a.length / 4 ){
            resize( a.length /2);
        }
        return t ;
    }

    private void resize(int max){
        //将栈移动到一个大小为max的新数组
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < N ; i++) {
            temp[i] = a [i] ;
        }
        a = temp ;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements  Iterator<T>{
        private int i = N ;
        @Override
        public boolean hasNext() {
            return i > 0 ;
        }

        @Override
        public T next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }

}