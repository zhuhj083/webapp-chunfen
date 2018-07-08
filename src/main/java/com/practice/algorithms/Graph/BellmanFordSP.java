package com.practice.algorithms.Graph;

import com.practice.algorithms.Queue;
import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.Stack;

/**
 * 基于队列的Bellman-Ford算法
 * 一般加权有向图中的最短路径问题
 * 可能有负值，可能有环
 */
public class BellmanFordSP {
    private double [] distTo;                   //从起点到某个顶点的路径的长度
    private DirectedEdge[] edgeTo;              //从起点到某个顶点的最后一条边
    private boolean[] onQ;                      //该顶点是否在队列中
    private Queue<Integer> queue;               //正在被放松的顶点
    private int cost;                           //relax()调用的次数
    private Iterable<DirectedEdge> cycle;       //edgeTo[]中是否有负权重环

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<>();
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0 ;
        queue.enqueue(s);
        onQ[s] = true ;

        while(!queue.isEmpty() && !hasNegativeCycle() ){
            int v = queue.dequeue();
            onQ[v] = false ;
            relax(G,v);
        }
    }

    private void relax(EdgeWeightedDigraph G , int v){
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e ;
                if (!onQ[w]){
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0){
                findNegativeCycle();
            }
        }
    }

    private void findNegativeCycle(){

    }

    private boolean hasNegativeCycle(){
        return false ;
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
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