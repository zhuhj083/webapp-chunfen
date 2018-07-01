package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;

public class HeapSort {

    public static void main(String[] args) {
        String a [] = {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }

    public static void sort(Comparable[] a){
       int N = a.length;

       for ( int k = N / 2 ; k >= 1 ; k-- ){  //从最后一个子树开始
           sink( a ,k , N );
       }

       //此时，最大的元素在根节点
       //从根节点开始，将最大的元素依次交换到最后
       while (N > 1 ){
           exch(a,1,N--);
           sink(a,1,N);
       }

    }

    /**
     * 将a[i] 到 a[j] 之间的元素排序
     */
    private static void sink(Comparable[] a , int k , int n ){
        while( 2 * k <= n ){
            int j = 2 * k ;

            if (j < n && less(a,j , j +1)){
                j++;   //寻找两个子几点中大的那个
            }
            if (!less(a,k,j)){  //如果父结点比大的那个子结点大。循环结束
                break;
            }
            exch(a,k ,j);    //交换
            k = j ;       //下沉一级
        }
    }

    private static void exch(Comparable[] a ,int i,int j){
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }

    private static boolean less(Comparable[] a,int i,int j){
        return a[i-1].compareTo(a[j-1]) < 0 ;
    }

    private static void show(Comparable[] a){
        //单行中打印数组
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 1 ; i < a.length; i++) {
            if (less(a,i+1 ,i))
                return false;
        }
        return true;
    }



}
