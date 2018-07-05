package com.practice.algorithms.Graph;

import com.practice.algorithms.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by zhj on 2018/7/5.
 */

public class Digraph {
    private final int V ;
    private int E ;
    private Bag<Integer>[] adj ;

    public Digraph(int V) {
        this.V = V ;
        this.E = 0 ;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Digraph(In in){
        this(in.readInt());     //读取V并将图初始化
        int E = in.readInt();   //读取E
        for (int i = 0; i < E ; i++) {
            //添加一条边
            int v = in.readInt();   //读取一个顶点
            int w = in.readInt();   //读取另一个顶点
            addEdge(v,w);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    //添加一条从v到w的边
    public void addEdge(int v , int w){
        adj[v].add(w);
        E++;
    }

    //所有顶点v发出的边
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v) ){
                R.addEdge( w , v );
            }
        }
        return R ;
    }

    @Override
    public String toString() {
        String s = V + " vertices," + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ":";
            for (int w : adj[v]) {
                s += w + " ";
            }
            s += "\n" ;
        }
        return s ;
    }

}