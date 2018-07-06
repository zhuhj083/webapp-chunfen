package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class IndexMaxPQ <Key extends Comparable<Key>> {

    private int maxN ; //最大存储
    private int n;
    private int[] pq;   //二叉堆
    private int[] qp;   //作用是存储与对象相关的整数在pq数组中的下标 pq[ pq[i] ] == i   qp[ pq[i] ] == i
    private Key[] keys; //实际存放对象的数组

    public IndexMaxPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0 ;
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        keys = (Key[]) new Comparable[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size(){
        return n ;
    }

    public boolean contains(int k){ //是否存在索引为k的元素
        if ( k < 0 || k >= maxN) throw new IndexOutOfBoundsException();
        return qp[k] != -1;
    }

    public void insert(int k,Key key){
        if ( k < 0 || k > maxN ){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (contains(k)){
            throw new IllegalArgumentException("index is already in the priority queue");
        }
        n++;
        qp[k] = n;
        pq[n] = k ;
        keys[k] = key;

        swim(n);
    }

    //将索引为k的元素设为key
    public void  change(int k ,Key  key){
        if ( k < 0 || k > maxN ){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (!contains(k)){
            throw new IllegalArgumentException("index is not in the priority queue");
        }

        keys[k] = key;

        //上浮或下沉到指定位置
        swim(qp[k]);
        sink(qp[k]);
    }

    //删除索引k及其相关联的元素
    public void delete(int k){
        if (k < 0 || k >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");

        //把他交换到最后去，最后的元素最小，所以交换后 要下沉
        int index = qp[k];

        //最后一节点拿到第一个位置，再下沉
        exch(index, n--);
        swim(index);
        sink(index);

        keys[k] = null;
        qp[k] = -1;
        pq[n+1] = -1;        // 不需要这个索引了
    }

    /**
     * 返回最大的元素
     */
    public Key max(){
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    /**
     * 返回最大元素的索引，即pq[1]
     */
    public int maxIndex() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     *  删除最大元素，即pq[1];
     */
    public int delMax(){
        if (n == 0)
            throw new NoSuchElementException("Priority queue underflow");

        int index = pq[1];

        //最后一节点拿到第一个位置，再下沉
        exch(1, n--);
        sink(1);

        assert index == pq[n+1]; //验证是不是交换到最后了

        qp[index] = -1 ;    // delete
        keys[index] = null ;  //防止元素游离
        pq[n+1] = -1;        // 不需要这个索引了

        return index ;
    }


    private void swim( int k){
        while ( k > 1 && less( k/2 , k ) ){
            exch(k,k/2);
            k = k / 2 ;
        }
    }

    private void sink(int k){
        while( 2 * k <= n){
            int j = 2 * k ;

            if (j < n && less( j , j +1 ) ){
                j++;   //寻找两个子几点中大的那个
            }

            if (less(j,k)){  //如果父结点比大的那个子结点大。循环结束
                break;
            }
            exch(k,j);    //交换
            k = j ;       //下沉一级
        }
    }

    private  boolean less(int i , int j ){
        return keys[pq[i]].compareTo( keys[pq[j]]) < 0 ;
    }

    private  void exch(int i,int j){
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public static void main(String[] args) {
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
        StdOut.println("length="+strings.length);
        IndexMaxPQ <String> pq = new IndexMaxPQ<String>(strings.length * 2);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        while (!pq.isEmpty()) {
            String max = pq.max();
            int i = pq.delMax();
            StdOut.println(i + " " + max);
        }
        StdOut.println();
    }
}
