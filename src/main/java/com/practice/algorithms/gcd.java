package com.practice.algorithms;

/**
 * 欧几里得算法:gcd.java
 * 计算两个非负整数p和q的最大公约数：
 * 若q是0，则最大公约数是p。
 * 否则，将p处以q得到余数r,p和q的最大公约数即为q和r的最大公约数
 */
public class gcd {
    public static int gcd(int p ,int q){
        if (q == 0 )
            return p;
        int r = p % q ;
        return gcd(q,r);
    }

    public static void main(String[] args) {
       int gcd =  gcd(9,6);
        System.out.println(gcd);
    }
}
