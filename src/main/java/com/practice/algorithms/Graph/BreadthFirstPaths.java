package com.practice.algorithms.Graph;

import com.practice.algorithms.Queue;
import com.practice.algorithms.Stack;

/**
 * 广度优先搜索查找
 */

public class BreadthFirstPaths {
    private boolean[] marked ;//到达该顶点的最短路径已知吗
    private int [] edgeTo;
    private final  int s ;//起点

    public BreadthFirstPaths(Graph G ,int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Graph G , int s){
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true; //标记起点
        queue.enqueue(s); //将它加入队列
        while(!queue.isEmpty()){
            int v = queue.dequeue(); //从队列删去下一个顶点
            for (int w: G.adj(v)) {
                if (!marked[w]){
                    edgeTo[w] = v ;    //保存最短路径的最后一条边
                    marked[w] = true ; //标记它，因为最短路径已知
                    queue.enqueue(w);  //并将它加入队列
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v ; x != s ; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
}