package com.practice.interview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 使用两个队列来构造一个栈
 *
 * Created by zhj on 2018/6/27.
 */

public class TwoQueueStack<T> {

    Queue<T> queue1 = new LinkedList<>();
    Queue<T> queue2 = new LinkedList<>();

    /**
     * 向栈中压入数据
     */
    public void push(T t){
        if (queue1.isEmpty() && queue2.isEmpty()){
            queue1.add(t);
            return ;
        }

        if (queue1.isEmpty()){
            queue2.add(t);
            return;
        }

        if (queue2.isEmpty()){
            queue1.add(t);
            return;
        }
    }

    /**
     * 从栈中弹出一个数据
     */
    public T pop(){
        if (queue1.isEmpty() && queue2.isEmpty()){
            return null;
        }
        //如果queue1中没有数据，queue2中有数据，将queue2中的元素依次放入queue1中，直到最后一个元素，弹出
        if (queue1.isEmpty()){
            while (queue2.size() > 1 ){
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }

        if (queue2.isEmpty()){
            while (queue1.size() > 1 ){
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }
        return null ;
    }

    public static void main(String[] args) {
        TwoQueueStack<Integer> qs = new TwoQueueStack();
        qs.push(2);
        qs.push(4);
        qs.push(7);
        qs.push(5);

        System.out.println(qs.pop());
        System.out.println(qs.pop());

        qs.push(1);
        System.out.println(qs.pop());

        System.out.println("------------------------------------------");

        Stack<Integer> stack = new Stack();
        stack.push(2);
        stack.push(4);
        stack.push(7);
        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.pop());

        stack.push(1);
        System.out.println(stack.pop());


    }

}