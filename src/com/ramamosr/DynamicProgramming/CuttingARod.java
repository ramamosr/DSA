package com.ramamosr.DynamicProgramming;
import java.util.Arrays;

public class CuttingARod {

    /*
    Problem Description

Given a rod of length N units and an array A of size N denotes prices that contains prices of all pieces of size 1 to N.

Find and return the maximum value that can be obtained by cutting up the rod and selling the pieces.



Problem Constraints

1 <= N <= 1000

0 <= A[i] <= 106



Input Format

First and only argument is an integer array A of size N.



Output Format

Return an integer denoting the maximum value that can be obtained by cutting up the rod and selling the pieces.



Example Input

Input 1:

 A = [3, 4, 1, 6, 2]
Input 2:

 A = [1, 5, 2, 5, 6]


Example Output

Output 1:

 15
Output 2:

 11


Example Explanation

Explanation 1:

 Cut the rod of length 5 into 5 rods of length (1, 1, 1, 1, 1) and sell them for (3 + 3 + 3 + 3 + 3) = 15.
Explanation 2:

 Cut the rod of length 5 into 3 rods of length (2, 2, 1) and sell them for (5 + 5 + 1) = 11.

     */
    /*
    For the naive solution, We can get the best price by making a cut at different positions and comparing the values obtained after a cut.
We can recursively call the same function for a piece obtained after a cut.

Let’s optimize it.

Consider val[i] denotes the maximum price we can get by selling the rod (can sell by cutting any how) of length i.
->for all j from 0 to i-1, val[i] = max(val[i],A[j] + val[i-j-1]).

val[n] will be the output.
     */

    public int solve(int[] A) {
        if(A.length==0) return 0;
        int len = A.length;
        int[] length = new int[len];
        for(int i=0;i<len;i++){
            length[i] = i+1;
        }
        return cutRod(len,A,length);

    }

    public int cutRod(int maxLength,int[] price, int[] length){
        int[] dp = new int[maxLength + 1];
        Arrays.fill(dp, 0);
        for (int i = 0; i <= maxLength; i++)
            for (int j = 0; j < price.length; j++)
                if (length[j] <= i)
                    dp[i] = Math.max(dp[i], dp[i - length[j]] + price[j]);
        return dp[maxLength];
    }

    public static void main(String[] args) {
        //int[] A = {3, 4, 1, 6, 2};
        int[] A = {1, 5, 2, 5, 6};
        CuttingARod car = new CuttingARod();
        System.out.println(car.solve(A));
    }
}
