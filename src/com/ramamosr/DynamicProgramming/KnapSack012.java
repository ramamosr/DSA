package com.ramamosr.DynamicProgramming

public class KnapSack012 {
    /*
    Problem Description

Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.

Also given an integer C which represents knapsack capacity.

Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.

NOTE: You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).



Problem Constraints

1 <= N <= 500

1 <= C, B[i] <= 106

1 <= A[i] <= 50



Input Format

First argument is an integer array A of size N denoting the values on N items.

Second argument is an integer array B of size N denoting the weights on N items.

Third argument is an integer C denoting the knapsack capacity.



Output Format

Return a single integer denoting the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.



Example Input

Input 1:

 A = [6, 10, 12]
 B = [10, 20, 30]
 C = 50
Input 2:

 A = [1, 3, 2, 4]
 B = [12, 13, 15, 19]
 C = 10


Example Output

Output 1:

 22
Output 2:

 0


Example Explanation

Explanation 1:

 Taking items with weight 20 and 30 will give us the maximum value i.e 10 + 12 = 22
Explanation 2:

 Knapsack capacity is 10 but each item has weight greater than 10 so no items can be considered in the knapsack therefore answer is 0.
     */

    /*
    Since the value of items will be less than equal to 50. So the max value can be 50 * N.

Create a dp array of size 50 * N where dp[val] will tell that minimum weight require to have value exactly equal to val.

This can be easily calculated by running two loops:

    for i -> 0 to N-1:
        for val -> mxval to A[i]
            dp[val] = min(dp[val],B[i] + dp[val-A[i]])
Now, check for the maximum value for which dp[val] is less than equal to the capacity of knapsack.
     */



    public int solve(int[] A, int[] B, int C) {
        // Problem constraint says value can be maximum 50 A[i]<=50.
        // so the max value that can be attained is 50 * A.length
        int maxVal = 50 * A.length;
        int[] dp = new int[maxVal +1 ];
        for(int i = 1;i<A.length+1;i++){
            for(int w = C; w>=0;w--){
                if (B[i - 1] <= w)
                    dp[w] = Math.max(dp[w],dp[w-B[i-1]] + A[i-1]);
            }
        }
        return dp[C];
    }


    public int solveScaler(int[] A, int[] B, int C) {
        int n = A.length;
        int mxval = 50 * n;
        int[] dp = new int[mxval + 1];
        for (int i = 0; i < mxval + 1; i++) {
            dp[i] = 1000000000;
        }
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int val = mxval; val >= A[i]; val--) {
                dp[val] = Math.min(dp[val], B[i] + dp[val - A[i]]);
            }
        }
        int ans = 0;
        for (int val = mxval; val >= 0; val--) {
            if (dp[val] <= C) {
                ans = val;
                break;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] A = {6, 10, 12};
        int[] B = {10, 20, 30};
        int C = 50;
        KnapSack012 ks = new KnapSack012();
        System.out.println(ks.solve(A,B,C));
    }
}
