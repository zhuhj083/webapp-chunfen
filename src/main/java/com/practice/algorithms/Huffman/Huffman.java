package com.practice.algorithms.Huffman;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

/**
 * 霍夫曼编码
 */

public class Huffman {

    private static final  int R = 256 ; //ASCII字母表

    public static void compress(){
        //读取输入
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        //频率统计
        int[] freq = new int[R];
        for (int i = 0; i < input.length ; i++) {
            freq[input[i]]++;
        }
        //构造霍夫曼编码树
        Node root = buildTrie(freq);
        //(递归地)构造编码表
        String [] st = new String [R];
        buildCode(st,root,"");

        //(递归地）打印解码用的单词查找树
        writeTrie(root);

        //打印字符总数
        BinaryStdOut.write(input.length);

        //使用霍夫曼编码处理输入
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '1')
                    BinaryStdOut.write(true);
                else
                    BinaryStdOut.write(false);
            }
        }

        BinaryStdOut.close();

    }

    public static void expand(){
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        for (int i = 0; i < N; i++) {
            //展开第i个编码所对应的字母
            Node x = root;
            while (!x.isLeaf()){
                if (BinaryStdIn.readBoolean()){
                    x = x.right;
                }else{
                    x = x.left;
                }
            }
            BinaryStdOut.write(x.ch);
        }
    }

    private static Node buildTrie(int[] freq){
        //使用多棵单结点树初始化优先队列
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char c = 0 ; c < R ; c++){
            if (freq[c] > 0  ){
                pq.insert(new Node(c,freq[c],null,null));
            }
        }
        while(pq.size() > 1 ){
            //合并两棵频率最小的树
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node('\0',x.freq+y.freq ,x , y);
            pq.insert(parent);
        }
        return pq.delMin();

    }

    private static String[] buildCode(Node root){
        //使用单词查找树构造编译表
        String[] st = new String[R];
        buildCode(st,root,"");
        return st;
    }

    private static void buildCode(String [] st , Node x , String s ){
        //使用单词查找树构造编译表（递归）
        if (x.isLeaf()){
            st[x.ch] = s ;
            return ;
        }
        buildCode(st,x.left,s+'0');
        buildCode(st,x.right,s+'1');
    }


    //输出单词查找树的比特字符串
    private static void writeTrie(Node x){
        if (x.isLeaf()){
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return ;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    //从比特流的前序表示中重建单词查找树
    private static Node readTrie(){
        if (BinaryStdIn.readBoolean()){
            return new Node(BinaryStdIn.readChar(),0,null,null);
        }
        return new Node('\0',0,readTrie(),readTrie());
    }

    private static  class Node implements Comparable<Node>{
        //霍夫曼单词查找树中的结点
        private char ch ; //内部结点不会使用该变量。表示叶子结点中需要被编码的字符
        private int freq ; //展开过程中不会使用该变量。
        private final Node left,right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf(){
            return left == null && right == null ;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }
}


