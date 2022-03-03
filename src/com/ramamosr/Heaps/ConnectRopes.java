package com.ramamosr.Heaps;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ConnectRopes {

    /*
    Problem Description

Given an array of integers A representing the length of ropes.

You need to connect these ropes into one rope. The cost of connecting two ropes is equal to the sum of their lengths.

Find and return the minimum cost to connect these ropes into one rope.



Problem Constraints

1 <= length of the array <= 100000
1 <= A[i] <= 1000



Input Format

The only argument given is the integer array A.



Output Format

Return an integer denoting the minimum cost to connect these ropes into one rope.



Example Input

Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [5, 17, 100, 11]


Example Output

Output 1:

 33
Output 2:

 182


Example Explanation

Explanation 1:

 Given array A = [1, 2, 3, 4, 5].
 Connect the ropes in the following manner:
 1 + 2 = 3
 3 + 3 = 6
 4 + 5 = 9
 6 + 9 = 15

 So, total cost  to connect the ropes into one is 3 + 6 + 9 + 15 = 33.
Explanation 2:

 Given array A = [5, 17, 100, 11].
 Connect the ropes in the following manner:
 5 + 11 = 16
 16 + 17 = 33
 33 + 100 = 133

 So, total cost  to connect the ropes into one is 16 + 33 + 133 = 182.
     */

    int parent(int i){
        return ((i-1)/ 2);
    }

    int left(int i){
        return (2*i + 1);
    }

    int right(int i){
        return (2*i + 2);
    }



    public int solve(ArrayList<Integer> A) {
        for(int i=A.size()-1;i >=0;i--){
            MinHeapify(A,i);
        }
        int sum = 0;
        while(A.size()>1){
            int first = extractMin(A);
            int second = extractMin(A);
            sum+=first+second;
            insertIntoHeap(A,first+second);
        }
        return sum;
    }

    public void MinHeapify(ArrayList<Integer> A, int index) {
        while ((left(index) < A.size() && A.get(left(index)) < A.get(index))
                || (right(index) < A.size() && A.get(right(index)) < A.get(index))) {
            int smallest = left(index);
            if (right(index) < A.size() && A.get(right(index)) < A.get(smallest)) {
                smallest = right(index);
            }
            // swap the current Node with the smallest and move the currNode to the smallest node;
            if(smallest==index)
                return;
            int temp = A.get(index);
            A.set(index, A.get(smallest));
            A.set(smallest, temp);
            index = smallest;
        }
    }

    public int extractMin(ArrayList<Integer>A){
        int min = A.get(0);

        // after getting the min value rebuild heap.
        // swap the min element with the last element in the heap
        // and remove the last element.
        A.set(0,A.get(A.size()-1));
        A.remove(A.size()-1);
        int currNode = 0;
        MinHeapify(A,currNode);
        return min;
    }

    public void insertIntoHeap(ArrayList<Integer> A, int value){
        if(A!=null)
            A.add(value);
        int currIndex = A.size()-1;
        // for min heap check if the parent index is less than the current index.
        // if not swap
        while(A.get(parent(currIndex))>A.get(currIndex)){
            int temp = A.get(parent(currIndex));
            A.set(parent(currIndex), A.get(currIndex));
            A.set(currIndex,temp);
            currIndex = parent(currIndex);
        }

    }

    public static void main(String[] args) {

        ConnectRopes cr  = new ConnectRopes();
        ArrayList<Integer> input = new ArrayList<>();
        input.add(5);
        input.add(17);
        input.add(11);
        input.add(100);
        System.out.println(cr.solve(input));
    }
/*
As mentioned in the hint, We should combine the ropes of minimum length first to get the minimum overall cost.
Let’s try to observe with an example: Suppose we are given 4 ropes of length 4, 6, 8 and 10.

1) First connect ropes of lengths 4 and 6. Now, we have three ropes of length 10(4 + 6), 8 and 10.
2) Now connect ropes of lengths 8 and 10. Now, we hace two ropes of length 18(8 + 10) and 10(4 + 6).
3) Now connect both the ropes with cost 18(8 + 10) + 10(4 + 6) = 28 (4 + 6 + 8 + 10).

So, total cost is 28 + 10 + 18 = 56. If we try to connect in some other way, the cost will >= 56.

Let’s try to find how many times each given initial lengths are added to the final cost.

In the first step, combine 4 and 6 and the cost of connecting them is 10 = 4 + 6.
In second step, combine 8 + 10(4 + 6) and cost of connecting them is 18 = 8 + 4 + 6.
In last step, combine 18 + 10 and cost of connecting them is 28 = 8 + 4 + 6 + 10.

So, it is easy to observe that 4 and 6 are added the most number of times, lengths of the ropes which are picked first are included more than once in total cost. So, the idea is to connect the smallest two ropes first and recur for remaining ropes.

To find the answer, take the two ropes with the smallest lengths and combine these ropes. Keep on doing this until we have only one rope left. This can easily be done using priority_queue.
 */
    public int solveScaler(int[] A) {

        PriorityQueue< Integer > pq = new PriorityQueue();
        int cost = 0;

        // insert all elements in the queue
        for (int x: A) {
            pq.offer(x);
        }

        // keep on removing elements from the queue untill there is one element in the queue
        while (pq.size() != 1) {

            // Take the two ropes with smallest length
            int l1 = pq.poll();
            int l2 = pq.poll();
            // cost of combining these two ropes is l1 + l2.
            cost += l1 + l2;

            // add the newly formed rope of length l1 + l2 to the queue.
            pq.offer(l1 + l2);
        }

        return cost;
    }
}
