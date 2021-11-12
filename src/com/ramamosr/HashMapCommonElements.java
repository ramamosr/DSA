package com.ramamosr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HashMapCommonElements {
    /*
    Common Elements
Problem Description

Given two integer array A and B of size N and M respectively. Your task is to find all the common elements in both the array.

NOTE:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.


Problem Constraints
1 <= N, M <= 105

1 <= A[i] <= 109



Input Format
First argument is an integer array A of size N.

Second argument is an integer array B of size M.



Output Format
Return an integer array denoting the common elements.



Example Input
Input 1:

 A = [1, 2, 2, 1]
 B = [2, 3, 1, 2]
Input 2:

 A = [2, 1, 4, 10]
 B = [3, 6, 2, 10, 10]


Example Output
Output 1:

 [1, 2, 2]
Output 2:

 [2, 10]


Example Explanation
Explanation 1:

 Elements (1, 2, 2) appears in both the array. Note 2 appears twice in both the array.
Explantion 2:

 Elements (2, 10) appears in both the array.
     */
    public int[] solve(int[] A,int[] B){
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashMap<Integer,Integer> hmA = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> hmB = new HashMap<Integer,Integer>();
        for(int i =0; i < A.length;i++){
            if(hmA.containsKey(A[i]))
                hmA.put(A[i],hmA.get(A[i]) +1);
            else
                hmA.put(A[i],1);
        }
        // Reduce the value from the hashmap for the matching digit in Array B.
        // If a digit occurs twice in A, the value will be set to 2.
        // if the same digit occurs thrice in B, THe result should show only 2 entries which occur
        // both in Array A & B. So when the count gets to zero, do not add it to the result.
        for(int i =0; i < B.length; i++){
            if(hmA.containsKey(B[i]) && hmA.get(B[i]) > 0) {
                hmA.put(B[i], hmA.get(B[i]) - 1);
                result.add(B[i]);
            }
                /*if(hmB.containsKey(B[i])) {
                    val = hmB.get(B[i]) +1;
                    if (val <= hmA.get(B[i])) {
                        hmB.put(B[i], val);
                        result.add(B[i]);
                    }
                }
                else{
                    hmB.put(B[i], 1);
                    result.add(B[i]);
                }*/

        }

        int[] arr = new int[result.size()];
        int k = 0;
        for(int value : result){
            arr[k] = value;
            k++;
        }
        return arr;
    }
    public static void main(String[] args) {
    HashMapCommonElements hmce = new HashMapCommonElements();
//        int[] A = {1, 2, 2, 1};
//        int[] B = {2, 3, 1, 2};

        int[] A = {2, 1, 4, 10};
        int[] B = {3, 6, 2, 10, 10};
        hmce.solve(A,B);

    }
}
