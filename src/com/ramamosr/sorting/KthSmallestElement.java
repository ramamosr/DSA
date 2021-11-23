package com.ramamosr.Sorting;

import java.util.Arrays;

public class KthSmallestElement {

    /*
    Problem Description

Find the Bth smallest element in given array A .

NOTE: Users should try to solve it in <= B swaps.



Problem Constraints

1 <= |A| <= 100000

1 <= B <= min(|A|, 500)

1 <= A[i] <= 109



Input Format

First argument is vector A.

Second argument is integer B.



Output Format

Return the Bth smallest element in given array.



Example Input

Input 1:

A = [2, 1, 4, 3, 2]
B = 3
Input 2:

A = [1, 2]
B = 2


Example Output

Output 1:

 2
Output 2:

 2


Example Explanation

Explanation 1:

 3rd element after sorting is 2.
Explanation 2:

 2nd element after sorting is 2.
     */

    public int kthsmallest(final int[] A, int B) {
        int[] arr = Arrays.copyOf(A,A.length);
        if(A.length == 1 && B ==1)
            return A[0];
        int result =  quickSort(arr,0,arr.length-1,B);
        return result;
    }

    public int quickSort(int[] arr, int start, int end, int swaps){
            if (swaps > 0 && swaps <= end - start + 1) {
                int pivot = partition(arr, start, end);
                if (pivot - start == swaps - 1)
                    return arr[pivot];
                //if pivot is greater than the swaps, sort only the left array
                if (pivot - start > swaps - 1)
                    return quickSort(arr, start, pivot - 1, swaps);
                    // if pivot is lesser than the swaps, sort only the right array.
                else
                    return quickSort(arr, pivot + 1, end, swaps - 1 - (pivot - start));
            } else
                return -1;
        }


    public int partition(int[] arr, int start, int end){

        // Pick the random pivot element.
        int elements = end - start;
        double rand = Math.random();
        int pivot = (int) ((rand) * (elements));
        swap(arr,start+pivot,end);
        // loop through the array. move all the smaller elements to the left of the pivot
        // and greater elements to the right of the pivot
        int pivotElement = arr[end];
        int i = start - 1;
        for(int j= start;j<end;j++){
            if(arr[j] < pivotElement) {
                i++;
                swap(arr,i,j);
            }
        }
        // Since the pivot element is placed at the end initially,
        // move the pivot element to the correct position
        swap(arr,i+1,end);
        return i+1;
    }

    public void swap(int[] arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
      public static void main(String[] args) {
        KthSmallestElement qs = new KthSmallestElement();
        int[] A = new int[]{5,8,2,1,6};
        qs.kthsmallest(A,3);
    }
}
