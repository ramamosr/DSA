package com.ramamosr.Hashing;

import java.util.Map;
import java.util.TreeMap;

public class SortArrayInGivenOrder {
    /*
    Problem Description

Given two array of integers A and B, Sort A in such a way that the relative order among the elements will be the same as those are in B. For the elements not present in B, append them at last in sorted order.

Return the array A after sorting from the above method.

NOTE: Elements of B are unique.
Problem Constraints
1 <= length of the array A <= 100000
1 <= length of the array B <= 100000
-10^9 <= A[i] <= 10^9
Input Format
The first argument given is the integer array A.
The second argument given is the integer array B.
Output Format
Return the array A after sorting as described.
Example Input
Input 1:
A = [1, 2, 3, 4, 5]
B = [5, 4, 2]
Input 2:

A = [5, 17, 100, 11]
B = [1, 100]
Example Output
Output 1:
[5, 4, 2, 1, 3]
Output 2:
[100, 5, 11, 17]
Example Explanation
Explanation 1:

 Simply sort as described.
Explanation 2:

 Simply sort as described.
     */
    /*
    Loop through A, store the count of every number in a TreeMap to store in sorted manner (key: number, value: count of number) .
Loop through B, check if it is present in TreeMap,
if so, put in output array that many times and remove the number from TreeMap.
Sort the rest of the numbers present in TreeMap and put in output array.
     */
    public int[] solve(int[] A, int[] B) {
        TreeMap<Integer,Integer> hm = new TreeMap<Integer,Integer>();
        for(int i= 0;i <A.length;i++) {
            if(hm.containsKey(A[i]))
                hm.put(A[i], hm.get(A[i]) + 1);
            else
                hm.put(A[i],1);
        }
        int k = 0;
        for(int j = 0; j<B.length;j++){
            if(hm.containsKey(B[j])){
                for(int cnt = 0; cnt < hm.get(B[j]);cnt++){
                    A[k] = B[j];
                    k++;
                }
                hm.remove(B[j]);
            }
        }

        for(Map.Entry e: hm.entrySet()){
            int count = (int) e.getValue();
            for(int t = 0; t < count;t++){
                A[k] = (int)e.getKey();
                k++;
            }
            //hm.remove(e);
        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = {1,2,3,4,5};
        int[] B = {5,4,2};

        SortArrayInGivenOrder saig = new SortArrayInGivenOrder();
        saig.solve(A,B);
    }
}
