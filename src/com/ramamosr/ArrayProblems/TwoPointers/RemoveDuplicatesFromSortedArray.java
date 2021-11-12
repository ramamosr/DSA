package com.ramamosr.ArrayProblems.TwoPointers;

public class RemoveDuplicatesFromSortedArray {
    /*
    Remove Duplicates from Sorted Array
Problem Description

Given a sorted array A consisting of duplicate elements.

Your task is to remove all the duplicates and return a sorted array of distinct elements consisting of all distinct elements present in A.

NOTE: The input format has been changed from previous versions.



Problem Constraints
1 <= |A| <= 106

1 <= A[i] <= 2*109



Input Format
First and only argument containing the integer array A.



Output Format
Return an array/vector from the function as per the question.



Example Input
Input 1:

A = [1, 1, 2]
Input 2:

A = [1, 2, 2, 3, 3]


Example Output
Output 1:

[1, 2]
Output 2:

[1, 2, 3]


Example Explanation
Explanation 1:

Updated Array: [1, 2] after removing the 2nd element.
Explanation 2:

Updated Array: [1, 2, 3] after removing the 3rd and 5th element.
     */
    public int[] solve(int[] A) {
        if(A.length<=1)
            return A;
        int index = 0;
        for(int i=0;i <A.length-1;i++){
            if(A[i]!=A[i+1]){
                A[index] = A[i];
                index++;
            }
        }
        A[index++] = A[A.length-1];
        int[] result = new int[index];
        for(int i=0; i<index;i++){
            result[i] = A[i];
        }
        return result;
    }
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray rdsa = new RemoveDuplicatesFromSortedArray();
        rdsa.solve(new int[]{1, 2, 2, 3, 3});
    }
}
