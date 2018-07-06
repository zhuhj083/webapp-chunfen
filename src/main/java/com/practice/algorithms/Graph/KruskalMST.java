package com.practice.algorithms.Graph;

import com.practice.algorithms.Queue;
import com.practice.algorithms.UnionFindQuickUnion;
import edu.princeton.cs.algs4.MinPQ;

/**
 * 最小生成树的Kruskal算法
 */

public class KruskalMST {
    private Queue<Edge> mst ;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()){
            pq.insert(e);
        }

        UnionFindQuickUnion uf = new UnionFindQuickUnion(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1 ){
            Edge e = pq.delMin(); //从pq得到权重最小的边和它的顶点
            int v = e.either();
            int w = e.other(v);

            if (uf.connected(v,w)){
                continue;
            }

            uf.union(v,w);
            mst.enqueue(e);
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


}