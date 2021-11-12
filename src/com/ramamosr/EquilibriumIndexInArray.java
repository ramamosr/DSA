package com.ramamosr;

public class EquilibriumIndexInArray {
    /*
    Equilibrium index of an array
Problem Description

You are given an array A of integers of size N.

Your task is to find the equilibrium index of the given array

Equilibrium index of an array is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes.

NOTE:

Array indexing starts from 0.
If there is no equilibrium index then return -1.
If there are more than one equilibrium indexes then return the minimum index.



Problem Constraints
1<=N<=1e5
-1e5<=A[i]<=1e5


Input Format
First arugment contains an array A .


Output Format
Return the equilibrium index of the given array. If no such index is found then return -1.


Example Input
Input 1:
A=[-7, 1, 5, 2, -4, 3, 0]
Input 2:

A=[1,2,3]


Example Output
Output 1:
3
Output 2:

-1


Example Explanation
Explanation 1:
3 is an equilibrium index, because:
A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
Explanation 1:

There is no such index.

     */
    public int solve(int[] A) {
        int totalSum = 0,rightSum = 0, leftSum = 0;
        int eqIndex = -1;
        for(int i=0;i <A.length;i++){
            totalSum+=A[i];
        }
        if(A[0] == totalSum)
            return 0;
        // right sum is the sum of all elements in the array.
        //as we move through the array compare the leftsum with rightsum-value of element at current index

        for(int j=1;j<A.length;j++){
            leftSum += A[j-1];
            rightSum = totalSum-(leftSum+A[j]);
            if(leftSum == rightSum)
                return j;
        }
        return eqIndex;
    }

    public static void main(String[] args) {
        EquilibriumIndexInArray eq = new EquilibriumIndexInArray();
        System.out.println(eq.solve( new int[] {1,2,3,7,1,2,3}));
    }
}
