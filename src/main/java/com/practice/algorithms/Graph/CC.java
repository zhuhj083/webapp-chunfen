package com.practice.algorithms.Graph;

/**
 * 无向图的连通分量
 * 使用深度优先搜索找出图中的所有连通分量
 */
public class CC {
    private boolean [] marked;
    private int [] id ;
    private int count ;

    public CC(Graph G) {
        marked = new boolean[G.V()];    //初始化是否访问过结点
        id = new int[G.V()];            //每个顶点所在的分量的id
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]){
                dfs(G,s);
                count ++ ;
            }
        }
    }

    private void dfs(Graph G , int v){
        marked[v] = true ;
        id[v] = count ;
        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G,w);
            }
        }
    }

    public boolean connected(int v ,int w){
        return id[v] == id[w];
    }

    // v 所在的连通分量的标识符，=（0~count()-1）
    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }

}