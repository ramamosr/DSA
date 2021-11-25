package com.ramamosr.BinarySearch;

public class RotatedSortedArraySearch {
    /*
    Problem Description
Given a sorted array of integers A of size N and an integer B.
array A is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2 ).
You are given a target value B to search. If found in the array, return its index, otherwise return -1.
You may assume no duplicate exists in the array.
NOTE: Users are expected to solve this in O(log(N)) time.

Problem Constraints
1 <= N <= 1000000
1 <= A[i] <= 10^9
all elements in A are disitinct.

Input Format
The first argument given is the integer array A.
The second argument given is the integer B.

Output Format
Return index of B in array A, otherwise return -1
Example Input
Input 1:
A = [4, 5, 6, 7, 0, 1, 2, 3]
B = 4
Input 2:
A = [1]
B = 1

Example Output
Output 1:
 0
Output 2:
 0
Example Explanation
Explanation 1:

Target 4 is found at index 0 in A.
Explanation 2:

Target 1 is found at index 0 in A.
     */
    /*
    SOlution Approach
    Since we dont know how many times the array has been rotated, first find the number of rotations.
    Then based on the pivot, split the array into 2 sorted arrays.
    then search for the element in both the sorted arrays using binary search.
     */
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int search(final int[] A, int B) {

        int pivotIndex = findPivot(A,0, A.length-1);
        int result = -1;
        result = searchElement(A,0,pivotIndex-1,B);
        if(result==-1){
           result = searchElement(A,pivotIndex,A.length-1,B);
        }
        return result;
    }

    public int searchElement(int[] A, int start, int end, int B) {
        int startIndex = start, endIndex = end;
        while(startIndex <= endIndex){
            int mid = (startIndex + endIndex) / 2;

            if(A[mid] == B) return mid;

            else if(A[mid]<B)
                startIndex = mid + 1;
            else
                endIndex = mid - 1;
        }
        return -1;
    }

    public int findPivot(int[] A, int start, int end ){
        int pivot = 0;
        while(start <=end) {
            int mid = (start + end) / 2;
            if(A[0] < A[mid]){
                start = mid + 1;
            }
            else{
                pivot = mid;
                end = mid - 1;
            }
        }
        return pivot;
    }

    public static void main(String[] args) {
        RotatedSortedArraySearch rss = new RotatedSortedArraySearch();
        int[] A = {4, 5, 6, 7,9, 0, 1, 2, 3};
        int B = 0;
        System.out.println(rss.search(A,B));

    }
}
