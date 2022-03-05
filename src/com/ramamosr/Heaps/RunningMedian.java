package com.ramamosr.Heaps;

import java.util.PriorityQueue;
import java.util.Collections;

public class RunningMedian {
    /*
    Problem Description

Given an array of integers A denoting a stream of integers. New arrays of integer B and C are formed. Each time an integer is encountered in a stream, append it at the end of B and append median of array B at the C.

Find and return the array C.

NOTE:

If the number of elements are N in B and N is odd then consider medain as B[N/2] ( B must be in sorted order).
If the number of elements are N in B and N is even then consider medain as B[N/2-1]. ( B must be in sorted order).


Problem Constraints

1 <= length of the array <= 100000
1 <= A[i] <= 109



Input Format

The only argument given is the integer array A.



Output Format

Return an integer array C, C[i] denotes the median of first i elements.



Example Input

Input 1:

 A = [1, 2, 5, 4, 3]
Input 2:

 A = [5, 17, 100, 11]


Example Output

Output 1:

 [1, 1, 2, 2, 3]
Output 2:

 [5, 5, 17, 11]


Example Explanation

Explanation 1:

 stream          median
 [1]             1
 [1, 2]          1
 [1, 2, 5]       2
 [1, 2, 5, 4]    2
 [1, 2, 5, 4, 3] 3
Explanation 2:

 stream          median
 [5]              5
 [5, 17]          5
 [5, 17, 100]     17
 [5, 17, 100, 11] 11
     */
    // Form a min heap and max heap
    // add the first element to the max heap first
    // for the rest of the elements, check if the max in max heap
    // is greater than the current element then add it to the max heap
    // else add it to the min heap;
    // after adding to the respective heaps, if the size of the 2 heaps
    // differs by more than 1, then extract from that heap and add it to the other heap.
    // Every iteration take the peek from the maxHeap and add it to the result
    // if the minHeap size is greater than maxHeap, take it from the minHeap, which means
    // we have even number of elements in the input array.then take it from minHeap.


    public int[] solve(int[] A) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int len = A.length;
        int index = 0;
        int[] result = new int[len];
        for(int i =0; i<len;i++){

            if(maxHeap.isEmpty() || maxHeap.peek() > A[i]){
                maxHeap.offer(A[i]);
                if(maxHeap.size() - minHeap.size()>1)
                    minHeap.offer(maxHeap.poll());
            }
            else{
                minHeap.offer(A[i]);
                if(minHeap.size()-maxHeap.size()>1)
                    maxHeap.offer(minHeap.poll());
            }
            if(minHeap.size() > maxHeap.size()){
                result[index++] = minHeap.peek();
            }
            else{
                result[index++] = maxHeap.peek();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 5, 4, 3};
        RunningMedian rm = new RunningMedian();
        rm.solve(A);
    }
}

