package com.practice.algorithms;

/**
 * 红黑二叉查找树
 *
 * 红黑二叉查找树的基本思想是用标准的二叉查找树和一些额外的信息来表示2-3树
 * 我们将树中的链接分为2中类型
 * 红链接表示两个2-节点连接起来够来一个3-节点
 * 黑链接则是2-3树中的普通链接
 */

public class RedBlackBST<Key extends  Comparable<Key>,Value> {

    private Node root ;

    private static final boolean RED = true;
    private static final boolean BLACK=false;

    private class Node{
        Key key;
        Value val;
        Node left ,right ; //左右子树
        int N; //以这个节点为根节点的树的节点个数
        boolean color ; //由其父节点指向它的链接的颜色

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if (x == null)
            return false ;
        return x.color == RED;
    }

    /**
     * 左旋转
     * 用两个链中的较小者作为根节点，变为将较大者作为根节点
     */
    private Node  rotateLeft(Node h){
        Node x = h.right; //x就是较大者，h为较小者
        h.right = x.left;
        x.left = h ;
        x.color = h.color;
        h.color = RED ;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x ;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h ;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x ;
    }

    /**
     * 查找key，找到则更新其值，否则为它新建一个节点
     */
    public void put(Key key,Value val){
        root = put(root,key,val);
        root.color = BLACK;
    }

    private Node put(Node x , Key key , Value val){
        if (x == null)//标准的插入操作，和父节点红链接相连
            return new Node(key,val,1,RED);


        int cmp = key.compareTo(x.key);
        if (cmp < 0 ){ //小节点，加入右侧
            x.left = put(x,key,val);
        }else if (cmp > 0 ){
            x.right = put(x,key,val);
        }else{
            x.val = val;
        }

        //除了这if语句，红黑树中put()的递归实现和二叉查找树中的put()完全一致
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)){
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)){
            flipColors(x);
        }

        x.N = size(x.left) + size(x.right) + 1 ;
        return x ;
    }

    /**
     * 当一个节点有两个红色子节点的时候
     * 转换这个节点的两个子节点的链接为黑色，并把这个节点自己的链接变为红色
     */
    private Node flipColors(Node h){
        h.color = RED;
        h.right.color = BLACK;
        h.left.color = BLACK;
        return h ;
    }

    public  int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null)
            return 0 ;
        return  x.N ;
    }

}