package com.ramamosr;

import java.util.ArrayList;
import java.util.Arrays;

public class MinAbsoluteDifference {
    /*
    Minimum Absolute Difference
Problem Description

Given an array of integers A, find and return the minimum value of | A [ i ] - A [ j ] | where i != j and |x| denotes the absolute value of x.



Problem Constraints
1 <= length of the array <= 100000

-109 <= A[i] <= 109



Input Format
The only argument given is the integer array A.



Output Format
Return the minimum value of | A[i] - A[j] |.



Example Input
Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [5, 17, 100, 11]


Example Output
Output 1:

 1
Output 2:

 6

     */

    public int solve(int[] A) {

        if(A.length==1) return 0;
        Arrays.sort(A);
        int diff = Integer.MAX_VALUE;

        for(int i=1; i< A.length;i++){
            diff = Math.min(diff,A[i]-A[i-1]);
        }
        return diff;

    }
    public static void main(String[] args) {
        MinAbsoluteDifference mad = new MinAbsoluteDifference();
        System.out.println(mad.solve(new int[] {1,2,3,4,5}));
    }
}
