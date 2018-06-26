package com.practice.offerBook;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 面试题6:从尾到头打印链表
 *  输入一个链表的头结点，从尾到头打印出每个结点的值
 *  方案一：首先遍历链表的节点后打印，典型的“后进先出”，可以使用栈来实现
 */
public class Question6 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = null;
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode();

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

//        printListFromTailToHeadByStack(node1);
//
//        printListFromTailToHead(node1);
//        printListFromTailToHead(node5);
//        printListFromTailToHead(node6);
//        printListFromTailToHead(node7);

        printListFromTailToHeadByReverseList(node1);
    }

    /**
     * 方案一：通过使用栈结构，遍历链表，把先遍历的节点的值推入栈中，遍历结束后通过弹出栈内元素实现逆序打
     */
    public static void printListFromTailToHeadByStack(ListNode node){
        Stack<Integer> stack = new Stack<>();
        while (node != null){
            stack.push(node.val);
            node = node.next;
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+",");
        }
    }

    /**
     * 方案二：递归法逆序打印链表
     */
    public static void printListFromTailToHead(ListNode node){
        if (node != null){
            if (node.next != null){
                printListFromTailToHead(node.next);
            }
            System.out.print(node.val + ",");
        }else{
            System.out.println("输入的链表为空");
        }
    }

    /**
     * 方案三：使用ArrayList逆序打印链表
     */
    public static void    printListFromTailToHeadByArrayList(ListNode node){
        if(node==null)
            System.out.print("输入链表为null");
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        while(node!=null){
            arrayList.add(node.val);
            node=node.next;
        }
        for(int i=arrayList.size()-1;i>=0;i--){
            System.out.print(arrayList.get(i)+",");
        }
    }

    /**
     * 方案四：递归反转链表，后遍历打印
     */
    public static void printListFromTailToHeadByReverseList(ListNode node){
        ListNode reversedNode = reverse(node);
        while(reversedNode!=null){
            System.out.print(reversedNode.val+",");
            reversedNode = reversedNode.next;
        }
    }

    /**
     * 递归反转链表
     */
    private static ListNode reverse(ListNode head){
        if(head.next == null)
            return head;
        ListNode reversedListNode = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reversedListNode;
    }

}

class ListNode{
    int val ;
    ListNode next = null;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }
}
