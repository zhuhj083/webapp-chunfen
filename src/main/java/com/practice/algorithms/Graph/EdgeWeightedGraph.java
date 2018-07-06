package com.practice.algorithms.Graph;

import com.practice.algorithms.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by zhj on 2018/7/6.
 */

public class EdgeWeightedGraph {
    private final int V;
    private int E ;
    private Bag<Edge>[] adj ; //邻接表

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            this.adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in){
        this(in.readInt());     //读取V并将图初始化
        int E = in.readInt();   //读取E
        for (int i = 0; i < E ; i++) {
            //添加一条边
            int v = in.readInt();   //读取一个顶点
            int w = in.readInt();   //读取另一个顶点
            double weight = in.readDouble(); //读取权重
            Edge e = new Edge(v,w,weight);
            this.addEdge(e);
        }
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]){
                if (e.other(v) > v){
                    b.add(e);
                }
            }
        }
        return b ;
    }

    @Override
    public String toString() {
        String s = V + " vertices," + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ":\t";
            for (Edge edge : adj[v]) {
                s += edge + "\t";
            }
            s += "\n" ;
        }
        return s ;
    }

    public int V(){
        return V ;
    }

    public  int E(){
        return E;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(in);
        System.out.println(edgeWeightedGraph);
    }

}