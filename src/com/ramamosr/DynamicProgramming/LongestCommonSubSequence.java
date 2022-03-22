package com.ramamosr.DynamicProgramming

public class LongestCommonSubSequence {

    /*
    Problem Description

Given two strings A and B. Find the longest common subsequence ( A sequence which does not need to be contiguous), which is common in both the strings.

You need to return the length of such longest common subsequence.



Problem Constraints

1 <= Length of A, B <= 1005



Input Format

First argument is a string A.
Second argument is a string B.



Output Format

Return an integer denoting the length of the longest common subsequence.



Example Input

Input 1:

 A = "abbcdgf"
 B = "bbadcgf"
Input 2:

 A = "aaaaaa"
 B = "ababab"


Example Output

Output 1:

 5
Output 2:

 3


Example Explanation

Explanation 1:

 The longest common subsequence is "bbcgf", which has a length of 5.
Explanation 2:

 The longest common subsequence is "aaa", which has a length of 3.
     */
/*
uppose LCS[i][j] represents the longest common subsequence of A[1..i] and B[1..j]

A[1..i] represents first i characters of string A
A[1..j] represents first j characters of string B

For every i, j we have two conditions A[i] == B[j] or not. Divide the problem based on this condition.

Recursion relation to divide the problem into smaller sub problems can be written as:-

LCS(i, j) = maximum (LCS(i-1, j-1] + 1,       //if(A[i] = B[j])
                     LCS(A[i-1], B[j],
                     LCS(A[i], B[j-1] )
LCS[ len(A) ][ len(B) ] will be our answer.

Think about the time complexity of this solution.
 */
    public int solve(String A, String B) {
        int n=A.length();
        int m=B.length();
        int[][] dp=new int[n][m];

        return lcs(A,B,n-1,m-1,dp);
    }
    private int lcs(String A,String B,int i,int j,int[][] dp){
        if(i==-1 || j==-1){
            return 0;
        }
        if(dp[i][j]!=0){
            return dp[i][j];
        }
        int max=0;
        if(A.charAt(i)==B.charAt(j)){
            max=1+lcs(A,B,i-1,j-1,dp);
        }
        else{
            max=Math.max(lcs(A,B,i-1,j,dp),lcs(A,B,i,j-1,dp));
        }
        dp[i][j]=max;
        return dp[i][j];
    }

    public int solveScaler(String A, String B) {
        int m = A.length(), n = B.length();
        int[][] L = new int[m + 1][n + 1];
        int i, j;
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                //Base condition
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (A.charAt(i - 1) == B.charAt(j - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[m][n];
    }

    public static void main(String[] args) {
        String A = "abbcdgf";
        String B = "bbadcgf";
        LongestCommonSubSequence lcs = new LongestCommonSubSequence();
        System.out.println(lcs.solve(A,B));
    }
}
