package com.ramamosr.hw.IntroToSorting;

import java.beans.PropertyEditorSupport;
import java.util.*;

public class UniqueElement {
    /*
    Unique Elements
Problem Description

You are given an array A of N elements. You have to make all elements unique, to do so in one step you can increase any number by one.

Find the minimum number of steps.



Problem Constraints
1 <= N <= 105
1 <= A[i] <= 109



Input Format
The only argument given is an Array A, having N integers.



Output Format
Return the Minimum number of steps required to make all elements unique.



Example Input
Input 1:

 A = [1, 1, 3]
Input 2:

 A = [2, 4, 5]


Example Output
Output 1:

 1
Output 2:

 0


Example Explanation
Explanation 1:

 We can increase the value of 1st element by 1 in 1 step and will get all unique elements. i.e [2, 1, 3].
Explanation 2:

 All elements are distinct.
     */
    public int solve(int[] A) {
        /// WHy did I use treemap instead of HashMap.
        /// TreeMap does not allow null keys. While iterating treemap preserves the order
        /// holds the sorted order by keys.
        TreeMap<Integer,Integer> hm = new TreeMap<>();
        HashSet<Integer> hs = new HashSet<>();
        Arrays.sort(A);;
        for(int i=0; i < A.length;i++) {
            if (hm.containsKey(A[i])) {
                hm.put(A[i], hm.get(A[i]) + 1);
            } else {
                hm.put(A[i], 1);
                hs.add(A[i]);
            }
        }
            int result = 0, currUsed = 0;
            for(Map.Entry<Integer,Integer> entry : hm.entrySet()){
                int key = entry.getKey();
                int value = entry.getValue();

                if(value ==1)
                    continue;
                int fillKey = Math.max(key+1,currUsed);
                int itemsToIncrement = value - 1;
                while(itemsToIncrement > 0){
                    if(!hs.contains(fillKey)){
                        //if you are incrementing the value of duplicate by 1 every time, it would take the new key - existing key
                        //got the problem wrong first time. you have to increment the value by 1 in every step
                        // for ex: to make a duplicate 1 to 4, it would take 3 steps. which is why
                        // we say the fillkey is 4, original key is 1. so the result should be increased by the difference.
                        result += fillKey-key;
                        hs.add(fillKey);
                        itemsToIncrement--;
                         currUsed = fillKey;
                    }
                    fillKey++;
                }
            }
            return  result;
        }

        /// Scaler Solution
    public int solveScaler(int[] A) {
        int n = A.length;
        // sort the array
        Arrays.sort(A);
        // initialize variables
        int steps = 0, i = A[0], j = 0;
        // loop unitil you reach the end
        while (j < n) {
            // make current element unique
            if (i >= A[j]) {
                steps += (i - A[j]);
                if(i-A[j] >0)
                    A[j] = i ;
                i += 1;
                j++;
            } else {
                i = A[j] + 1;
                j += 1;
            }
        }
        // return the answer
        return steps;
    }

    public static void main(String[] args) {
        UniqueElement ue = new UniqueElement();
        int[] A = new int[] {51, 6, 10, 8, 22, 61, 56, 48, 88, 85, 21, 98, 81, 76, 71, 68, 18, 6, 14, 23, 72, 18, 56, 30, 97, 100, 81, 5, 99, 2, 85, 67, 46, 32, 66, 51, 76, 53, 36, 31, 81, 56, 26, 75, 69, 54, 54, 54, 83, 41, 86, 48, 7, 32, 85, 23, 47, 23, 18, 45, 79, 95, 73, 15, 55, 16, 66, 73, 13, 85, 14, 80, 39, 92, 66, 20, 22, 25, 34, 14, 51, 14, 17, 10, 100, 35, 9, 83, 31, 60, 24, 37, 69, 62};
        //A = new int[] {100, 94, 33, 20, 67, 91, 27, 17, 58, 96, 85, 36, 17, 2, 35, 95, 1, 73, 73, 18, 30, 83, 57, 39, 84, 89, 34, 89, 23, 67, 69, 83, 94, 100, 53, 12, 19, 12, 98, 79, 73, 48, 98, 91, 3, 52, 6, 98, 79, 59, 18, 19, 40, 75, 27, 5, 58, 42, 22, 86, 51 };
        A = new int[]{3,4,4,5,5,7};
        System.out.println(ue.solveScaler(A));
    }
}
