package com.practice.algorithms.String;

/**
 * 低位优先 的字符串排序
 */
public class LSD {

    public static void sort(String[] a , int W){
        //如果字符串的长度均为W
        // 通过前W个字符将 a[] 排序
        int N = a.length;
        int R = 256 ;
        String[] aux = new String[N];

        for (int d = W - 1 ; d >= 0 ; d-- ) {
            //根据第d位字符用键索引计数法排序
            int[] count = new int [R+1]; //计算出现频率
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1 ]++;
            }

            //将频率转换为索引
            for (int r = 0; r < R ; r++) {
                count[r+1] += count[r];
            }

            //将元素分类
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            //回写
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }

        }
    }

    public static void main(String[] args) {
        String[] a = {"LK7378","KP8972","OP0JID","LP9839"};
        sort(a,6);
        for (String str : a) {
            System.out.println(str);
        }

    }


}
