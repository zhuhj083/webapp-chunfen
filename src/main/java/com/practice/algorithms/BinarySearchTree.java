package com.practice.algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class BinarySearchTree<Key extends Comparable<Key>,Value>{

    private Node  root ; //二叉查找树的根节点

    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int N;

        public Node(Key key, Value val,int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    public  int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null){
            return 0 ;
        }else{
            return x.N;
        }
    }

    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x , Key key){
        //在以x为根节点的子树中查找并返回key所对应的值
        //如果找不到，则返回null
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0 ){
            return get(x.left,key);
        }else if (cmp > 0 ){
            return get(x.right,key);
        }else{
            return x.val;
        }
    }

    public void put(Key key ,Value val){
        //查找可key，找到则更新它的值，否则为它创建一个新的节点
        root = put(root,key,val);
    }

    /**
     * 递归实现
     */
    private Node put(Node x , Key key , Value val){
        //如果key存在与以x为根节点的子树中，则更新它的值
        //否则将以key和val为键值对的新节点插入到该子树中
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0 ){
            x.left = put(x.left,key,val);
        }else if (cmp > 0 ){
           x.right = put(x.right,key,val);
        }else{
            x.val = val ;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

    public Key floor(Key key){
        Node x = floor(root,key);
        if (x == null )
            return null;
        else
            return x.key;
    }

    private Node floor(Node x ,Key key){
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0 )
            return x;
        if (cmp < 0 )
            return floor(x.left,key);
        Node t = floor(x.right,key);
        if (t != null)
            return t ;
        else return x;
    }

    public Key ceiling(Key key){
        Node x = ceiling(root,key);
        if (x == null )
            return null;
        else
            return x.key;
    }

    private Node ceiling(Node x ,Key key){
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0 )
            return x;
        if (cmp > 0 )
            return ceiling(x.right,key);
        Node t = ceiling(x.left,key);
        if (t != null)
            return t ;
        else
            return x;
    }

    public Key select(int k){
        return select(root,k).key;
    }

    /**
     * 以x为根节点，查找排名为k的节点
     * 如果左子树中的结点树t大于k，继续递归地在左子树中查找排名为k的键
     * 如果t=k，返回根节点中的键
     * 如果t<k。那么我们就递归地在右子树中查找排名为（k-t-1）的键
     */
    private Node select(Node x,int k){
        if (x == null)
            return null;
        int t = size(x.left);
        if ( t > k ) {
            return select(x.left, k);
        }else if (t < k ){
            return select(x.right,k-t-1);
        }else {
            return x;
        }
    }

    public int rank(Key key){
        return rank(root,key);
    }

    private int rank(Node x,Key key){
        //返回以x为根节点的子树中小于x.key的键的数量
        if (x== null )
            return 0 ;
        int cmp = key.compareTo(x.key);
        if (cmp < 0 ){
            return rank(x.left,key);
        }else if (cmp > 0){
            return rank(x.right,key);
        }else{
            return size(x.left);
        }
    }

    /**
     * 不断深入根节点的左子树，直至遇见一个空链接
     * 然后将指向该节点的链接指向该节点的右子树（只要在递归调用中返回它的右链接即可）
     */
    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x ;
    }

    public void deleteMax(){
        root = deleteMax(root);
    }

    private Node deleteMax(Node x){
        if (x.right == null)
            return x.left;
        x.right = deleteMin(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x ;
    }

    public void delete(Key key){
        root = delete(root,key);
    }

    private Node delete(Node x ,Key key){
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0 )
            x.left = delete(x.left,key);
        else if (cmp > 0)
            x.right = delete(x.right,key);
        else{
            //找到了这个要删除的节点了 x
            if (x.right == null)
                return x.right;
            if (x.left == null )
                return x.right;
            Node t = x ;
            x = min(t.right);
            x.right = deleteMin(t.right); //将x的右子树最小节点删除
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x ;
    }

    private void print(Node x){
        if ( x == null)
            return ;
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }

    public Iterable<Key> keys(){
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo , Key hi){
        Queue<Key> queue = new Queue<Key>();
        keys(root,queue,lo,hi);
        return queue;
    }

    private void keys(Node x,Queue<Key> queue,Key lo , Key hi){
        if (x == null )
            return ;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)
            keys(x.left,queue,lo,hi);
        if (cmplo <= 0 && cmphi >= 0 ){
            queue.enqueue(x.key);
        }
        if (cmphi > 0 )
            keys(x.right,queue,lo,hi);
    }

    public static void main(String[] args) {
        BinarySearchTree<String,Integer> bst = new BinarySearchTree<String,Integer>();
        for (int i = 0; i < 1000000; i++) {
            String key = StdRandom.uniform(0,1000)+"";
            int val = 0 ;
            if (bst.get(key)!=null){
                val = bst.get(key) + 1;
            }
            bst.put(key,val);
        }

        for (String key:bst.keys()) {
            System.out.println("key="+key+",value="+bst.get(key));
        }

        System.out.println(bst.size());
        System.out.println(bst.min());
        System.out.println(bst.max());
        System.out.println(bst.floor("50"));

    }
}
