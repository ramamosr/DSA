package com.ramamosr.DynamicProgramming;

public class LongestIncreasingSubSequence {

    /*
    Problem Description

Find the longest increasing subsequence of a given array of integers, A.

In other words, find a subsequence of array in which the subsequence's elements are in strictly increasing order, and in which the subsequence is as long as possible.

In this case, return the length of the longest increasing subsequence.



Problem Constraints

0 <= length(A) <= 2500
1 <= A[i] <= 2500



Input Format

The first and the only argument is an integer array A.



Output Format

Return an integer representing the length of the longest increasing subsequence.



Example Input

Input 1:

 A = [1, 2, 1, 5]
Input 2:

 A = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]


Example Output

Output 1:

 3
Output 2:

 6


Example Explanation

Explanation 1:

 The longest increasing subsequence: [1, 2, 5]
Explanation 2:

 The possible longest increasing subsequences: [0, 2, 6, 9, 13, 15] or [0, 4, 6, 9, 11, 15] or [0, 4, 6, 9, 13, 15]
     */

    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int lis(final int[] A) {
        int len = A.length;
        int[] dp = new int[len];
        int currMax = 1;
        dp[0] =1;
        for(int i=1;i<len;i++){
            dp[i] = 1;
            for(int j =0;j<i;j++){
                if(A[j] < A[i])
                    dp[i] = Math.max(dp[i],dp[j]+1);
            }
            currMax = Math.max(currMax,dp[i]);
        }
        return currMax;
    }

    public static void main(String[] args) {
        LongestIncreasingSubSequence lis = new LongestIncreasingSubSequence();
        System.out.println(lis.lis(new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
    }
}
