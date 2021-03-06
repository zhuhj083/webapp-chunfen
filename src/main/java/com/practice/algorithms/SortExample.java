package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;

/**
 * 排序的模板
 */

public class SortExample {
    public static void sort(Comparable[] a){
        //选择排序、插入排序、希尔排序、归并排序、快速排序、堆排序  各自实现
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