package com.ramamosr.ProblemSolvingAssignment;

import java.util.HashMap;

public class ContiguousSubArray {

    /*
    Contiguous Array
Given an array of integers A consisting of only 0 and 1.

Find the maximum length of a contiguous subarray with equal number of 0 and 1.



Input Format

The only argument given is the integer array A.
Output Format

Return the maximum length of a contiguous subarray with equal number of 0 and 1.
Constraints

1 <= length of the array <= 100000
0 <= A[i] <= 1
For Example

Input 1:
    A = [1, 0, 1, 0, 1]
Output 1:
    4
    Explanation 1:
        Subarray from index 0 to index 3 : [1, 0, 1, 0]
        Subarray from index 1 to index 4 : [0, 1, 0, 1]
        Both the subarrays have equal number of ones and equal number of zeroes.

Input 2:
    A = [1, 1, 1, 0]
Output 2:
    2
     */

    public int solve(int[] A) {
        // Replace all the zeros by -1.
        // then to a prefix sum.
        // Insert into a hashMap with starting index for every unique element in the PS array.
        // you can maintain the length of 0s and 1s separately or
        // update the sum only if the length increases.
        // Condition is array length is 1. if array length is 1 return 0.
        if(A.length==1) return 0;
        //replace 0 with -1
        for(int i=0; i<A.length;i++){
            if(A[i]==0) A[i] = -1;
        }

        int[] psum = new int[A.length];
        HashMap<Integer,Integer> hm = new HashMap<>();
        // To take care of edge cases where the index starts with zero and ps becomes -1.
        // if ps[0] = -1, then we will have to assume there was a zero before the 0th index.
        hm.put(0,-1);
        psum[0] = A[0];
        if(!hm.containsKey(psum[0])){
            hm.put(psum[0],0);
        }
        for(int i=1; i<A.length;i++){
            psum[i] = psum[i-1] + A[i];
            if(!hm.containsKey(psum[i])){
                hm.put(psum[i],i);
            }
        }
        int len = 0;
        for(int i=0;i<psum.length;i++){
            if(hm.containsKey(psum[i])) {
                int temp = i - hm.get(psum[i]);
                if (temp > len)
                    len = i - hm.get(psum[i]);
            }
        }
        return len;
    }

    public int solveScaler(int [] A){
        // subarray having some sum
        HashMap<Integer, Integer> map = new HashMap<>();

        // insert `(0, -1)` pair into the set to handle the case when a
        // subarray with zero-sum starts from index 0
        //map.put(0, -1);

        // `len` stores the maximum length of subarray with zero-sum
        int len = 0;

        // stores ending index of the largest subarray having zero-sum
        int ending_index = -1;

        int sum = 0;

        // Traverse through the given array
        for (int i = 0; i < A.length; i++)
        {
            // sum of elements so far (replace 0 with -1)
            sum += (A[i] == 0)? -1: 1;

            // if the sum is seen before
            if (map.containsKey(sum))
            {
                // update length and ending index of largest subarray having zero-sum
                if (len < i - map.get(sum))
                {
                    len = i - map.get(sum);
                    ending_index = i;
                }
            }
            // if the sum is seen for the first time, insert the sum with its
            // index into the map
            else {
                map.put(sum, i);
            }
        }

        // print the subarray if present
        if (ending_index != -1)
        {
            return len;

        }
        else {
            return 0;
        }
    }
    public static void main(String[] args) {
        ContiguousSubArray csa = new ContiguousSubArray();
        System.out.println(csa.solve(new int[]{0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1}));
        System.out.println(csa.solve(new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0}));
    }
}
