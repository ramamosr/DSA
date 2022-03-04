package com.ramamosr.Heaps;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MaximumSumArray {

    /*
        Given an array of integers A and an integer B. You must modify the array exactly B number of times. In single modification, we can replace any one array element A[i] by -A[i].

You need to perform these modifications in such a way that after exactly B modifications, sum of the array must be maximum.



Problem Constraints

1 <= length of the array <= 5*105
1 <= B <= 5 * 106
-100 <= A[i] <= 100



Input Format

First argument given is an integer array A.
Second argument given is an integer B.



Output Format

Return an integer denoting the maximum array sum after B modifications.



Example Input

Input 1:

 A = [24, -68, -29, -9, 84]
 B = 4
Input 2:

 A = [57, 3, -14, -87, 42, 38, 31, -7, -28, -61]
 B = 10


Example Output

Output 1:

 196
Output 2:

 362


Example Explanation

Explanation 1:

 Final array after B modifications = [24, 68, 29, -9, 84]
Explanation 2:

 Final array after B modifications = [57, -3, 14, 87, 42, 38, 31, 7, 28, 61]
     */

    // use the priority queue to create a min heap.
    // take the smallest element and multiply by -1.
    // add it back to the heap till B becomes 0.
    public int solve(ArrayList<Integer> A, int B) {
        int maxSum=0;
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i:A){
            pq.add(i);
        }
        while(B-->0){
            int temp=pq.poll();
            temp*=-1;
            pq.add(temp);
        }
        for(int j:pq){
            maxSum+=j;
        }
        return maxSum;
    }
    /*
    This problem can simply be solved by just changing the minimum element A[i] to -A[i].

Keep on getting the minimum element from the array and replace that element to -1. Do this exactly B times.

It is easy to observe that, if the minimum element is zero, we can’t increase our answer by any modification.
If the minimum element is x < 0, then just change it to -x.
If the minimum element is x > 0 and number of operations left is even. Do not need to change anything.
If the minimum element is x > 0 and number of operations left is odd. We can directly change number of operations left to 0 and set x to -x.

Now, just find the sum of all the elements.
     */

    public int solveScaler(int[] A, int B) {
        PriorityQueue < Integer > pq = new PriorityQueue();

        // insert all elements into the queue
        for (int x: A) pq.offer(x);

        // perform B modifications

        while (B > 0) {
            // pop minimum lement from the queue
            int x = pq.poll();

            // if minimum element is 0, we will use all remaining opeations on this and the result will be same
            if (x == 0) {
                B = 0;
            }
            // if minimum element is negative, modify the element to -x and push -x to queue.
            else if (x < 0) {
                pq.offer(-x);
            } else {
                //if remaining operations are even, then in one operation we convert x to -x and in another -x to x. So, no change
                //if operations are odd, we will change the number to -x. Set B = 0.
                if (B % 2 == 0) {
                    pq.offer(x);
                } else {
                    pq.offer(-x);
                }

                B = 0;
                break;
            }

            B--;
        }

        int ans = 0;

        // add all the elements in the queue to the answer
        while (pq.size() > 0) {
            ans += pq.poll();
        }

        return ans;
    }
    public static void main(String[] args) {
        MaximumSumArray msa = new MaximumSumArray();
        ArrayList<Integer> input = new ArrayList<>();
        input.add(24);
        input.add(-68);
        input.add(-29);
        input.add(-9);
        input.add(84);
        System.out.println(msa.solve(input,4));
    }

}
