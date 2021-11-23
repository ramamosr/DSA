package com.ramamosr.Sorting;

import java.util.Arrays;

public class InversionCountInArray {

    /*
    Problem Description
Given an array of integers A. If i < j and A[i] > A[j] then the pair (i, j) is called an inversion of A. Find the total number of inversions of A modulo (109 + 7).



Problem Constraints
1 <= length of the array <= 100000

1 <= A[i] <= 10^9



Input Format
The only argument given is the integer array A.



Output Format
Return the number of inversions of A modulo (109 + 7).



Example Input
Input 1:

A = [3, 2, 1]
Input 2:

A = [1, 2, 3]


Example Output
Output 1:

3
Output 2:

0


Example Explanation
Explanation 1:

 All pairs are inversions.
Explanation 2:

 No inversions.
     */
    public int solve(int[] A) {
        int mod = 1000000007;
        long res = mergeSort(A,0,A.length-1);
        return (int)(res%mod);
    }

    // Do the merge sort. Divide and conquer.
    // Sort the left half and right half using recursion.
    //Merge the two arrays. While merging calculate the number of elements that are
    // more than the current element. As the elements will be the sorted array,
    // elements larger than the current one can be counted as inversion.

    public long mergeArrays(int[] A, int left, int mid, int right){

        // Array copy copies upto the last element and not the last element.
        // to copy the last element increase the range by 1.
        int[] leftArr = Arrays.copyOfRange(A,left,mid+1);
        int[] rightArr = Arrays.copyOfRange(A,mid+1,right+1);

        int i=0,j=0;
        long count = 0;
        int k = left;
        while(i < leftArr.length && j < rightArr.length){
            if(leftArr[i] <= rightArr[j]){
                A[k] = leftArr[i];
                i++;
            }
            else{
                A[k] = rightArr[j];
                j++;
                count+= mid + 1 - left-i;
            }
            k++;
        }

        while(i < leftArr.length){
            A[k] = leftArr[i];
            i++;
            k++;
        }
        while(j < rightArr.length){
            A[k] = rightArr[j];
            j++;
            k++;
        }
        return count;
    }

    public long mergeSort(int[] A, int left, int right){

        long inversionCount = 0;
        if (left < right) {
            int mid = (left + right) / 2; //left + ((right - left) / 2);
            inversionCount += mergeSort(A, left, mid);
            inversionCount += mergeSort(A, mid + 1, right);
            inversionCount += mergeArrays(A, left, mid, right);
        }
        return inversionCount;
    }

    public static void main(String[] args) {
        InversionCountInArray inv = new InversionCountInArray();
        int[] A = {3,2,1};
        System.out.println(inv.solve(A));
    }
}
