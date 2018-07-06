package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 最小生成树的测试用例
 */

public class TestMST {
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        KruskalMST mst = new KruskalMST(G);

        for (Edge e : mst.edges()) {
            StdOut.println("edge:"+e);
        }
        StdOut.println(mst.weight());
    }
}