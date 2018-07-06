package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zhj on 2018/7/5.
 */

public class TestSCC {
    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));


        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        System.out.print("逆后序： ");
        for (int s : order.reversePost()){
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.print("顺后序： ");
        for (int s : order.post()){
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.print("前序： ");
        for (int s : order.pre()){
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println();

        KosarajuSCC cc = new KosarajuSCC(G);

        int M = cc.count();
        StdOut.println(M + " 个强连通分量");

        //初始化
        Bag<Integer>[] components;
        components = (Bag<Integer>[] )new Bag[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Bag<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[ cc.id(v) ].add( v );  //cc.id(v) 获得v所在的分量的id （0~M-1 之间）
        }

        //打印所有分量
        for (int i = 0; i < M; i++) {
            for (int v: components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

    }

}