package com.banary.jiegou.stack;

import java.util.Stack;

/**
 * @Description
 * @Author eden
 * @Date 2018/8/30 下午5:40
 */
public class ReverseStack {

    public static <T> void move_bottom_to_top(Stack<T> stack){
        if(stack.isEmpty()){
            return;
        }
        T top1 = stack.pop();
        if(!stack.isEmpty()){
            move_bottom_to_top(stack);
            T top2 = stack.pop();
            stack.push(top1);
            stack.push(top2);
            return;
        }
        stack.push(top1);
    }

    public static <T> void reverse(Stack<T> stack){
        if(stack.isEmpty()){
            return;
        }
        move_bottom_to_top(stack);
        T top = stack.pop();
        reverse(stack);
        stack.push(top);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.forEach(value->System.out.println(value+","));

        reverse(stack);
        stack.forEach(value->System.out.println(value+","));
    }
}
