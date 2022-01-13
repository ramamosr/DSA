package com.ramamosr.StacksAndQueues;

import java.util.Stack;

public class LargestRectangleHistogram {

    /*
    Problem Description

Given an array of integers A .

A represents a histogram i.e A[i] denotes height of the ith histogram's bar. Width of each bar is 1.

Find the area of the largest rectangle formed by the histogram.



Problem Constraints

1 <= |A| <= 100000

1 <= A[i] <= 1000000000



Input Format

The only argument given is the integer array A.



Output Format

Return the area of largest rectangle in the histogram.



Example Input

Input 1:

 A = [2, 1, 5, 6, 2, 3]
Input 2:

 A = [2]


Example Output

Output 1:

 10
Output 2:

 2


Example Explanation

Explanation 1:

The largest rectangle has area = 10 unit. Formed by A[3] to A[4].
Explanation 2:

Largest rectangle has area 2.
     */
    /*
    We traverse all bars from left to right, maintain a stack of bars.
    Every bar is pushed to stack once. A bar is popped from stack when a bar of smaller height is seen.
    When a bar is popped, we calculate the area with the popped bar as smallest bar.
    How do we get left and right indexes of the popped bar – the current index tells us the ‘right index’
    and index of previous item in stack is the ‘left index’.
     */
    /*
    From our previous observation, we already know that when I traverse left, I only need entries in decreasing order.

We traverse all histograms from left to right, maintain a stack of histograms. Every histogram is pushed to stack once. A histogram is popped from stack when a histogram of smaller height is seen. When a histogram is popped, we calculate the area with the popped histogram as smallest histogram. How do we get left and right indexes of the popped histogram – the current index tells us the ‘right index’ R and index of previous item in stack is the ‘left index’ L. Following is a rough pseudocode.

max_rectangle = 0
stack = an instance of empty stack
for index = 0 to all_histograms.length
    if stack.empty OR H[index] > H[stack.top]
        Push index to the stack
    if H[index] < H[stack.top]
        while !stack.empty && H[stack.top] > H[index]
            h = H[stack.pop]
            # Calculate the area with h as the smallest height.
            R = index
            L = stack.top
            max_rectangle = MAX(max_rectangle, (R - L - 1) * h)
        Push index to the stack
if stack is not empty
    Repeat the process of removing entries one by one from stack and calculating the max_rectangle as done earlier.     
     */

    public int largestRectangleArea(int[] A) {
        Stack<Integer> histogram = new Stack<Integer>();
        int largestArea = 0;
        int tempArea = 0;
        int top = 0, index = 0;

        while(index<A.length){

            if(histogram.isEmpty() || A[histogram.peek()] <= A[index]) {
                histogram.push(index++);
            }
            else{
                top = histogram.pop();
                tempArea = A[top] * (histogram.empty()?index:index-histogram.peek()-1);
                largestArea = Math.max(largestArea,tempArea);
            }
        }
        while(!histogram.isEmpty()){
            top = histogram.pop();
            tempArea = A[top] * (histogram.empty()?index:index-histogram.peek()-1);
            largestArea = Math.max(largestArea,tempArea);
        }
        return largestArea;
    }

    public static void main(String[] args) {
        LargestRectangleHistogram lrh = new LargestRectangleHistogram();
        System.out.println(lrh.largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3}));
    }
}
