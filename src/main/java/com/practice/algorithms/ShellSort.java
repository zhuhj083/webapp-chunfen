package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;

/**
 * 希尔排序(Shell Sort)是插入排序的一种。也称缩小增量排序，是直接插入排序算法的一种更高效的改进版本。
 * 希尔排序是非稳定排序算法。希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 */

public class ShellSort {
    public static void main(String[] args) {
        String a [] = {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }

    public static void sort(Comparable[] a){
        //将a[]按升序排列
        int N = a.length;
        int h = 1;
        while(h < N / 3){
            h = 3 * h + 1;  //1,4,13,40,121...
        }
        while( h >= 1 ){
            for (int i = h; i < N ; i++) {
                //将a[i]插入到a[i-h],a[i-2*h],,a[i-3*h]...之中
                for (int j = i; j >= h && less(a[j],a[j-h]) ; j -= h ) {
                    exch(a,j,j-h);
                }
            }
            h = h / 3 ;
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