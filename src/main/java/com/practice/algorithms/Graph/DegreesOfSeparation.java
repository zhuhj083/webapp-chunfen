package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 间隔的度数
 */

public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0],args[1]);
        Graph G = sg.G();

        String source = args[2];

        if (!sg.contains(source)){
            StdOut.println(source + " not in database.");
            return;
        }

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G,s);
        while (!StdIn.isEmpty()){
            String slink = StdIn.readLine();
            if (sg.contains(slink)){
                int t = sg.index(slink);
                if (bfs.hasPathTo(t)){
                    for (int v:bfs.pathTo(t)){
                        StdOut.println("    "+sg.name(v));
                    }
                }else{
                    StdOut.println("Not connected");
                }
            }else{
                StdOut.println(" Not in database.");
            }
        }


    }
}