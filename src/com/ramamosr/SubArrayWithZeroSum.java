package com.ramamosr;

import java.util.HashMap;

public class SubArrayWithZeroSum {

    /*
    Sub-array with 0 sum
Problem Description

Given an array of integers A, find and return whether the given array contains a non-empty subarray with a sum equal to 0.

If the given array contains a sub-array with sum zero return 1 else return 0.



Problem Constraints
1 <= |A| <= 100000

-10^9 <= A[i] <= 10^9



Input Format
The only argument given is the integer array A.



Output Format
Return whether the given array contains a subarray with a sum equal to 0.



Example Input
Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [-1, 1]


Example Output
Output 1:

 0
Output 2:

 1


Example Explanation
Explanation 1:

 No subarray has sum 0.
Explanation 2:

 The array has sum 0.
     */
    public int solve(int[] A) {
        int result = 0;
        long[] prefixSum = new long[A.length];
        HashMap<Long,Integer> hm = new HashMap<Long,Integer>();
        long sum = 0;
        hm.put(sum,1);
        for(int j= 0; j < A.length; j++) {
            sum = sum + A[j];
            if (hm.containsKey(sum)) {
                result = 1;
                break;
            }
            hm.put(sum, 1);
        }
        return result;
    }
    public static void main(String[] args) {
        SubArrayWithZeroSum sa = new SubArrayWithZeroSum();
        System.out.println(sa.solve(new int[]{-1,1}));
    }
}
