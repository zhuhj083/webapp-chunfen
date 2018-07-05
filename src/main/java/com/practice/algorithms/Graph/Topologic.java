package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.StdOut;

/**
 * 拓扑排序
 */

public class Topologic {
    private Iterable<Integer> order ;  //顶点的拓扑顺序

    public Topologic(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    //拓扑有序的所有结点
    public Iterable<Integer> order() {
        return order;
    }

    //G是有向无环图吗
    public boolean isDAG(){
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String sepatator = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename,sepatator);

        Topologic top = new Topologic(sg.G());
        for (int v : top.order() ){
            StdOut.println(sg.name(v));
        }
    }

}