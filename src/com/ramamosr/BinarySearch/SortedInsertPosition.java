package com.ramamosr.BinarySearch;

public class SortedInsertPosition {
    /*
    Problem Description

Given a sorted array A of size N and a target value B, return the index (0-based indexing) if the target is found.
If not, return the index where it would be if it were inserted in order.

NOTE: You may assume no duplicates in the array. Users are expected to solve this in O(log(N)) time.
Problem Constraints
1 <= N <= 106
Input Format
First argument is an integer array A of size N.
Second argument is an integer B.
Output Format
Return an integer denoting the index of target value.
Example Input
Input 1:

A = [1, 3, 5, 6]
 B = 5
Input 2:

A = [1]
 B = 1
Example Output
Output 1:
2
Output 2:
0
Example Explanation
Explanation 1:
The target value is present at index 2.
Explanation 2:
The target value is present at index 0.
     */

    /*
    Do a binary search of the element in the array.
    if it exists return the index.
    if not find the element which is just greater than the value and return that index.
    In Binary search, if mid equals the  element return the mid index.
    if mid is greater, then move left, when moving left,move the end index = mid-1
    if mid is lower than the element move right. when moving right move the start end = mid+1;
     */
    public int searchInsert(int[] A, int B) {
        int startIndex = 0, endIndex = A.length-1;
        while(startIndex <= endIndex){
            int mid = (startIndex + endIndex) / 2;

            if(A[mid] == B) return mid;

            else if(A[mid]<B)
                startIndex = mid + 1;
            else
                endIndex = mid - 1;
        }
        return endIndex + 1;
    }

    public static void main(String[] args) {
        SortedInsertPosition sip = new SortedInsertPosition();
        System.out.println(sip.searchInsert(new int[]{1,3,5,6},7));
    }
}
