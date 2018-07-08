package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.Stack;

/**
 * 无环加权有向图的 最长 路径算法
 */
public class AcyclicLP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph G , int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0 ;

        Topologic top = new Topologic(G);
        for (int v : top.order()){
            relax(G,v);
        }
    }
    private void relax(EdgeWeightedDigraph G ,int v){
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)){
            return null ;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge x = edgeTo[v] ; x != null ; x = edgeTo[x.from()] ){
            path.push(x);
        }
        return path;
    }
}
