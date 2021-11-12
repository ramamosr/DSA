package com.ramamosr;

public class KthSmallestElement {
/*
Kth Smallest Element
Problem Description

Find the Bth smallest element in given array A .

NOTE: Users should try to solve it in <= B swaps.



Problem Constraints
1 <= |A| <= 100000

1 <= B <= min(|A|, 500)

1 <= A[i] <= 109



Input Format
First argument is vector A.

Second argument is integer B.



Output Format
Return the Bth smallest element in given array.



Example Input
Input 1:

A = [2, 1, 4, 3, 2]
B = 3
Input 2:

A = [1, 2]
B = 2


Example Output
Output 1:

 2
Output 2:

 2


Example Explanation
Explanation 1:

 3rd element after sorting is 2.
Explanation 2:

 2nd element after sorting is 2.
 */
// DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int kthsmallest(final int[] A, int B) {
        int minValue = 0;
        int minIndex = 0;
        for(int i = 0,swap=0; i<A.length && swap<B;i++){
            minValue = A[i];
            minIndex = i;
            for(int j=i+1; j<A.length;j++){
                if(minValue > A[j]){
                    minValue = A[j];
                    minIndex = j;
                }
            }
            if(i!=minIndex) {
                int temp = A[i];
                A[i] = A[minIndex];
                A[minIndex] = temp;
                swap++;
            }
        }
        return A[B-1];
    }
    public static void main(String[] args) {
    KthSmallestElement kSE = new KthSmallestElement();
    int[] arr = new int[]{2, 1, 4, 3, 2};
    int B = 3;
    kSE.kthsmallest(arr,B);
    }
}
