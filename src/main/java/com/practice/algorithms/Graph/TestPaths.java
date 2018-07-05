package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zhj on 2018/7/5.
 */

public class TestPaths {
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        //DepthFirstPaths paths = new DepthFirstPaths(G,s);

        BreadthFirstPaths paths = new BreadthFirstPaths(G,s);

        for (int v = 0 ; v < G.V() ;v++){
            StdOut.print(s+" to " + v + ": ");
            if (paths.hasPathTo(v)){
                for (int x: paths.pathTo(v)) {
                    if ( x == s )
                        StdOut.print(x);
                    else
                        StdOut.print("-"+x);
                }
            }
            StdOut.println();
            StdOut.println();
        }
    }

}