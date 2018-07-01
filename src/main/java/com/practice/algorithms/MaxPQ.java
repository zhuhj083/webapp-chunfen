package com.practice.algorithms;

/**
 * 基于二叉堆的优先队列
 *  当一棵二叉树的每个结点都大于等于它的两个子结点树时，它被称为堆有序
 */
public class MaxPQ<T extends Comparable<T>> {

    private T[] pq; //基于堆的完全二叉树
    private int N = 0; //纯属于pq[1...N]中，pq[0] 没有使用

    public MaxPQ(int maxN){
        pq = (T []) new Comparable[maxN + 1] ;
    }

    public boolean isEmpty(){
        return N == 0 ;
    }

    public int size(){
        return N;
    }

    public void insert(T t){
        pq[++N] = t;
        swim(N);
    }

    public T delMax(){
        T max = pq[1]; //从根结点得到最大元素
        exch(1,N--);//将其和最后一个结点交换
        pq[N+1] = null ;//防止对象游离
        sink(1);
        return max ;
    }

    private void swim( int k){
        while ( k > 1 && less(k,k/2)){//如果它比他的父节点大
            exch(k,k/2);
            k = k / 2 ;
        }
    }

    private void sink(int k){
        while( 2 * k <= N){
            int j = 2 * k ;
            if (j < N && less(j , j +1)){
                j++;   //寻找两个子几点中大的那个
            }
            if (!less(k,j)){  //如果父结点比大的那个子结点大。循环结束
                break;
            }
            exch(k,j);    //交换
            k = j ;       //下沉一级
        }
    }

    private  boolean less(int i , int j ){
        return pq[i].compareTo(pq[j]) < 0 ;
    }

    private  void exch(int i,int j){
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

}
