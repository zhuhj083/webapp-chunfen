package com.practice.offerBook;

import java.util.Arrays;
import java.util.Random;

/**
 * 面试题3：数组中重复的数字
 *
 * 在一个长度为n的数组里的所有数字都在0~n-1的范围内，
 * 数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复了几次
 * 请找出数组中任意一个重复的数字
 *
 */
public class Question3_RepeatInArray {

    public static final int N = 100 ;
    private static int [] array;

    public static void initArray(){
        Random random = new Random();
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] =  random.nextInt(N);
        }
    }

    public static boolean findRepeat(){
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > array.length - 1) {
                return false;
            }
        }
        for (int i = 0; i < array.length; i++) {
            while( array[i] != i ){
                if (array[i] == array[array[i]] ){
                    System.out.println("找到一个重复的数字：" + array[i] );
                    return true ;
                }
                int temp = array[i];
                array[i] = array[temp] ;
                array[temp] = temp ;
            }
        }
        return false ;
    }

    public static void main(String[] args) {
        initArray();
        System.out.println(Arrays.toString(array));

        boolean flag = findRepeat();
        System.out.println(flag);
    }

}
