package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort3Way {

    public static void main(String[] args) {
//        String a [] = {"S","O","R","T","E","X","A","M","P","L","E"};
        String a[] = {"K","R","A","T","E","L","E","P","U","I","M","Q","C","X","O","S"};
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);  //打乱顺序，消除对输入的依赖
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int lo ,int hi){
       if (hi <= lo){
           return;
       }
       int lt = lo ,i = lo + 1 , gt = hi ;
       Comparable v = a[lo];
       while (i <= gt ){
           int cmp = a[i].compareTo(v);
           if (cmp < 0 ){
               exch(a ,lt++ ,i++ );
           }else if (cmp > 0 ){
               exch(a , i , gt--);
           }else{
               i++;
           }
       }
       sort(a,lo,lt-1);
       sort(a,gt+1,hi);
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
