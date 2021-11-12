package com.ramamosr;

import java.util.HashMap;

public class SubArrayWithGivenSumClassRoom {

    /*
    Subarray with given sum
Problem Description

Given an array of positive integers A and an integer B, find and return the number of subarrays which adds to B.

If the answer does not exist return an array with a single element "-1".

First sub-array means the sub-array for which starting index in minimum.



Problem Constraints
1 <= length of the array <= 100000
1 <= A[i] <= 109
1 <= B <= 109



Input Format
The first argument given is the integer array A.

The second argument given is integer B.



Output Format
Return the number of subarrays which adds to B and if the answer does not exist return an array with a single element "-1".



Example Input
Input 1:

 A = [1, 2, 3, 4, 5]
 B = 5
Input 2:

 A = [5, 10, 20, 100, 105]
 B = 110


Example Output
Output 1:

1
Output 1:

 -1


Example Explanation
Explanation 1:

 [2, 3] sums up to 5.

Explanation 2:

 No subarray sums up to required number.
     */
    public int solve(int[] A, int B) {
        int result;
        int startIndex = 0;
        int endIndex = -1;
        int ans  = 0;
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();

        int[] prefixSum = new int[A.length +1];
        prefixSum[0] = 0;
        for(int i =0; i <A.length; i++){
            prefixSum[i+1] = prefixSum[i] + A[i];
        }
        for(int j =0; j < prefixSum.length-1;j++){
            if(hm.containsKey(prefixSum[j]-B)){
                ans = ans + hm.get(prefixSum[j]-B);
            }
            if(hm.containsKey(prefixSum[j]))
                hm.put(prefixSum[j],hm.get(prefixSum[j])+1);
            else
                hm.put(prefixSum[j],1);
        }

        return ans;
    }
    public static void main(String[] args) {

        SubArrayWithGivenSumClassRoom sub = new SubArrayWithGivenSumClassRoom();
        System.out.println(sub.solve(new int[]{1, 2, 3, 4, 5},5));
    }
}
