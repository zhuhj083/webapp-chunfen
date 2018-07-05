package com.practice.algorithms.Graph;

/**
 * G是二分图吗？（双色问题）
 * 能够用两种颜色将所有的图的所有的顶点着色，使得任意一条边的两个端点的颜色都不相同
 */

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]){
                dfs( G , s);   //查询s到s
            }
        }
    }

    private void dfs(Graph G , int v){
        marked[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w]){
                color[w] = !color[v];
                dfs(G,w);
            }else if (color[w] == color[v]){
                isTwoColorable = false ;
            }
        }
    }

    public boolean isBipartite(){
        return isTwoColorable;
    }
}