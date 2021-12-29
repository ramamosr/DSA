package com.ramamosr.StacksAndQueues;

import java.util.Stack;

public class SortStackUsingAnotherStack {
    /*
    Problem Description

Given a stack of integers A, sort it using another stack.

Return the array of integers after sorting the stack using another stack.



Problem Constraints

1 <= |A| <= 5000

0 <= A[i] <= 1000000000



Input Format

The only argument given is the integer array A.



Output Format

Return the array of integers after sorting the stack using another stack.



Example Input

Input 1:

 A = [5, 4, 3, 2, 1]
Input 2:

 A = [5, 17, 100, 11]


Example Output

Output 1:

 [1, 2, 3, 4, 5]
Output 2:

 [5, 11, 17, 100]


Example Explanation

Explanation 1:

 Just sort the given numbers.
Explanation 2:

 Just sort the given numbers.
     */

    /*
    Create a temporary stack say B.

While input stack is not empty:
1. pop an element from input stack calls it x.
2. while the temporary stack is not empty and top of the temporary stack is greater than x pop from the temporary stack and push it into input stack.
3. push x in the temporary stack.

The sorted numbers are in the temporary stack.
     */

    public int[] solve(int[] A) {
        int[] result = new int[A.length];
        Stack<Integer> input = new Stack<>();
        Stack<Integer> sorted = new Stack<>();

        for(int i=0;i <A.length;i++){
            input.push(A[i]);
        }
        while(!input.isEmpty()) {
            // take the top element from the input stack.
            int curr = input.pop();
            // Add it to the sorted stack. But if the top element in the sorted stack is greater
            // than the current element, remove the top element and add it back to the input stack.
            // do this till the time time the top element in sorted stack is either less than or
            // equal to the current element and then add it to the sorted stack.
            // Sorted stack will be sorted from bottom to top. Add it to the result array in the reverse
            //order.
            // if we check for sorted.peek() < curr, the sorted stack will be sorted from top to bottom.
            while (!sorted.isEmpty() && sorted.peek() > curr) {
                input.push(sorted.pop());
            }
            sorted.push(curr);
        }
        for(int i=result.length-1; i>=0;i--){
            result[i] = sorted.pop();
        }
        return result;
    }

    public static void main(String[] args) {

        int[] A = {5, 4, 3, 2, 1};
        SortStackUsingAnotherStack ssuas = new SortStackUsingAnotherStack();
        ssuas.solve(A);
    }
}
