package com.practice.algorithms.Graph;

import com.practice.algorithms.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 邻接表 表示图
 * 长度为V的数组adj[V]表示所有的顶点 0到V-1
 * adj[i] 表示与顶点i链接的所有的边
 */

public class Graph {
    private final int V ;   //顶点数目
    private int E ;         //边的数目
    private Bag<Integer>[] adj; //邻接表  adj[v] 表示和顶点v相邻的所有顶点的集合

    public Graph(int V) {
        this.V = V ;
        this.E = 0 ;
        adj = (Bag<Integer>[])new Bag[V];
        for (int  v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(In in){
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
        return V ;
    }

    public int E(){
        return E ;
    }

    public void addEdge(int v , int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    //返回与v相邻的所有的顶点
    public Iterable<Integer> adj(int v){
        return adj[v];
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

    //计算顶点的度，即依附于它的边的总数
    public static int degree(Graph G , int v){
        int degree = 0 ;
        for (int w : G.adj(v)){
            degree ++ ;
        }
        return degree;
    }

    //计算所有顶点的最大的度数
    public static int maxDegree(Graph G){
        int max = 0 ;
        for (int v = 0 ; v < G.V() ; v++){
            if (degree(G,v) > max){
                max = degree(G,v);
            }
        }
        return max ;
    }

    //计算所有顶点的平均度数
    public static double avgDegree(Graph G){
        return 2.0 * G.E() / G.V();
    }

    //计算子环的个数
    public static int numberOfSelfLoops(Graph G){
        int count = 0 ;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)){
                if (w == v){//边中有链接到自己的顶点，说明有一条边
                    count ++ ;
                }
            }
        }
        return count ;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        System.out.println(graph);
    }
    
}