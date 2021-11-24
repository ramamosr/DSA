package com.ramamosr.Hashing;

import java.util.HashSet;

public class LongestConsecutiveSubSequence {

    /*
    Problem Description

Given an unsorted integer array A of size N.

Find the length of the longest set of consecutive elements from the array A.



Problem Constraints

1 <= N <= 106

-106 <= A[i] <= 106



Input Format

First argument is an integer array A of size N.



Output Format

Return an integer denoting the length of the longest set of consecutive elements from the array A.



Example Input

Input 1:

A = [100, 4, 200, 1, 3, 2]
Input 2:

A = [2, 1]


Example Output

Output 1:

 4
Output 2:

 2


Example Explanation

Explanation 1:

 The set of consecutive elements will be [1, 2, 3, 4].
Explanation 2:

 The set of consecutive elements will be [1, 2].
     */
    /*
    Brute force approach is to sort the array and check for consecutive elements
    from the start of the array. Restart the count when there is a gap.
    keep track of the length and return the max length. This will be nlogn solution.

    Using Hashmap this can be solved in O(n)
    insert all the elements of the array in HashSet.
    Then iterate through the hashmap and check if the current value -1 exists in the hashmap.
    this is to find the beginning element of the subsequence.
         */
    public int longestConsecutive(final int[] A) {

        int result = 0;
        HashSet<Integer> hm = new HashSet<>();

        for(int i = 0; i<A.length;i++)
            hm.add(A[i]);

        for(int i= 0; i< A.length;i++){
            // if there is no prior element, then currentElement can be considered as
            // the start element.
            if(!hm.contains(A[i] - 1)){
                int currentElement = A[i];
                while(hm.contains(currentElement))
                    currentElement++;
                // result will be the difference between the current element and the start element
                // which we considered as A[i];
                result = Math.max(result,currentElement-A[i]);
/*                if(result < currentElement-A[i])
                    result = currentElement - A[i];*/
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LongestConsecutiveSubSequence lcs = new LongestConsecutiveSubSequence();
        lcs.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
    }
}
