package com.ramamosr.BinarySearch;

public class FindPeakElement {
    /*
    Problem Description

Given an array of integers A, find and return the peak element in it. An array element is peak if it is NOT smaller than its neighbors.
For corner elements, we need to consider only one neighbor. We ensure that answer will be unique.
NOTE: Users are expected to solve this in O(log(N)) time.

Problem Constraints
1 <= |A| <= 100000
1 <= A[i] <= 109



Input Format
The only argument given is the integer array A.
Output Format
Return the peak element.
Example Input
Input 1:
A = [1, 2, 3, 4, 5]
Input 2:
A = [5, 17, 100, 11]

Example Output
Output 1:
5
Output 2:
100

Example Explanation
Explanation 1:
5 is the peak.
Explanation 2:
100 is the peak.
     */

    /*
    Solution Approach
    Edge cases -
    if the 0th element is greater than or equal to 1st element return 0th element.
    if the n-1th element is greater than or equal to n-2th element return n-1th element.
    In other cases, if the mid value of the search is greater than or equal to neighbouring
    elements on both sides mid-1 and mid + 1, then thats a peak element.
    if the current mid is less than mid + 1, then the move to right.
    else move to left as the question guarantees unqiue answer.
     */
    public int solve(int[] A) {
        if(A.length ==1) return A[0];
        if(A[0] >= A[1])
            return A[0];
        if(A[A.length-1] >= A[A.length-2])
            return A[A.length-1];

        int start = 0, end = A.length-1;
        while (start<=end){
            int mid = (start + end) / 2;
            if((A[mid] >= A[mid-1]) && (A[mid] >= A[mid+1]))
                return A[mid];
            if (A[mid] < A[mid+1])
                start = mid +1;
            else
                end = mid - 1;
        }
        return -1;
    }
