package com.practice.algorithms;

import com.practice.util.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by zhj on 2018/6/29.
 */

public class SortCompare {
    public static double time(String alg,Double[] a ){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) InsertionSort.sort(a);
        if (alg.equals("Selection")) SelectionSort.sort(a);
        if (alg.equals("Shell")) ShellSort.sort(a);
        if (alg.equals("Merge")) MergeSort.sort(a);
        if (alg.equals("MergeBU")) MergeBUSort.sort(a);
        if (alg.equals("Quick")) QuickSort.sort(a);
        if (alg.equals("Heap")) QuickSort.sort(a);

        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg , int N, int T){
        //使用算法alg将T个长度为N的数组排序
        double total = 0.0 ;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg,a);
        }
        return total;
    }

    public static void main(String[] args) {
//        String alg1 = "Insertion";
//        String alg2 = "Selection";
//        String alg3 = "Shell";
        String alg4 = "Merge";
//        String alg5 = "MergeBU";
        String alg6 = "Quick";
        String alg7 = "Heap";

        int N = 1000000;
        int T = 10 ;

//        double t1 = timeRandomInput(alg1,N,T);
//        double t2 = timeRandomInput(alg2,N,T);
//        double t3 = timeRandomInput(alg3,N,T);
        double t4 = timeRandomInput(alg4,N,T);
//        double t5 = timeRandomInput(alg5,N,T);
        double t6 = timeRandomInput(alg6,N,T);
        double t7 = timeRandomInput(alg7,N,T);

//        System.out.println(t1);
//        System.out.println(t2);
//        System.out.println(t3);
        System.out.println(t4);
//        System.out.println(t5);
        System.out.println(t6);
        System.out.println(t7);

//        StdOut.printf("For %d random Doubles\n  %s is", N ,alg1);
//        StdOut.printf(" %.1f times faster than %s\n" , t2/t1 ,alg2);

    }
    
}