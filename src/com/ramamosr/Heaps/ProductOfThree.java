package com.ramamosr.Heaps;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class ProductOfThree {
    /*
    Problem Description

Given an integer array A of size N.

You have to find the product of the 3 largest integers in array A from range 1 to i, where i goes from 1 to N.

Return an array B where B[i] is the product of the largest 3 integers in range 1 to i in array A. If i < 3, then the integer at index i is -1.



Problem Constraints

1 <= N <= 105
0 <= A[i] <= 103



Input Format

First and only argument is an integer array A.



Output Format

Return an integer array B. B[i] denotes the product of the largest 3 integers in range 1 to i in array A.



Example Input

Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [10, 2, 13, 4]


Example Output

Output 1:

 [-1, -1, 6, 24, 60]
Output 2:

 [-1, -1, 260, 520]


Example Explanation

Explanation 1:

 For i = 1, ans = -1
 For i = 2, ans = -1
 For i = 3, ans = 1 * 2 * 3 = 6
 For i = 4, ans = 2 * 3 * 4 = 24
 For i = 5, ans = 3 * 4 * 5 = 60

 So, the output is [-1, -1, 6, 24, 60].

Explanation 2:

 For i = 1, ans = -1
 For i = 2, ans = -1
 For i = 3, ans = 10 * 2 * 13 = 260
 For i = 4, ans = 10 * 13 * 4 = 520

 So, the output is [-1, -1, 260, 520].
     */

    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(4);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(-1);
        result.add(-1);
        if(A.size()<=2)
            return result;
        int prod = 1;
        int i=0;
        for( i=0; i<=2;i++){
            pq.offer(A.get(i));
            prod*=A.get(i);
        }
        result.add(prod);
        for(i=3;i<A.size();i++){
            pq.offer(A.get(i));
            prod = (prod/pq.poll()) * A.get(i);
            result.add(prod);
        }
        return result;
    }
/*
If we have traversed the array till some number, (say ith number), we will only add numbers further to it, and no deletion will occur.

A max heap will have the largest number at the top of it. Once the top number is removed, it will have the second largest number at the top. Once the second largest number is removed, we will have the third largest number at the top.

Using these two observation, we can devise the following algorithm to compute the product of three largest number from first number to ith number.

Take the ith number, and add it to the max heap.
Take the top number, this is the largest number from first number to the ith number. Store this in your product.
Delete the largest number. Now, the top number will have the second largest number. Take this and multiply to the product.
Delete the top element of the max heap and now the top element is the third largest number. Read that and multiply to the product so far to get the product of the three largest numbers from first to ith number.
We can directly use priority queues (from STL in cpp), since they are already an implementation of the max heaps.

The above runs for each number in the list and hence the worst case time complexity would be O(n log(n)) which would easily fit with the constraints.
 */
    public int[] solveScaler(int[] A) {
        // n is the size of the vector
        int n = A.length;
        // Initialize an answer vector
        ArrayList < Integer > ans = new ArrayList < Integer > ();
        // Initialize a priority queue
        PriorityQueue < Integer > pq = new PriorityQueue(new CustomComp());

        for (int i = 0; i < n; i++) {

            // Add element in priority queue
            pq.offer(A[i]);

            // Append -1 to the answer as number of elements are < 3.
            if (i < 2) {
                ans.add(-1);
            } else {
                //take 3 maximum elements from queue.
                int a = pq.poll();
                int b = pq.poll();
                int c = pq.poll();

                //add all these numbers back to queue
                pq.offer(a);
                pq.offer(b);
                pq.offer(c);

                //append the product to answer
                ans.add(a * b * c);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }
}
class CustomComp implements Comparator< Integer > {
    @Override
    public int compare(Integer a, Integer b) {
        return b - a;
    }

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        ProductOfThree pot = new ProductOfThree();
        pot.solve(input);
    }
}