package com.ramamosr;

public class StepwiseSelectionSort {
    /*
    Stepwise Selection Sort!
Problem Description

Given an integer array A of size N.

You need to sort the elements in increasing order using SelectionSort. Return a array containing the min value's index position before every iteration.

NOTE:

Consider 0 based indexing while looking for min value in each step of selection sort.
There will be total N - 1 iterations in selection sort so the output array will contain N - 1 integers.


Problem Constraints
2 <= N <= 104

1 <= A[i] <= 106

All elements are distinct in array A.



Input Format
First and only argument is an integer array A.



Output Format
Return an integer array containing N - 1 integers denoting min value's index position before every iteration.



Example Input
Input 1:

 A = [6, 4, 3, 7, 2, 8]


Example Output
Output 1:

 [4, 2, 2, 4, 4]


Example Explanation
Explanation 1:

 Explanation : 6 4 3 7 2 8 : Index of 1st min - 4
 After 1st Iteration : 2 4 3 7 6 8 : Index of 2nd min - 2
 After 2nd Iteration : 2 3 4 7 6 8 : Index of 3rd min - 2
 After 3rd Iteration : 2 3 4 7 6 8 : Index of 4th min - 4
 After 4th iteration : 2 3 4 6 7 8 : Index of 5th min - 4
 After 5th iteration. : 2 3 4 6 7 8.
     */
    public int[] solve(int[] A) {
        int[] result = new int[A.length-1];
        int minIndex,count = 0;
        for(int i =0; i<A.length-1;i++){
            minIndex = i;
            for(int j=i+1; j<A.length;j++){
                if(A[j] < A[minIndex]) {
                    minIndex = j;
                }
            }
            result[count] = minIndex;
            count++;
            int temp = A[minIndex];
            A[minIndex] = A[i];
            A[i] = temp;
        }
        return result;
    }
    public static void main(String[] args) {
        StepwiseSelectionSort sss = new StepwiseSelectionSort();
        sss.solve(new int[]{6, 4, 3, 7, 2, 8});
    }
}
