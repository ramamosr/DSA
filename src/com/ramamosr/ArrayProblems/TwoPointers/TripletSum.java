package com.ramamosr.ArrayProblems.TwoPointers;

import java.util.Arrays;

public class TripletSum {

    /*
    Problem Description

Given an array A of N integers, find three integers in A such that the sum is closest to a given number B. Return the sum of those three integers.

Assume that there will only be one solution.
Problem Constraints
-108 <= B <= 108
1 <= N <= 104
-108 <= A[i] <= 108
Input Format
First argument is an integer array A of size N.
Second argument is an integer B denoting the sum you need to get close to.
Output Format
Return a single integer denoting the sum of three integers which is closest to B.
Example Input
Input 1:
A = [-1, 2, 1, -4]
B = 1
Input 2:
A = [1, 2, 3]
B = 6
Example Output
Output 1:
2
Output 2:
6
Example Explanation
Explanation 1:
 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
Explanation 2:
 Take all elements to get exactly 6.
     */

    public int threeSumClosest(int[] A, int B) {
        long ClosestSum = Integer.MAX_VALUE;
        Arrays.sort(A);

        for(int i=0; i<A.length-2;i++){
            long sum = 0;
            int second = i+1;
            int last =A.length-1;
            while(second < last){
                sum = A[i] + A[second] + A[last];
                if(Math.abs(B-sum) < Math.abs(B-ClosestSum)){
                    ClosestSum = sum;
                }
                if(sum > B)
                    last--;
                else
                    second++;
            }
        }
        return (int)ClosestSum;
    }

    public static void main(String[] args) {
        TripletSum ts = new TripletSum();
        System.out.println(ts.threeSumClosest(new int[]{2, 1, -9, -7, -8, 2, -8, 2, 3, -8 },-1));
    }
}
