package com.ramamosr.BinarySearch;

public class SearchForRange {
    /*
    Problem Description
Given a sorted array of integers A(0 based index) of size N, find the starting and ending position of a given integar B in array A.
Your algorithm's runtime complexity must be in the order of O(log n).
Return an array of size 2, such that first element = starting position of B in A and second element = ending position of B in A, if B is not found in A return [-1, -1].

Problem Constraints
1 <= N <= 106
1 <= A[i], B <= 109
Input Format
The first argument given is the integer array A.
The second argument given is the integer B.
Output Format
Return an array of size 2, such that first element = starting position of B in A and second element = ending position of B in A, if B is not found in A return [-1, -1].
Example Input
Input 1:
 A = [5, 7, 7, 8, 8, 10]
 B = 8
Input 2:
 A = [5, 17, 100, 111]
 B = 3
Example Output
Output 1:
 [3, 4]
Output 2:
 [-1, -1]

Example Explanation
Explanation 1:

 First occurence of 8 in A is at index 3
 Second occurence of 8 in A is at index 4
 ans = [3, 4]
Explanation 2:
 There is no occurence of 3 in the array.
     */

    public int[] searchRange(final int[] A, int B) {
        int[] result = new int[2];
        int first = -1,last = -1;
        first = findFirstOccurrence(A,B);
        if(first!=-1)
            last = findLastOccurrence(A,first,B);

        result[0] = first;
        result[1] = last;
        return result;

    }

    public int findFirstOccurrence(int[] A, int B){

        int start = 0, end = A.length-1;
        int first = -1;
        while(start<=end){
            int mid = start + ((end-start)/2);
            if(A[mid]>B)
                end = mid -1;
            else if(A[mid]<B)
                start = mid+1;
            else{
                first = mid;
                end = mid - 1;
            }
        }
        return first;
    }

    public int findLastOccurrence(int[] A, int first, int B){

        int last = -1;
        int start = first, end = A.length-1;
        while(start<=end){
            int mid = start + ((end-start)/2);
            if(A[mid]>B)
                end = mid -1;
            else if(A[mid]<B)
                start = mid+1;
            else{
                last = mid;
                start = mid + 1;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int A[] = {1, 2, 2, 2, 2, 3, 4,4,4,4,5,6, 7, 8, 8};
        int B = 4;
        SearchForRange sfr = new SearchForRange();
        sfr.searchRange(A,B);
    }
}
