package com.practice.algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Multiway {
    public static void merge(In[] streams){
        int N = streams.length;
        IndexMaxPQ<String> pq = new IndexMaxPQ<>(N);

        for (int i = 0; i < N; i++) {
            if (!streams[i].isEmpty()){
                pq.insert(i,streams[i].readString());
            }
        }

        while (!pq.isEmpty()){
            StdOut.println(pq.max());
            int i = pq.delMax();

            //在重新读进去
            if (!streams[i].isEmpty()){
                pq.insert(i,streams[i].readString());
            }
        }
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N ; i++) {
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }
}
