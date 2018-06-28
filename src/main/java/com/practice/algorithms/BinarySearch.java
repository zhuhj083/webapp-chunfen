package com.practice.algorithms;

import java.util.Arrays;

/**
 * 二分查找
 */

public class BinarySearch {
    public static int rank(int key , int[] a){
        //数组必须是有序的
        int lo = 0 ;
        int li = a.length - 1 ;
        while(lo <= li){
            //查找的键要么不存在，要么必然存在于a[lo,li]之中
            int mid =  (li+lo) / 2 ;
            if (key < a[mid]){
                li = mid - 1 ;
            }else if (key > a[mid]){
                lo = mid + 1 ;
            }else{
                return mid;
            }
        }
        return -1 ;
    }

    public static void main(String[] args) {
        int [] a = {12,44,5,335,4,1,56,42,165,161,318,484,4485,1515,2154,1512,6545,9,84,7,9};
        Arrays.sort(a);
        System.out.println("有"+a.length+"个数字："+Arrays.toString(a));
        System.out.println(rank(9,a));
    }

}