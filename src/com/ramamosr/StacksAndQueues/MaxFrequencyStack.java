package com.ramamosr.StacksAndQueues;

import java.util.Stack;
import java.util.HashMap;

public class MaxFrequencyStack {

    /*
    Problem Description

You are given a matrix A which represent operations of size N x 2. Assume initially you have a stack-like data structure you have to perform operations on it.

Operations are of two types:

1 x: push an integer x onto the stack and return -1

2 0: remove and return the most frequent element in the stack.

If there is a tie for the most frequent element, the element closest to the top of the stack is removed and returned.

A[i][0] describes the type of operation to be performed. A[i][1] describe the element x or 0 corresponding to the operation performed.



Problem Constraints

1 <= N <= 100000

1 <= A[i][0] <= 2

0 <= A[i][1] <= 109



Input Format

The only argument given is the integer array A.



Output Format

Return the array of integers denoting answer to each operation.



Example Input

Input 1:

A = [
            [1, 5]
            [1, 7]
            [1, 5]
            [1, 7]
            [1, 4]
            [1, 5]
            [2, 0]
            [2, 0]
            [2, 0]
            [2, 0]  ]
Input 2:

 A =  [
        [1, 5]
        [2 0]
        [1 4]   ]


Example Output

Output 1:

 [-1, -1, -1, -1, -1, -1, 5, 7, 5, 4]
Output 2:

 [-1, 5, -1]


Example Explanation

Explanation 1:

 Just simulate given operations
Explanation 2:

 Just simulate given operations
     */

    public int[] solve(int[][] A) {
        int[] result = new int[A.length];
        // Map between the element and the frequency
        HashMap<Integer,Integer> freq  = new HashMap<>();
        // Map between frequency and the element stack
        HashMap<Integer, Stack<Integer>> elemStack = new HashMap<>();
        int maxFreq = 0;

        for(int i=0; i<A.length;i++){

            int oper = A[i][0];
            int element = A[i][1];
            if(oper==1){
                if(freq.containsKey(element))
                    freq.put(element, freq.get(element) + 1);
                else
                    freq.put(element,1);
                maxFreq = Math.max(maxFreq,freq.get(element));
                // Add to the stack based on frequency of the element.
                // for every frequency, the element gets added to the corresponding stack.
                // if 5 occurs 3 times, 5 will be added to 3 stacks 1 for each freq.
                if(elemStack.containsKey(freq.get(element)))
                    elemStack.get(freq.get(element)).push(element);
                else {
                    Stack<Integer> tmp = new Stack<>();
                    tmp.push(element);
                    elemStack.put(freq.get(element),tmp);
                }
                result[i] = -1;
            }
            else{
                // gets the max freq element at the top of the element frequency stack.
                int ans = elemStack.get(maxFreq).peek();
                // reduce the frequency of the element.
                freq.put(ans,freq.get(ans)-1);
                // remove the top element from the element frequency stack.
                elemStack.get(maxFreq).pop();
                // if there are no elements in the frequency, reduce the max frequency.
                if(elemStack.get(maxFreq).size()==0)
                    maxFreq--;
                result[i] = ans;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        MaxFrequencyStack mfs = new MaxFrequencyStack();
        int[][] A =  {{1, 5},{1, 7},{1, 5},{1, 7},{1, 4}, {1, 5}, {2, 0},{2, 0},{2, 0},{2, 0} };
        mfs.solve(A);
    }
}
