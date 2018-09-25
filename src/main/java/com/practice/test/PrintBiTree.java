package com.practice.test;

import java.util.ArrayList;

/**
 * Created by zhj on 2018/8/13.
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */

public class PrintBiTree {
    static  class TreeNode{
        int val ;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    //存放所有符合条件的路径
    ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    //存放一条符合条件的节点路径
    ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> findPath(TreeNode root ,int target) {
        if (root == null )
            return listAll;


        list.add( root.val );
        target -= root.val;
        //当遍历到叶子结点且满足条件的时候，才将路径上的结点加入其中
        if (target == 0 && root.left == null && root.right == null )
            listAll.add(list);

        //如果不是叶子结点，继续向下遍历，直到叶子结点为止
        findPath(root.left ,target);
        findPath(root.right ,target);

        list.remove(list.size() - 1);

        return listAll;

    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> listA = new PrintBiTree().findPath( root ,30);

        if (listA.isEmpty())
            System.out.println("is empty");
        else
            System.out.println(listA.size());

        for (ArrayList<Integer> l : listA){
            System.out.println(l.size());
            for (Integer i :l){
                System.out.print( i + " -->");
            }
        }

    }
    
}