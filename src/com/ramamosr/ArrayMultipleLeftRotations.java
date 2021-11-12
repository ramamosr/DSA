package com.ramamosr;

import java.util.Arrays;

public class ArrayMultipleLeftRotations {
    /*
    Multiple left rotations of the array
Problem Description

Given an array of integers A and multiple values in B which represents number of times array A needs to be left rotated.

Find the rotated array for each value and return the result in the from of a matrix where i'th row represents the rotated array for the i'th value in B.



Problem Constraints
1 <= length of both arrays <= 2000 -10^9 <= A[i] <= 10^9 0 <= B[i] <= 2000


Input Format
The first argument given is the integer array A.
The second argument given is the integer array B.


Output Format
Return the resultant matrix.


Example Input
Input 1:

    A = [1, 2, 3, 4, 5]
    B = [2, 3]

Input 2:


    A = [5, 17, 100, 11]
    B = [1]




Example Output
Output 1:

    [ [3, 4, 5, 1, 2]
     [4, 5, 1, 2, 3] ]


Output 2:


    [ [17, 100, 11, 5] ]



Example Explanation
for input 1 -> B[0] = 2 which requires 2 times left rotations

1: [2, 3, 4, 5, 1]

2: [3, 4, 5, 1, 2]

B[1] = 3 which requires 3 times left rotation

1: [2, 3, 4, 5, 1]

2: [3, 4, 5, 1, 2]

2: [4, 5, 1, 2, 4]


     */

    public int[][] solve(int[] A, int[] B) {

        if(A.length==0 || B.length==0)
            return new int[0][0];
        int rotateLen = B.length;
        int[][] result = new int[B.length][A.length];
        for(int i =0;i <rotateLen;i++) {
            int rotateSteps = B[i]%A.length;
            int[] ans = new int[A.length];
            for (int j = 0; j < A.length; j++) {
                ans[j] = A[(j + rotateSteps + A.length)%A.length];
            }
            result[i] = ans;
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayMultipleLeftRotations amlr = new ArrayMultipleLeftRotations();
        int[] A = new int[]{6, 31, 33, 13, 82, 66, 9, 12, 69, 21, 17, 2, 50, 69, 90, 71, 31, 1, 13, 70, 94, 46, 89, 13, 55, 54, 67, 97, 28, 27, 62, 34, 41, 18, 15, 35, 13, 84, 93, 27, 89, 23, 6, 56, 94, 40, 54, 95, 47 };
        int[] B = new int[]{88, 85, 98, 36, 66, 40, 30, 26, 51, 77, 62, 60, 92, 64, 53, 86, 24, 53, 85, 49, 57, 29, 32, 60, 75, 82, 17, 23, 67, 51, 23, 11, 70, 59 };
        amlr.solve(A,B);
    }
}
