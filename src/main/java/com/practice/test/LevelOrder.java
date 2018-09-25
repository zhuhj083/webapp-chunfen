package com.practice.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhj on 2018/8/13.
 * 根据层次遍历的顺序，每一层都是从左到右的遍历输出，借助于一个队列。
 * 先将根节点入队，当前节点是队头节点，将其出队并访问，
 * 如果当前节点的左节点不为空将左节点入队，如果当前节点的右节点不为空将其入队。所以出队顺序也是从左到右依次出队。
 *
 */

public class LevelOrder {

    static  class TreeNode{
        int val ;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void levelIterator(TreeNode root){
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);    //将根结点入队列
        TreeNode current ;
        TreeNode nLast = root ;
        TreeNode last = root ;
        while (!queue.isEmpty() ){
            current = queue.poll();    //取出并删除队列第一个元素
            System.out.print( current.val );
            if(current.left != null){   //如果当前节点的左节点不为空入队
                queue.offer(current.left);
                nLast = current.left;
            }
            if(current.right != null){ //如果当前节点的右节点不为空，把右节点入队
                queue.offer(current.right);
                nLast = current.right;
            }
            if (last == current ){
                System.out.println();
                last = nLast ;
            }else{
                System.out.print("-->");
            }
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        levelIterator(root);
    }
    
}