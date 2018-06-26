package com.practice.offerBook;

/**
 * 面试题4：二位数组中查找
 *
 * 题目：在一个二位数组中，每一行都是按照从左到右递增的顺序排序
 * 每一列都是按照从上到下的递增排序。请完成一个函数，输入这样一个二位数组和一个整数
 * 判断数组中是否含有这个整数
 */
public class Question4_FIndInDyadicArray {
    public static int[][] matrix = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};

    public static boolean find(int[][] matrix,int rows,int columns,int number){
        boolean found = false ;
        if (matrix != null && rows > 0 && columns > 0){
            //右上角的下标
            int row = 0 ;
            int column = columns - 1 ;
            while(row < rows && column > 0 ){
                if (matrix[row][column] == number){
                    found = true;
                    System.out.println("row="+row+" column="+column +"  number="+matrix[row][column]);
                    break;
                }else if (matrix[row][column] > number){
                    --column;
                }else{
                    ++row;
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        System.out.println(find(matrix,4,4,10));
    }

}
