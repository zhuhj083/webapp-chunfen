package com.practice.algorithms.String;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.io.FileInputStream;

public class Count {
    public static void main(String[] args) throws Exception{
        Alphabet alphabet = new Alphabet(args[0]);
        int R = alphabet.R();
        int[] count = new int[R];

        FileInputStream input = new FileInputStream("F:\\algs4-data\\abra.txt");
        System.setIn(input);
        String s = StdIn.readAll();
        int N = s.length();
        for (int i = 0; i < N; i++) {
            if (alphabet.contains(s.charAt(i))){
                count[alphabet.toIndex(s.charAt(i))]++;
            }
        }

        for (int c = 0; c < R ; c++) {
            System.out.println(alphabet.toChar(c) + "   "+count[c]);
        }

    }
}
