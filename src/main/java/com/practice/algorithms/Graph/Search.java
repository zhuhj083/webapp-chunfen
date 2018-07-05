package com.practice.algorithms.Graph;

/**
 * 找到和起点s连通的所有顶点
 */

public class Search {
    private  Graph G;
    private  int s ;

    public Search(Graph g, int s) {
        G = g;
        this.s = s;
    }

    //这么写是错的.这么写只是查到直接相连的顶点，没有查到间接相连的顶点
    public  boolean marked(int v){ //v和s是连通的吗
        for (int w : G.adj(s)) {
            if ( w == v){
                return true;
            }
        }
        return false;
    }

    //与s连通的顶点的总数
    public int count(){
        int count = 0 ;
        for (int w : G.adj(s)){
            if (w != s){//边中有链接到自己的顶点，说明有一条边
                count ++ ;
            }
        }
        return count;
    }

}