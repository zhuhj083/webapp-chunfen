package com.practice.algorithms;

public class UnionFindQuickFind {

    private int [] id ; //分量id（以触点作为索引）
    private int count;  //分量数量

    //以整数0~N-1初始化N个触电
    public UnionFindQuickFind(int N){
        //初始化分量id数组
        count = N ;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i ; //每个触点都构成了一个只有它自己的分量，因此我们将id[i]初始化为i
        }
    }

    //连通分量的个数
    public int count(){
        return count;
    }

    //如果p和q存在于同一个分量中，则返回true
    public boolean connected(int p , int q){
        return find(p) == find(q);
    }

    //p(0到N-1)所在的分量的标识符
    public int find(int p){
        return id[p];
    }

    //在p和q之间添加一条连接
    public void union(int p , int q){
        //将p和q归并到相同的分量中
        int pID = find(p);
        int qID = find(q);

        //如果p和q已经在相同的分量之中，则不需要采取任务行动
        if (pID == qID){
            return ;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID){
                id[i] = qID;
            }
        }
        count -- ;
    }

}
