package com.practice.algorithms;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 计算数学公式，支持+ - * / 和( )
 * Created by zhj on 2018/6/28.
 */

public class ExpressionCalculator {
    private static Stack<Double> numberStack = new Stack<Double>();
    private static Stack<String> operationStack = new Stack<String>();

    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static double calculate(String expression){
        double result = 0 ;
        String[] strs = expression.trim().split("\\s+");
        System.out.println("length = "+strs.length);
        if (strs.length > 0){
            for (String str : strs){
                if (str.equals("+")||str.equals("-")||str.equals("*")||str.equals("/")){
                    System.out.println("push "+str);
                    operationStack.push(str);
                }else if (str.equals("(")){
                    continue;
                }else if (isInteger(str)){
                    System.out.println("push "+str);
                    numberStack.push(Double.parseDouble(str));
                }else if (str.equals(")")){
                    if (!numberStack.isEmpty() &&!operationStack.isEmpty()){
                        String operation = operationStack.pop();
                        double hou = numberStack.pop();
                        double qian = numberStack.pop();
                        if (operation.equals("+")){
                            result = qian + hou ;
                        }else  if (operation.equals("-")){
                            result = qian - hou ;
                        }else  if (operation.equals("*")){
                            result = qian * hou ;
                        }else  if (operation.equals("/")){
                            result = qian / hou ;
                        }
                        System.out.println("result "+result);
                        System.out.println("push "+result);
                        numberStack.push(result);
                    }
                }
            }
            return numberStack.pop();
        }
        return result ;
    }
    
    public static void main(String[] args) {
        String expression = "( 5 + ( ( ( 2 + 3 ) * ( 4 / 2 ) ) - 1 ) )";
        System.out.println(calculate(expression));
    }


}