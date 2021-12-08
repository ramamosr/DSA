package com.ramamosr.BinarySearch;

public class SpecialInteger {
    /*
    Problem Description

Given an array of integers A and an integer B, find and return the maximum value K such that there is no subarray in A of size K with sum of elements greater than B.



Problem Constraints

1 <= |A| <= 100000
1 <= A[i] <= 10^9

1 <= B <= 10^9



Input Format

The first argument given is the integer array A.

The second argument given is integer B.



Output Format

Return the maximum value of K (sub array length).



Example Input

Input 1:

A = [1, 2, 3, 4, 5]
B = 10
Input 2:

A = [5, 17, 100, 11]
B = 130


Example Output

Output 1:

 2
Output 2:

 3


Example Explanation

Explanation 1:

Constraints are satisfied for maximal value of 2.
Explanation 2:

Constraints are satisfied for maximal value of 3.
     */

    public int solve(int[] A, int B) {
        long[] prefixSum = new long[A.length+1];
        int result = -1;
        prefixSum[0] = 0;

        for(int i = 0; i<A.length;i++){
            prefixSum[i+1] = prefixSum[i] + A[i];
        }

        int start = 0, end = A.length;
        while(start<=end){
            int mid = start + ((end-start) / 2);
            if(checkSum(prefixSum,B,mid,start)){
                start = mid +1;
                result = mid;
            }
            else
                end = mid - 1;
        }
        return result;
    }

    public boolean checkSum(long[] ps, int sum, int mid,int start){
        long temp = 0;
        for(int i= mid;i<ps.length;i++){
            temp = ps[i] - ps[i-mid];
            if(temp > sum)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SpecialInteger si = new SpecialInteger();
        System.out.println(si.solve(new int[]{5, 17, 100, 11},130));
    }
}
