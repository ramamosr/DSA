package com.ramamosr;

public class SumOfOddEvenAfterRemovingAnElement {
    /*
    Count ways to make sum of odd and even indexed elements equal by removing an array element
Problem Description

Given an array, arr[] of size N, the task is to find the count of array indices such that removing an element from these indices makes the sum of even-indexed and odd-indexed array elements equal.



Problem Constraints
1<=n<=1e5
-1e5<=A[i]<=1e5


Input Format
First argument contains an array A of integers of size N


Output Format
Return the count of array indices such that removing an element from these indices makes the sum of even-indexed and odd-indexed array elements equal.



Example Input
Input 1:
A=[2, 1, 6, 4]
Input 2:

A=[1, 1, 1]


Example Output
Output 1:
1
Output 2:

3


Example Explanation
Explanation 1:
Removing arr[1] from the array modifies arr[] to { 2, 6, 4 } such that, arr[0] + arr[2] = arr[1].
Therefore, the required output is 1.
Explanation 2:

 Removing arr[0] from the given array modifies arr[] to { 1, 1 } such that arr[0] = arr[1]
Removing arr[1] from the given array modifies arr[] to { 1, 1 } such that arr[0] = arr[1]
Removing arr[2] from the given array modifies arr[] to { 1, 1 } such that arr[0] = arr[1]
Therefore, the required output is 3.
     */
    public int solve(int[] A) {
        if(A.length==1) return 1;
        int result = 0;
        int[] psEven = new int[A.length];
        int[] psOdd = new int[A.length];

        for(int i=0;i < A.length;i++){
            // INsert into psEven if i%2 is zero.
            if(i%2==0){
                if(i==0){
                    psEven[0] = A[i];
                }
                else{
                    psEven[i] = psEven[i-1] + A[i];
                    psOdd[i] = psOdd[i-1];
                }
            }
            // INsert into psEven if i%2 is not equal to zero.
            else{
                psOdd[i] = psOdd[i-1] + A[i];
                psEven[i] = psEven[i-1];
            }
        }
        int sumOfEven = 0, sumOfOdd = 0;

        for(int k =0; k <A.length;k++){
            if(k==0){
                // if k is zero, then there is no left side even or odd. just flip and compare the right side.
                sumOfEven = psOdd[A.length-1] - psOdd[k];
                sumOfOdd =  psEven[A.length-1] - psEven[k];
            }
            else if (k<A.length-1){
                sumOfEven = psEven[k-1] + psOdd[A.length-1]-psOdd[k];
                sumOfOdd = psOdd[k-1] + psEven[A.length-1]-psEven[k];
            }
            else if(k==A.length-1){
                sumOfEven = psEven[k-1];
                sumOfOdd = psOdd[k-1];
            }
            if(sumOfEven==sumOfOdd){
                result =  result +1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SumOfOddEvenAfterRemovingAnElement soear = new SumOfOddEvenAfterRemovingAnElement();
        soear.solve(new int[] {1,1,1});
    }
}
