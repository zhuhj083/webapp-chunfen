package com.practice.algorithms.Graph;

/**
 * Created by zhj on 2018/7/5.
 */

public class DepthFirstSearch {
    private boolean [] marked;
    private int count ; //与s连通的顶点个数

    public DepthFirstSearch(Graph G , int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    //
    public void dfs(Graph G, int v){
        marked[v] = true ; //访问一个顶点的时候，将它标记为已访问
        count ++ ;
        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G,w);
            }
        }
    }


    //v和s是连通的吗
    public boolean marked(int v){
        return marked[v];
    }


    public int count(){
        return count ;
    }


}