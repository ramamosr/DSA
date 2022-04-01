package com.ramamosr.DynamicProgramming;

public class LongestPalndromicSubsequence {

    /*
    Problem Description

Given a string A. Find the longest palindromic subsequence (A subsequence which does not need to be contiguous and is a palindrome).

You need to return the length of longest palindromic subsequence.



Problem Constraints

1 <= length of(A) <= 103



Input Format

First and only integer is a string A.



Output Format

Return an integer denoting the length of longest palindromic subsequence.



Example Input

Input 1:

 A = "bebeeed"
Input 2:

 A = "aedsead"


Example Output

Output 1:

 4
Output 2:

 5


Example Explanation

Explanation 1:

 The longest palindromic subsequence is "eeee", which has a length of 4.
Explanation 2:

 The longest palindromic subsequence is "aedea", which has a length of 5.
     */

    /*
    First, Create a recurrence relation then we will think of optimizing that.
Let’s say for sequence A[0..n-1] , L(0,n-1) denotes the length of longest palidromic subsequence.
It will be calculated by:
-> If last and first character of the sequence are same, then L(0,n-1) = L(1,n-1) + 2
->Else, L(0,n-1) = max(L(0,n-2),L(1,n-1))

Since we can observe overlapping Subproblems, we will optimize it using a dynamic programming solution.
     */

    public int solve(String A) {
        // Idea: find least common subsequence between string A and its reverse string
        StringBuilder sb=new StringBuilder(A);
        sb.reverse();
        String rev =  sb.toString();
        return lcs(A, rev);
    }

    // TC: O(n x m)
    // SC: O(n x 2)
    public int lcs(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[2][m + 1];

        // initialize first row and first column with 0 (considering extra row and
        // column as dummy)
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int currRow = (i) % 2;
                int prevRow = (i - 1) % 2;
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[currRow][j] = Math.max((1 + dp[prevRow][j - 1]), Math.max(dp[currRow][j - 1], dp[prevRow][j]));
                } else {
                    dp[currRow][j] = Math.max(dp[currRow][j - 1], dp[prevRow][j]);
                }
            }
        }
        return dp[n % 2][m];
    }

    public int solveScaler(String A) {
        return lps(A);
    }
    static int lps(String seq) {
        int n = seq.length();
        int i, j, cl;
        int L[][] = new int[n][n];
        for (i = 0; i < n; i++)
            L[i][i] = 1;
        for (cl = 2; cl <= n; cl++) {
            for (i = 0; i < n - cl + 1; i++) {
                j = i + cl - 1;
                if (seq.charAt(i) == seq.charAt(j) && cl == 2)
                    L[i][j] = 2;
                else if (seq.charAt(i) == seq.charAt(j))
                    L[i][j] = L[i + 1][j - 1] + 2;
                else
                    L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
            }
        }
        return L[0][n - 1];
    }

    public static void main(String[] args) {
        String A = "bebeeed";
        LongestPalndromicSubsequence lps = new LongestPalndromicSubsequence();
        System.out.println(lps.solve(A));
    }
}
