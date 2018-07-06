package com.practice.algorithms.Graph;

/**
 * 计算强连通分量的Kosarajusuanfa
 */

public class KosarajuSCC {
    private boolean [] marked; //已经访问过的顶点
    private int [] id;          //强连通分量的标识符
    private int  count ;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        //这段不好理解
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()){
            if ( ! marked[s] ){
                dfs(G,s);
                count ++ ;
            }
        }

    }

    //深度优先搜索
    private void dfs(Digraph G , int v){
        marked[v] = true ;
        id[v] = count;
        for ( int w : G.adj(v) ){
            if (!marked[w]){
                dfs(G,w);
            }
        }
    }

    public boolean stronglyConnected(int v,int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return  id[v];
    }

    public int count(){
        return count;
    }

}