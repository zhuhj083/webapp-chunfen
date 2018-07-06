package com.practice.algorithms.Graph;

import com.practice.algorithms.Queue;
import edu.princeton.cs.algs4.MinPQ;

/**
 * 最小生成树的Prim算法的延时实现
 */

public class LazyPrimMST {
    private boolean [] marked; //最小生成树的顶点
    private Queue<Edge> mst ; //最小生成树的边
    private MinPQ<Edge> pq ; //横切边，包括失效的边

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();

        visit(G,0);
        while (!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w])
                continue;
            mst.enqueue(e); //将边添加到树中
            if (!marked[v])
                visit(G,v);
            if (!marked[w])
                visit(G,w);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        double weight = 0;
        for (Edge e: mst) {
            weight += e.weight();
        }
        return weight;
    }


    private void visit(EdgeWeightedGraph G , int v){
        //标记顶点V。并将所有连接V和未被标记顶点的边加入pq
        marked[v] = true ;
        for (Edge e  : G.adj(v)){
            if ( !marked[e.other(v)] ){
                pq.insert(e);
            }
        }

    }

}