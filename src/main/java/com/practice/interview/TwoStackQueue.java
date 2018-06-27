package com.practice.interview;

import java.util.Stack;

/**
 *
 * 使用两个栈来构造一个队列
 *
 * Created by zhj on 2018/6/27.
 */

public class TwoStackQueue<T>{
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    public void appendTail(T t){
        stack1.push(t);
    }

    public T deletedHead(){
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()){
            return null ;
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        TwoStackQueue<Integer> queue = new TwoStackQueue<>();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        queue.appendTail(4);

        System.out.println(queue.deletedHead());
    }

}