package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 加权有向图 的最短路径api 的测试用例
 */

public class TestSP {
    public static void main(String[] args) {
        EdgeWeightedDigraph G ;
        G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);

//        DijkstraSP sp = new DijkstraSP(G,s);
        AcyclicSP sp = new AcyclicSP(G,s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s +" SP to "+ v );
            StdOut.printf("(%4.2f): ",sp.distTo(v));
            if (sp.hasPathTo(v)){
                for (DirectedEdge edge : sp.pathTo(v)) {
                    StdOut.print(edge + " ");
                }
            }
            StdOut.println();
        }

        StdOut.println();
        StdOut.println();

        AcyclicLP LP = new AcyclicLP(G,s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s +" LP to "+ v );
            StdOut.printf("(%4.2f): ",LP.distTo(v));
            if (LP.hasPathTo(v)){
                for (DirectedEdge edge : LP.pathTo(v)) {
                    StdOut.print(edge + " ");
                }
            }
            StdOut.println();
        }
    }
}