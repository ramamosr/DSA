package com.ramamosr.ArrayProblems.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;

public class IntersectionOfSortedArrays {
    /*
    Intersection Of Sorted Arrays
Problem Description

Find the intersection of two sorted arrays. OR in other words, Given 2 sorted arrays, find all the elements which occur in both the arrays.

Example:

Input:
    A: [1 2 3 3 4 5 6]
    B: [3 3 5]

Output: [3 3 5]

Input:
    A: [1 2 3 3 4 5 6]
    B: [3 5]

     */

    public int[] solve(final int[] A, final int[] B) {
        if(A.length==0 || B.length ==0){
            return new int[0];
          }
        else {
            ArrayList<Integer> result = new ArrayList<>();
            Arrays.sort(A);;
            Arrays.sort(B);;
            int i = 0,j=0;
            while( i < A.length && j < B.length){
                if(A[i]==B[j]) {
                    result.add(A[i]);
                    i++;
                    j++;
                }
                else if(A[i] < B[j]){
                    i++;
                }
                else if (A[i] > B[j]){
                    j++;
                }
            }
            int[] resultarr = new int[result.size()];
            for(int k=0; k<result.size();k++){
                resultarr[k] = result.get(k);
            }
            return resultarr;
        }
    }

    public static void main(String[] args) {
        IntersectionOfSortedArrays isa = new IntersectionOfSortedArrays();
        int[]  A = new int[]{1,2,3,3,4,5,6 };
        int[] B = new int[]{3,3,5};
        isa.solve(A,B);
    }
}
