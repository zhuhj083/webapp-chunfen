package com.practice.algorithms.String;

import com.practice.algorithms.HeapSort;
import com.practice.algorithms.InsertionSort;
import com.practice.util.SleepUtils;
import com.practice.util.Stopwatch;

/**
 * 高位优先的字符串排序
 */
public class MSD {
    private static int R = 256 ; //基数
    private static final int M = 15 ; //小数组的切换阙值。长度小于这个的数组，用插入排序来排
    private static String[] aux ;

    private static int charAt( String s , int d ){
        if (d < s.length()){
            return s.charAt(d);
        }else{
            return -1;
        }
    }

    public static void sort(String a []){
        int N = a.length;
        aux = new String[N];
        sort(a,0,N-1,0);
    }

    private static void sort(String[] a , int lo ,int hi , int d ){
        //以第d个字符为键，将a[lo] 至 a[hi] 排序
        if ( hi <= lo + M ){
            insertion( a ,lo , hi , d );
            return;
        }

        int [] count = new int[ R + 2 ];
        //计算评率
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i],d)  + 2 ]++ ;
        }

        //将频率转换为索引
        for (int r = 0; r < R + 1; r++) {
            count[r+1] += count[r];
        }

        //数据分类
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i],d) + 1] ++ ] = a[i];
        }

        //回写
        for (int i = lo; i <= hi ; i++) {
            a[i] = aux[i-lo];
        }

        //递归的以每个字符为键排序
        for (int r = 0; r < R ; r++) {
//            System.out.println("r="+(char)R);
            sort(a,lo+count[r],lo+count[r+1]-1,d+1);
        }

    }


    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(String v , String w , int d) {
        for ( int i = d; i < Math.min( v.length(), w.length() ); i++ ) {
            if (v.charAt(i) < w.charAt(i))
                return true;
            if (v.charAt(i) > w.charAt(i))
                return false;
        }
        return v.length() < w.length();
    }

    public static void main(String[] args) {
        String[] small = {"zhuhaijun","hefei","beijing","anhui","chaoyang","chaohu","nenu","software","sogou","changyou"};

        String[] big = new String[100000];
        for (int i = 0; i < 100000 ; i++) {
            big[i] = small[ i % small.length ];
        }

        Stopwatch stopwatch = new Stopwatch();
        sort(big);
        System.out.println(stopwatch.elapsedTimeMillis());

        for (String str:big) {
//            System.out.println(str);
        }
    }


}
