package com.ramamosr.ArrayProblems.TwoPointers;

import java.util.Arrays;

public class MergeTwoSortedArrays {
    /*
    Merge Two Sorted Arrays
Problem Description

Given two sorted integer arrays A and B, merge B and A as one sorted array and return it as an output.



Problem Constraints
-1010 <= A[i],B[i] <= 1010



Input Format
First Argument is a 1-D array representing A.

Second Argument is also a 1-D array representing B.



Output Format
Return a 1-D vector which you got after merging A and B.



Example Input
Input 1:

A = [4, 7, 9 ]
B = [2 ,11, 19 ]
Input 2:

A = [1]
B = [2]


Example Output
Output 1:

[2, 4, 7, 9, 11, 19]
Output 2:

[1, 2]


Example Explanation
Explanation 1:

Merging A and B produces the output as described above.
Explanation 2:

 Merging A and B produces the output as described above.
     */

    public int[] solve(final int[] A, final int[] B) {
        if(A.length==0 && B.length>0)
            return A;
        else if(B.length==0 && A.length>0)
            return B;
        else if(A.length==0 && B.length==0)
            return new int[0];
        else {
            int[] result = new int[(A.length+B.length)];
            int i = 0,j=0,index = 0;
            while( i < A.length && j < B.length){
                if(A[i] < B[j]){
                    result[index] = A[i];
                    i++;
                }
                else{
                    result[index] = B[j];
                    j++;
                }
                index++;
            }
            while (i < A.length){
                result[index++] = A[i++];
            }
            while (j < B.length){
                result[index++] = B[j++];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        MergeTwoSortedArrays mtsa = new MergeTwoSortedArrays();
        int[]  A = new int[]{4, 7, 9 };
        int[] B = new int[]{2 ,11, 19};
        mtsa.solve(A,B);
    }
}
