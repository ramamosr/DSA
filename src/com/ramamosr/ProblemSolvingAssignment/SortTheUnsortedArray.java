package com.ramamosr.ProblemSolvingAssignment;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.lang.System.*;

public class SortTheUnsortedArray {
    /*
    Sort the Unsorted Array
Problem Description

You are given an integer array A having N integers.

You have to find the minimum length subarray A[l..r] such that sorting this subarray makes the whole array sorted.



Problem Constraints
1 <= N <= 105

-109 <= A[i] <= 109



Input Format
First and only argument is an integer array A.



Output Format
Return an integer denoting the minimum length.



Example Input
Input 1:

 A = [0, 1, 15, 25, 6, 7, 30, 40, 50]
Input 2:

 A = [10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60]


Example Output
Output 1:

 4
Output 2:

 6


Example Explanation
Explanation 1:

 The smallest subarray to be sorted to make the whole array sorted =  A[3..6] i.e, subarray lying between positions 3 and 6.
Explanation 2:

 The smallest subarray to be sorted to make the whole array sorted =  A[4..9] i.e, subarray lying between positions 4 and 9.
     */

    public int solve(int[] A) {
        int[] B = new int[A.length];
        System.arraycopy(A,0,B,0,A.length);
        Arrays.sort(B);
        boolean orginalArraySorted = true;
        int startIndex = Integer.MAX_VALUE, endIndex = Integer.MIN_VALUE;
        for(int i = 0; i<A.length; i++){
            if(A[i]!=B[i]) {
                    startIndex = Math.min(startIndex, i);
                    orginalArraySorted = false;
            }
            if(A[A.length-1-i] != B[B.length-1-i]) {
                endIndex = Math.max(endIndex, A.length - 1 - i);
                orginalArraySorted = false;
            }

        }
        if(orginalArraySorted) return 0;
        return endIndex - startIndex + 1;
    }
    public int solveScaler(int[] A) {
        int N = A.length;

        int l = 0;
        int r = N - 1;

        for (l = 0; l < N - 1; l++)
        {
            if (A[l] > A[l + 1])
                break;
        }

        if (l == N - 1){
            return 0;
        }


        for(r = N - 1; r > 0; r--)
        {
            if(A[r] < A[r - 1])
                break;
        }


        int mx = A[l];
        int mn = A[l];

        for(int i = l + 1; i <= r; i++)
        {
            if(A[i] > mx)
                mx = A[i];
            if(A[i] < mn)
                mn = A[i];
        }


        for(int i = 0; i < l; i++)
        {
            if(A[i] > mn)
            {
                l = i;
                break;
            }
        }

        for(int i = N - 1; i >= r + 1; i--)
        {
            if(A[i] < mx)
            {
                r = i;
                break;
            }
        }

        return r - l + 1;
    }
    public static void main(String[] args) {
        SortTheUnsortedArray stua = new SortTheUnsortedArray();
        System.out.println(stua.solve(new int[]{-146316343, 179840175, 760528516}));
    }
}
