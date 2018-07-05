package com.practice.algorithms.Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 符号图
 */

public class SymbolDigraph {

    public static void main(String[] args) {
        String filename = args[0];
        String delim  = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename,delim);
        Digraph G = sg.G;

        while (StdIn.hasNextLine()){
            String source = StdIn.readLine();
            for (int w : G.adj(sg.index(source))) {
                StdOut.println("    "+sg.name(w));
            }
        }
    }

    private ST<String,Integer> st ;   //符号表-->索引
    private String [] keys;                         //索引-->符号表
    private Digraph G ;

    public SymbolDigraph(String stream , String sp) {
        st = new ST<String,Integer>();
        In in = new In(stream);         //第一遍
        while (in.hasNextLine()){       //第一遍构造
            String a[] = in.readLine().split(sp);//读取字符串
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])){
                    st.put(a[i],st.size()); //每个字符串对应一个数字，从0开始递增
                }
            }
        }

        keys = new String[st.size()] ;  //用来获取顶点名的反向索引是一个数组

        for (String name : st.keys()){
            keys[st.get(name)] = name ;
        }

        G = new Digraph(st.size());
        in = new In(stream);    //第二遍
        while (in.hasNextLine()){
            String a[] = in.readLine().split(sp);//读取字符串
            int v =st.get(a[0]);
            for (int i = 1 ; i < a.length; i++) {
                G.addEdge(v , st.get(a[i]) );
            }
        }
    }

    //key是一个顶点吗
    public boolean contains(String s){
        return st.contains(s);
    }

    //key的索引
    public int index(String s){
        return st.get(s);
    }

    //索引v的顶点名
    public String name(int v){
        return keys[v];
    }

    //隐藏的Graph对象
    public Digraph G(){
        return G;
    }


}