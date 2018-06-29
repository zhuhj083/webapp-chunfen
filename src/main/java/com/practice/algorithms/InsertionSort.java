package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zhj on 2018/6/29.
 */

public class InsertionSort {

    public static void main(String[] args) {
        String a [] = {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }

    public static void sort(Comparable[] a){
        //将a[] 按照升序排序
        //将a[i]插入到a[i-1],a[i-2],a[i-3]...之中
        //对于1到N-1之间的每一个i，将a[i]与a[0]到a[i-1]中比它小的所有元素依次交换
        int N = a.length;
        for (int i = 1 ; i < N; i++) {
            for (int j = i ;j > 0 && less(a[j],a[j-1]) ; j -- ) {
                exch(a,j,j-1);
            }
        }
    }


    private static boolean less(Comparable v , Comparable w){
        return v.compareTo(w) < 0 ;
    }

    private static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a){
        //单行中打印数组
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            if (less(a[i],a[i-1]))
                return false;
        }
        return true;
    }
}