package com.ramamosr.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class MinSumPathInMatrix {
    /*
    Problem Description

Given a M x N grid A of integers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Return the minimum sum of the path.

NOTE: You can only move either down or right at any point in time.



Problem Constraints

1 <= M, N <= 2000

-1000 <= A[i][j] <= 1000



Input Format

First and only argument is a 2-D grid A.



Output Format

Return an integer denoting the minimum sum of the path.



Example Input

Input 1:

 A = [
       [1, 3, 2]
       [4, 3, 1]
       [5, 6, 1]
     ]
Input 2:

 A = [
       [1, -3, 2]
       [2, 5, 10]
       [5, -5, 1]
     ]


Example Output

Output 1:

 8
Output 2:

 -1


Example Explanation

Explanation 1:

 The path will be: 1 -> 3 -> 2 -> 1 -> 1.
Input 2:

 The path will be: 1 -> -3 -> 5 -> -5 -> 1.
     */
    //top down approach
    public int minPathSum(int[][] A) {
        for(int i=A.length-1;i>=0;i--){
            for(int j=A[0].length-1;j>=0;j--){
                if(i == A.length-1 && j == A[0].length-1){
                    continue;
                }
                int rightVal = j == A[0].length-1 ? Integer.MAX_VALUE : A[i][j+1]+A[i][j];
                int bottomVal = i == A.length-1 ? Integer.MAX_VALUE : A[i+1][j]+A[i][j];
                A[i][j] = Math.min(rightVal,bottomVal);
            }
        }
        return A[0][0];



    }
    
    
    //bottom up approach
    private int mem[][];
    private ArrayList <ArrayList< Integer >> A;
    public int minPathSum(ArrayList < ArrayList < Integer >> A) {
        int m, n;
        if (A == null || A.size() == 0 || A.get(0).size() == 0)
            return 0;
        m = A.size();
        n = A.get(0).size();
        mem = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(mem[i], Integer.MAX_VALUE);
        mem[0][0] = A.get(0).get(0);
        this.A = A;
        rec(m - 1, n - 1);
        return mem[m - 1][n - 1];
    }

    public int rec(int i, int j) {
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;
        if (mem[i][j] != Integer.MAX_VALUE)
            return mem[i][j];
        int res = rec(i - 1, j);
        res = Math.min(rec(i, j - 1), res);
        mem[i][j] = res + A.get(i).get(j);
        return mem[i][j];
    }

    public static void main(String[] args) {
        MinSumPathInMatrix mspm = new MinSumPathInMatrix();
        int[][] A = { {1, 3, 2},{4, 3, 1},{5, 6, 1}};
        System.out.println(mspm.minPathSum(A));
    }
}
