package com.ramamosr;

import javax.swing.*;
import javax.xml.stream.events.Characters;
import java.util.Arrays;
import java.util.HashMap;
import java.nio.*;
import java.util.stream.IntStream;

public class HashMapValidSudoku {
    /*
    Valid Sudoku
Determine if a Sudoku is valid, according to: http://sudoku.com.au/TheRules.aspx

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.



The input corresponding to the above configuration :

["53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79"]
A partially filled sudoku which is valid.

 Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
Return 0 / 1 ( 0 for false, 1 for true ) for this problem
     */

    public int isValidSudoku(final String[] A) {
        int result = 1;

        //if the matrix is not a perfect square return false as this will not be a valid sudoku matrix
        int sqr = (int) Math.sqrt(A.length);
        if(sqr*sqr != A.length)
            return 0;
        boolean hasDigits = false;
        for(String temp : A){
            for(char c : temp.toCharArray())
                if(Character.isDigit(c)) {
                    hasDigits = true;
                    break;
                }
        }
        if(!hasDigits)
            return 1;
        // Check if the length of the individual string is more than the length of the array.
        // this is to check if there is a string value equal to 10 only if this is an perfect square array
        // of length less than 10.
        if(A.length < 10) {
            for (String arr : A) {
                if (arr.length() > A.length)
                    return 0;
            }
        }
        // convert the input string[] to char[][] so it is easy to do array operations.
        char[][] source = new char[A.length][A.length];
        for(int i =0; i<A.length;i++){
            String temp = A[i];
            for(int j =0; j<temp.length();j++){
                source[i][j] = temp.charAt(j);
            }
        }

        // check if the row is valid or not
        for(int i=0; i<source.length;i++){
            boolean validRow = checkIfRowIsValid(source[i]);
            if(!validRow)
                return 0;
        }
        // check if column is valid
        for(int j=0; j<source.length;j++){
            boolean validColumn = checkIfColumnIsValid(source,j);
            if(!validColumn)
                return 0;
        }

        // check if sub matrices are true. in 9*9 matrix and above
        for(int k = 0; k < source.length - (sqr-1); k+=sqr) {
            for (int l = 0; l < source.length - (sqr - 1); l += sqr) {
                    int startRow = k;
                HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
                while (startRow < k + sqr) {
                    int startColumn = l;
                    while (startColumn < l + sqr) {
                        if (hm.containsKey(source[startRow][startColumn]))
                            return 0;
                        if (Character.isDigit(source[startRow][startColumn]))
                            hm.put(source[startRow][startColumn], 1);
                        //System.out.println("Row is " + (startRow) + "  Column is  " + (startColumn));
                        startColumn++;
                    }
                    startRow++;
                }
            }
        }
        return result;
    }

    public boolean checkIfRowIsValid(char[] row){
        HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
        for(int i=0; i < row.length; i++){
            if(hm.containsKey(row[i]))
                return false;
            if(Character.isDigit(row[i])){
                hm.put(row[i],1);
            }
        }
        return true;
    }

    public boolean checkIfColumnIsValid(char[][] source, int col ){
        HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
        for(int i=0; i < source.length; i++){
            if(hm.containsKey(source[i][col]))
                return false;
            if(Character.isDigit(source[i][col])){
                hm.put(source[i][col],1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] input = {".........", ".7....7..", "3........", "...2.....", "....4..3.", "....6..7.", "....1....", "..3...67.", "........."};

        HashMapValidSudoku hsvs = new HashMapValidSudoku();
        System.out.println(hsvs.isValidSudoku(input));

    }

}
