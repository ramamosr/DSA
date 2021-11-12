package com.ramamosr.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class OptimalPartitioning {
    /*
    Optimal Partitioning
Problem Description

You are given an array A having N integers.

You have to divide / split the array into 2 subsequence partitions such that:

Both the partitions are non-empty.
Each integer A[i] in the array belongs to exactly one of the two partitions.
Absolute difference between the maximum of first partition and the minimum of second partition is minimum possible.
If B and C represent the two partitions, then size(B) >= 1, size(C) >= 1 and |max(B) - min(C)| is minimum possible. You have to find such a spliting and tell the minimum value of |max(B) - max(C)|.



Problem Constraints
2 <= N <= 105

-109 <= A[i] <= 109



Input Format
First and only argument is an integer array A.



Output Format
Return a single integer denoting the absolute difference.



Example Input
Input 1:

 A = [3, 1, 2, 6, 4]
Input 2:

 A = [2, 1, 3, 2, 4, 3]


Example Output
Output 1:

 1
Output 2:

 0


Example Explanation
Explanation 1:

 B = [1, 2, 4]
 C = [3, 6]
 max(B) = 4, min(C) = 3
 abs(max(B) - min(C)) = abs(4 - 3) = abs(1) = 1
Explanation 2:

 B = [2, 1]
 C = [3, 2, 4, 3]
 max(B) = 2, min(C) = 2
 abs(max(B) - min(C)) = abs(2 - 2) = abs(0) = 0
     */

    public int solve(int[] A) {
        if(A.length==2)
            return Math.abs(A[1]-A[0]);
/*        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<A.length;i++){

            max = Math.max(max,A[i]);
            min = Math.min(min,A[i]);
        }
        int currMax = max;
        int currMin = min;*/
        Arrays.sort(A);
        int currMin = Integer.MAX_VALUE;
        for(int i = 1; i<A.length;i++){
            currMin = Math.min(currMin,A[i]-A[i-1]);
            if(currMin==0)
                return currMin;
        }
        return currMin;
    }

    public static void main(String[] args) {
        OptimalPartitioning op = new OptimalPartitioning();
        System.out.println(op.solve(new int[]{2, 1, 3, 2, 4, 3}));
    }
}
