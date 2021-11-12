package com.ramamosr.hw.IntroToSorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ElementRemovalWithLowestCost {
    /*
    Elements Removal
Problem Description

Given an integer array A of size N. In one operation, you can remove any element from the array, and the cost of this operation is the sum of all elements in the array present before this operation.

Find the minimum cost to remove all elements from the array.



Problem Constraints
0 <= N <= 1000
1 <= A[i] <= 103



Input Format
First and only argument is an integer array A.



Output Format
Return an integer denoting the total cost of removing all elements from the array.



Example Input
Input 1:

 A = [2, 1]
Input 2:

 A = [5]


Example Output
Output 1:

 4
Output 2:

 5


Example Explanation
Explanation 1:

 Given array A = [2, 1]
 Remove 2 from the array => [1]. Cost of this operation is (2 + 1) = 3.
 Remove 1 from the array => []. Cost of this operation is (1) = 1.
 So, total cost is = 3 + 1 = 4.

Explanation 2:

 There is only one element in the array. So, cost of removing is 5.
     */

    /*
    Let’s try to find the count of how many times a element will contribute to the answer.

Remove any element from the array. Cost of this operation is equal to the sum of array elements irrespective of which element gets removed.

If we remove another element from the array, cost of this operation will be ( cost of previous operation - element which gets removed in previous operation.)

So, we can easily observe that it is wise to remove highest element first to make the total cost minimum and elements also follow pattern.

Element removed first will be added 1 time to the answer.
Element removed second will be added 2 times to the answer.
Element removed third will be added 3 times to the answer.
     */
    public int solve(int[] A) {
        if(A.length==1) return A[0];

        Arrays.sort(A);
        int result = 0;
        int count = 1;
        for(int j = A.length-1; j>=0;j--)
        {
            result += count *A[j];
            count++;
        }
        return result;
    }
    public static void main(String[] args) {
        ElementRemovalWithLowestCost erwl = new ElementRemovalWithLowestCost();
        System.out.println(erwl.solve(new int[]{1,2,3,4}));
    }
}
