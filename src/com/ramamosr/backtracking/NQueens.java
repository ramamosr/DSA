package com.ramamosr.backtracking;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class NQueens {
    /*
    NQueens
Problem Description

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer A, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
The final list should be generated in such a way that the indices of the queens in each list should be in reverse lexicographical order.


Problem Constraints
1 <= A <= 10



Input Format
First argument is an integer n denoting the size of chessboard



Output Format
Return an array consisting of all distinct solutions in which each element is a 2d char array representing a unique solution.



Example Input
Input 1:

A = 4
Input 2:

A = 1


Example Output
Output 1:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Output 1:

[
 [Q]
]


Example Explanation
Explanation 1:

There exist only two distinct solutions to the 4-queens puzzle:
Explanation 1:

There exist only one distinct solutions to the 1-queens puzzle:

     */
    static int[] visitedCols;
    static int[] primDiag;
    static int[] secDiag;
    String[][] result;
    char[][] chessboard;
    int size;
    int index = 0;

    public String[][] solveNQueens(int A) {
        size = A;
        chessboard = new char[A][A];
        primDiag = new int[2*size];
        secDiag = new int[2*size];
        visitedCols = new int[size];
        for(char[] ch : chessboard)
            Arrays.fill(ch,'.');
        result = new String[A*A][A];
        fillQueens(chessboard,0);
        String[][] res = new String[index][size];
        if(index > 0){
            System.arraycopy(result,0,res,0,index);
            //Arrays.sort(res, Collections.reverseOrder());
            return res;
        }
        else
            return  new String[0][0];
    }

    public void fillQueens(char[][] chessboard,int row){

        // base condition
        if(row == size) {
            String[] temp = new String[size];
            int cnt = 0;
            for(int j=0;j<size;j++){
                temp[cnt] = new String(chessboard[j]);
                cnt++;
            }
            result[index] = temp;
            index++;
            return;
        }

        for(int cols=0;cols<size;cols++){

            if(primDiag[row-cols+size] ==1 || secDiag[row+cols] == 1 || visitedCols[cols]==1)
                continue;

            primDiag[row - cols + size] = 1;
            secDiag[row + cols] = 1;
            visitedCols[cols] = 1;
            chessboard[row][cols] = 'Q';

            fillQueens(chessboard,row+1);

            chessboard[row][cols] = '.';
            primDiag[row - cols + size] = 0;
            secDiag[row + cols] = 0;
            visitedCols[cols] = 0;

        }
    }



    public static void main(String[] args) {
        NQueens nq = new NQueens();
        nq.solveNQueens(5);
    }
}
