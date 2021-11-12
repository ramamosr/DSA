package com.ramamosr;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {

    /*
    Sliding Window Maximum
Problem Description

Given an array of integers A. There is a sliding window of size B which is moving from the very left of the array to the very right. You can only see the B numbers in the window. Each time the sliding window moves rightwards by one position. You have to find the maximum for each window.

Return an array C, where C[i] is the maximum value in the array from A[i] to A[i+B-1].

Refer to the given example for clarity.

NOTE: If B > length of the array, return 1 element with the max of the array.



Problem Constraints
1 <= |A|, B <= 106



Input Format
The first argument given is the integer array A.

The second argument given is the integer B.



Output Format
Return an array C, where C[i] is the maximum value of from A[i] to A[i+B-1].



Example Input
Input 1:

 A = [1, 3, -1, -3, 5, 3, 6, 7]
 B = 3
Input 2:

 A = [1, 2, 3, 4, 2, 7, 1, 3, 6]
 B = 6


Example Output
Output 1:

 [3, 3, 5, 5, 6, 7]
Output 2:

 [7, 7, 7, 7]


Example Explanation
Explanation 1:

 Window position     | Max
 --------------------|-------
 [1 3 -1] -3 5 3 6 7 | 3
 1 [3 -1 -3] 5 3 6 7 | 3
 1 3 [-1 -3 5] 3 6 7 | 5
 1 3 -1 [-3 5 3] 6 7 | 5
 1 3 -1 -3 [5 3 6] 7 | 6
 1 3 -1 -3 5 [3 6 7] | 7
Explanation 2:

 Window position     | Max
 --------------------|-------
 [1 2 3 4 2 7] 1 3 6 | 7
 1 [2 3 4 2 7 1] 3 6 | 7
 1 2 [3 4 2 7 1 3] 6 | 7
 1 2 3 [4 2 7 1 3 6] | 7
     */
    public int[] slidingMaximum(final int[] A, int B) {
        /*int[] result = new int[A.length - B +1];

        if(B>A.length){
            int max = A[0];
            for(int i=1; i < A.length;i++){
                max = Math.max(max,A[i]);
            }
            return new int[]{max};
        }
        int resultIndex = 0;
        for(int j=0;j<=A.length-B;j++) {
            int slidingMax = A[j];
            for(int k=j+1; k<j+B;k++){
                slidingMax = Math.max(slidingMax,A[k]);

            }
            result[resultIndex] = slidingMax;
            resultIndex++;
        }
        return result;*/

        Deque<Integer> que = new ArrayDeque<Integer>();
        int[] result = new int[A.length - B +1];
        int resultIndex = 0;
        for(int i=0; i<B;i++){
            while (!que.isEmpty() &&A[i] >= A[que.getLast()]){
                que.removeLast();
            }
            que.addLast(i);
        }
        result[resultIndex] = A[que.getFirst()];
        resultIndex++;

        for(int j=B;j<A.length;j++){
            while((!que.isEmpty() && que.getFirst() <=j-B)){
                que.removeFirst();
            }
            while (!que.isEmpty() &&A[j] >= A[que.getLast()]){
                que.removeLast();
            }
            que.addLast(j);
            result[resultIndex] = A[que.getFirst()];
            resultIndex++;
        }
        return result;
    }
    public static void main(String[] args) {
        SlidingWindowMaximum swm = new SlidingWindowMaximum();
        int[] A = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int B = 2;
        swm.slidingMaximum(A,B);
    }
}
