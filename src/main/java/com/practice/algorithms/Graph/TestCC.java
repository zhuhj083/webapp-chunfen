package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zhj on 2018/7/5.
 */

public class TestCC {
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        CC cc = new CC(G);

        int M = cc.count();
        StdOut.println(M + " components");

        //初始化
        Bag<Integer>[] components;
        components = (Bag<Integer>[] )new Bag[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Bag<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].add(v);  //cc.id(v) 获得v所在的分量的id （0~M-1 之间）
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