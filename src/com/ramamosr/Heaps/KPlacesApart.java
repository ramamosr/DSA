package com.ramamosr.Heaps;

import java.util.PriorityQueue;

public class KPlacesApart {
    /*
    Problem Description

Given N persons with different priorities standing in a queue.

Queue is following a property that Each person is standing atmost B places away from it's sorted position.

Your task is to sort the queue in the increasing order of priorities.

NOTE:

No two persons can have the same priority.
Use the property of the queue to sort the queue with complexity O(NlogB).


Problem Constraints

1 <= N <= 100000
0 <= B <= N



Input Format

First argument is an integer array A representing the priorities of N persons.
Second argument is an integer B.



Output Format

Return an integer array representing the sorted queue.



Example Input

Input 1:

 A = [1, 40, 2, 3]
 B = 2
Input 2:

 A = [2, 1, 17, 10, 21, 95]
 B = 1


Example Output

Output 1:

 [1, 2, 3, 40]
Output 2:

 [1, 2, 10, 17, 21, 95]


Example Explanation

Explanation 1:

 Given array A = [1, 40, 2, 3]
 After sorting, A = [1, 2, 3, 40].
 We can see that difference between initial position of elements amd the final position <= 2.
Explanation 2:

 After sorting, the array becomes [1, 2, 10, 17, 21, 95].
     */

    /*
    Take the first B+1 elements in a list, finding the minimum of the list will give us the first element in the sorted array.

Remove the smallest element from the list and add the (B+2)th element from the given array to the list.

Now taking the minimum of the list will give us the second element in the sorted array as each element is <= B distance away from it’s sorted position.

Proceed in the same way and keep on finding the minimum of B+1 elements.

We can use priority_queue to implement the above solution. Each time we remove the minimum element from the queue and add the new element to the queue.

Time complexity of removing an element from the priority_queue will be O(log B) as the size of the queue is <= B+1 and we will remove all N elements from the queue one by one.

So, overall time complexity will be O(NlogB).
     */

    public int[] solve(int[] A, int B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] result = new int[A.length];
        int index = 0;
        // insert first K elements to the array.
        for(int i=0; i<=B;i++){
            pq.offer(A[i]);
        }
        result[index++] = pq.poll();
        for(int i=B+1;i <A.length;i++) {
            pq.offer(A[i]);
            if (pq.size() == B) {
                result[index++] = pq.poll();
            }
        }
        while(pq.size()>0){
            result[index++] = pq.poll();
        }
        return result;
    }

    public static void main(String[] args) {

        int[] A = {2, 1, 17, 10, 21, 95};
        int B= 1;
        KPlacesApart kpa = new KPlacesApart();
        kpa.solve(A,B);
    }
}
