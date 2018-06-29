package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zhj on 2018/6/29.
 */

public class SelectionSort {

    public static void main(String[] args) {
        String a [] = {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a){
        //将a[] 按照升序排序
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i ;
            for (int j = i + 1; j < N ; j++) {
                if (less(a[j],a[min])){
                    exch(a,j,min);
                }
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