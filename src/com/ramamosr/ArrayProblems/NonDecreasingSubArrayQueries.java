package com.ramamosr.ArrayProblems;

import java.util.ArrayList;
import java.util.Arrays;

public class NonDecreasingSubArrayQueries {

    /*
    Non decreasing subarray queries
Problem Description

Given an array A of size N. You will be given M queries to process. Each query will be of the form B[i][0] B[i][1].

If the subarray from B[i][0] to B[i][1] is non decreasing, the output should be 1 else output should be 0.

Return an array of integers answering each query.


Problem Constraints
1 <= N <= 105

1 <= A[i] <= 109

1 <= M <= 105

1 <= B[i][0], B[i][1] <= N


Input Format
First argument contains the array A.

Second argument contains B, denoting the queries.


Output Format
Return an array of integers consisting of 0 and 1


Example Input
Input :
A = [1, 7, 3, 4, 9]
B = [
      [1, 2],
      [2, 4]
    ]


Example Output
Input :
[1, 0]


Example Explanation
Explanation :
Query 1: The subarray in the range [1, 2] is {1, 7} which is non-decreasing. Therefore, ans = 1
Query 2: The subarray in the range [2, 4] is {7, 3, 4, 9} which is not non-decreasing. Therefore, ans = 0
     */
    // Approach is to take the prefix sum.
    /*
    Scaler approach
    If we want to prove that some subarray is actually not non-decreasing then all we need that is there should be atleast one irregularity. Irregularity can be defined as if for some i, A[i]>A[i+1]. That means the subarray was not non-decreasing.

We can create a new array where if arr[i] is 1 then it will mean that A[i]>A[i+1], that means it was an irregularity. Now we need to find whether there is some arr[i] = 1 for i between l and r-1.

We can create a prefix array that stores the sum of these irregularities. If there is even a single irregularity between l and r then ans will be 0.

We can find the sum of irregularities as pre[r-1]-pre[l-1].

Here note that we are not taking the arr[r] value because A[r+1] is not our concern.
     */
    /*
    For non-decreasing we will have to check if the i+1th element is greater than ith element.
    if its greater mark it as 0. if its less mark it as 1.
    Then take a prefix sum of the array after marking it as 0s and 1s.
    If the PS sum is zero for any range, then its non-decreasing.
     */
    public int[] solve(int[] A, int[][] B) {
        if(A.length==0)
            return new int[0];
        int[] preSum = new int[A.length];
        int[] result = new int[B.length];

        preSum[0] = 0;
        for(int i=1; i < A.length;i++){
            if(A[i] < A[i-1]){
                preSum[i] = preSum[i-1] + 1;
            }
            else{
                preSum[i] = preSum[i-1];
            }
        }
        int count = 0;
        for(int j= 0; j<B.length;j++){
            //Assuming its the 0th index we will have to reduce 1 from the index values.
            // after the prefix sum, we will have to check the sum from 1st element in the subarray.
            // Prefix sum in a range (L, R) is calculated by PS(R)- PS(L-1);
            // In this case, the ith element or starting element will not determine the non-decreasing order.
            // so we will have to check from the i+1 element. Substituting that in the prefix sum logic.
            //PS(R)- PS(L-1+1) = PS(R) - PS(L). We will have to make it to 0th index based in our solution.
            // take the endIndex and startIndex and reduce them by 1.
            int endIndex = B[j][1] - 1;
            int startIndex = B[j][0] - 1;
            if(preSum[endIndex] - preSum[startIndex] > 0)
                result[count] = 0;
            else
                result[count] = 1;

            count++;
        }
        return result;
    }

    public ArrayList<Integer> solveScaler(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int n = A.size();
        int pre[] = new int[n];
        pre[0] = 0;
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1];
            if(A.get(i-1) > A.get(i)) pre[i]++;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(ArrayList<Integer> i : B){
            if((int)pre[i.get(0) - 1] == pre[(i.get(1)) - 1]) {
                ans.add(1);
            } else {
                ans.add(0);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] A = new int[]{1, 7, 3, 4, 9};
        int[][] B = new int[][]{ {1, 2},{2,4}};
        NonDecreasingSubArrayQueries ndsaq = new NonDecreasingSubArrayQueries();
        ndsaq.solve(A,B);
    }
}
