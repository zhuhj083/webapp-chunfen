package com.practice.algorithms.Graph;

import com.practice.algorithms.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权有向图的数据结构
 */

public class EdgeWeightedDigraph {
    private final int V ;   //顶点的总数
    private int E ;         //边的总数
    private Bag<DirectedEdge> [] adj ; //邻接表

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    public EdgeWeightedDigraph(In in){
        this(in.readInt());     //读取V并将图初始化
        int E = in.readInt();   //读取E
        for (int i = 0; i < E ; i++) {
            //添加一条边
            int v = in.readInt();   //读取from
            int w = in.readInt();   //读取to
            double weight = in.readDouble(); //读取权重
            DirectedEdge e = new DirectedEdge(v,w,weight);
            this.addEdge(e);
        }
    }

    public void addEdge(DirectedEdge e){
        int v = e.from();
        adj[v].add(e );
        E++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]){
                bag.add(e);
            }
        }
        return bag ;
    }

    @Override
    public String toString() {
        String s = V + " vertices," + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ":\t";
            for (DirectedEdge edge : adj[v]) {
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
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        System.out.println(G);
    }


}