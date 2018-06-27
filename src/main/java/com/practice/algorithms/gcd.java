package com.practice.algorithms;

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
