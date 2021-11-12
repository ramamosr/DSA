package com.ramamosr.ArrayProblems;

import java.util.Arrays;

public class MaximumDifference {
    /*
    Maximum Difference
Given an array of integers A and an integer B. Find and return the maximum value of | s1 - s2 |

where s1 = sum of any subset of size B, s2 = sum of elements of A - sum of elemets of s1

Note |x| denotes the absolute value of x.


Input Format

The arguments given are the integer array A and integer B.
Output Format

Return the maximum value of | s1 - s2 |.
Constraints

1 <= B < length of the array <= 100000
1 <= A[i] <= 10^5
For Example

Input 1:
    A = [1, 2, 3, 4, 5]
    B = 2
Output 1:
    9

Input 2:
    A = [5, 17, 100, 11]
    B = 3
Output 2:
    123
     */

    /*
    We have to find the maximum value of |s1-s2|.
Now for to maximize any absolute value 2 conditions are there:
1st cond.-> s1 should be max as possible and s2 should be min. as possible.
2nd cond.-> s2 should be min as possible and s1 should be max as possible.

Now in this question there is a mandatory condition we have to take any subset of B size.
So for 1st condition if we try to take maximize the value of s1 . so take only 'B' large values
and for 2nd condition try to take 'B' small values.
Time Complexity; Nlog N

NOTE: to find small values and large values . Sort the given array
     */

    public int solve(int[] A, int B) {
        int s1Min =0,s1Max=0,sum=0;
        if(A.length == B)
            return Math.abs(B);
        else{
            Arrays.sort(A);
            for(int i=0; i<A.length;i++){
                sum+=A[i];
                if(i<B)
                    s1Min+= A[i];
                if(i>=A.length-B)
                    s1Max+= A[i];
            }

        }
        return Math.max(Math.abs(s1Min-(sum-s1Min)),Math.abs(s1Max-(sum-s1Max)));

    }
    public static void main(String[] args) {
        MaximumDifference md = new MaximumDifference();
        System.out.println(md.solve(new int[]{69, 72, 53, 45, 19, 31, 50, 17, 2, 28, 48, 43, 59, 23, 78, 32, 67, 51, 36, 60, 76, 25, 59, 6, 90, 78, 15, 26, 79, 6, 2},15));
        System.out.println(md.solve(new int[]{5,17,100,11},3));
    }
}
