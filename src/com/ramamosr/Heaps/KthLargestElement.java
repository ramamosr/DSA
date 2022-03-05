pacakge com.ramamosr.Heaps;

import java.util.PriorityQueue;


public class KthLargestElement {
    /*
    Problem Description

Given an integer array B of size N.

You need to find the Ath largest element in the subarray [1 to i] where i varies from 1 to N. In other words, find the Ath largest element in the sub-arrays [1 : 1], [1 : 2], [1 : 3], ...., [1 : N].

NOTE: If any subarray [1 : i] has less than A elements then output array should be -1 at the ith index.



Problem Constraints

1 <= N <= 100000
1 <= A <= N
1 <= B[i] <= INT_MAX



Input Format

First argument is an integer A.
Second argument is an integer array B of size N.



Output Format

Return an integer array C, where C[i] (1 <= i <= N) will have the Ath largest element in the subarray [1 : i].



Example Input

Input 1:

 A = 4
 B = [1 2 3 4 5 6]
Input 2:

 A = 2
 B = [15, 20, 99, 1]


Example Output

Output 1:

 [-1, -1, -1, 1, 2, 3]
Output 2:

 [-1, 15, 20, 20]


Example Explanation

Explanation 1:

 for i <= 3 output should be -1.
 for i = 4 , Subarray [1:4] has 4 elements 1, 2, 3 and 4. So, 4th maximum element is 1.
 for i = 5 , Subarray [1:5] has 5 elements 1, 2, 3, 4 and 5. So, 4th maximum element is 2.
 for i = 6 , Subarray [1:6] has 6 elements 1, 2, 3, 4, 5 and 6. So, 4th maximum element is 3.
 So, output array is [-1, -1, -1, 1, 2, 3].

Explanation 2:

 for i <= 1 output should be -1.
 for i = 2 , Subarray [1:2] has 2 elements 15 and 20. So, 2th maximum element is 15.
 for i = 3 , Subarray [1:3] has 3 elements 15, 20 and 99. So, 2th maximum element is 20.
 for i = 4 , Subarray [1:4] has 4 elements 15, 20, 99 and 1. So, 2th maximum element is 20.
 So, output array is [-1, 15, 20, 20].
     */


    /*
    One solution is to use Min Heap of size k to store k largest numbers. The Kth highest element is always at root and can be found in O(1) time.

How to process a new number?
Compare the new number value, X with root of heap. If X is smaller, then ignore it. Otherwise replace root with X and call heapify for the root of modified heap.

Time complexity of adding new element and finding the Kth highest element is O(LogK).
STL priority queue can be used to implement a heap directly.
     */
    public int[] solve(int A, int[] B) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int len = B.length;
        int[] result = new int[B.length];
        int index = 0;
        for(int i=0; i< len;i++){

            if(pq.size()<A){
                pq.offer(B[i]);
                // after adding the element check if the size of the PQ becomes equal to A
                // if so, then take the peek to get the min element to get the Kth largest element
                // in the array.
                // The kth largest element is the smallest element in the array of size K.
                if(pq.size()==A)
                    result[index++] = pq.peek();
                else
                    result[index++] = -1;
            }
            else{
                if(B[i] > pq.peek()){
                    pq.poll();
                    pq.offer(B[i]);
                }
                result[index++] = pq.peek();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {15, 20, 99, 1};
        int A = 2;
        KthLargestElement kle = new KthLargestElement();
        kle.solve(A,input);


    }
}
