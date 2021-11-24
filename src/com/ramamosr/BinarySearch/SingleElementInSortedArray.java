package com.ramamosr.BinarySearch;

public class SingleElementInSortedArray {
    /*
    Problem Description
Given a sorted array of integers A where every element appears twice except for one element which appears once, find and return this single element that appears only once.
NOTE: Users are expected to solve this in O(log(N)) time.

Problem Constraints
1 <= |A| <= 100000
1 <= A[i] <= 10^9
Input Format
The only argument given is the integer array A.
Output Format
Return the single element that appears only once.

Example Input
Input 1:
A = [1, 1, 7]
Input 2:
A = [2, 3, 3]
Example Output
Output 1:
 7
Output 2:
 2
Example Explanation
Explanation 1:
 7 appears once
Explanation 2:
2 appears once
*/
    /*
    Do a binary search.
    To find the search space,use the index location.
    if the single unique element lies at the beginning the
    first element of the duplicate will start at odd index.
    else the first element of the duplicate will start at even index.
    but its not guaranteed that the mid will always land at the first occurrence of the duplicate.
    to ensure that check if A[mid] == A[mid+1] and then change the mid to mid+1.
     */

    public int solveWithoutRecursion(int[] A) {
        int ans = 0, n = A.length;
        int low = 0, high = n-1;
        while(low <= high){
            int mid = (high - low)/2 + low;
            if(mid == n - 1)
                return A[n-1];
            if(A[mid] == A[mid+1])
                mid++;
            // if all elements with index < mid are occurring twice then mid should be odd
            if(mid%2 == 1)
                low = mid + 1;
            else{
                ans = mid;
                high = mid - 1;
            }
        }
        return A[ans];
    }

    public int solve(int[] A) {
        int startIndex = 0, endIndex = A.length - 1;

        if (A.length == 1) return A[0];
        if (A[startIndex] != A[startIndex + 1])
            return A[startIndex];
        if (A[endIndex] != A[endIndex - 1])
            return A[endIndex];
        return findSingleElement(A,startIndex,endIndex);
    }

    public int findSingleElement(int[] A, int startIndex, int endIndex){

        while(startIndex<=endIndex) {
            int mid = (startIndex + endIndex) / 2;

            if(startIndex==endIndex)
                return A[startIndex];
            if(mid%2==0){
                if(A[mid]==A[mid+1])
                    return findSingleElement(A,mid+2, endIndex);
                else
                    return findSingleElement(A,startIndex,mid);
            }
            else{
                if(A[mid]==A[mid-1])
                    return findSingleElement(A,mid+1, endIndex);
                else
                    return findSingleElement(A,startIndex,mid-1);
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        SingleElementInSortedArray seis = new SingleElementInSortedArray();
        System.out.println(seis.solve(new int[] {1,1,3,7,7}));

    }
}
