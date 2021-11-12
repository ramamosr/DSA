package com.ramamosr.PS4;

public class MinimizeAbsDifference {
    /*
    Minimize the absolute difference
Given three sorted arrays A, B and Cof not necessarily same sizes.

Calculate the minimum absolute difference between the maximum and minimum number from the triplet a, b, c such that a, b, c belongs arrays A, B, C respectively. i.e. minimize | max(a,b,c) - min(a,b,c) |.

Example :

Input:

A : [ 1, 4, 5, 8, 10 ]
B : [ 6, 9, 15 ]
C : [ 2, 3, 6, 6 ]
Output:

1
Explanation: We get the minimum difference for a=5, b=6, c=6 as | max(a,b,c) - min(a,b,c) | = |6-5| = 1.
     */
    public int solve(int[] A, int[] B, int[] C) {
        int diff = Integer.MAX_VALUE;
        int posA = 0,posB = 0,posC = 0;
        int Alen = A.length, Blen = B.length, Clen = C.length;
        int max = 0, min = 0;
        while(posA < Alen && posB < Blen && posC < Clen){

            max = Math.max(A[posA],Math.max(B[posB],C[posC]));
            min = Math.min(A[posA],Math.min(B[posB],C[posC]));
            if(max-min==0){
                return diff = 0;
            }
            else{
                diff = Math.min(diff,(max-min));
            }
            if(min == A[posA])
                posA++;
            else if(min == B[posB])
                posB++;
            else if(min == C[posC])
                posC++;
        }

        return diff;
    }

    public static void main(String[] args) {
        MinimizeAbsDifference mad = new MinimizeAbsDifference();
        int[] A = new int[]{1, 4, 5, 8, 10 };
        int[] B = new int[]{6, 9, 15};
        int[] C = new int[]{2, 3, 6, 6};
        mad.solve(A,B,C);
    }
}
