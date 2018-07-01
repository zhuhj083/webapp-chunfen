package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序递归地将子数组a[lo...hi]排序，
 * 先用partition（）方法将a[j]放到一个合适位置，然后在递归调用将其他位置的元素排序
 *
 * 关键在于切分，这个过程需要满足下面三个条件
 * 1、对于某个j，a[j]已经排定
 * 2、a[lo]到a[j-1]中的所有元素都不大于a[j]
 * 3、a[j+1]到a[hi]中的所有元素都不大于a[j]
 */
public class QuickSort {

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
        //if (hi<= lo ) return ;
        if (hi<= lo + 5 ){
            InsertionSort.sort(a,lo,hi);
            return;
        }
        int j = partition(a,lo,hi);  //切分
        sort(a,lo,j-1); //将左半部分a[lo...j-1]排序
        sort(a,j+1,hi);//将右半边a[j+1...hi]排序
    }

    private static int partition(Comparable[]a,int lo,int hi){
        //将数组切分为a[lo...i-1],a[i],a[i+1...hi]
        int i = lo , j = hi + 1 ;  //左右扫描指针，i从左向右移动，j从右向左移动
        Comparable v = a[lo];   //随意找一个切分元素，将它移动到正确的位置上去
        while (true){
            //扫描左右，检查扫描是否结束并交换元素
            while (i < hi){   //i一直向右移动，直到找到一个比切分元素v大的元素
                if (less(v,a[++i])){
                    break;
                }
            }
            while (j > lo){  //j一直向左移动，直到找到一个比v小的元素
                if (less(a[--j],v)){
                    break;
                }
            }
            if ( i >= j ) {
                break;
            }
            exch(a,i,j);
        }
        exch(a,lo,j);  //将v=a[j]放入正确的位置
        return j ; //将a[lo...j-1] <= a[j] <= a[j+1...hi]达成
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
