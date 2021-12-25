package com.ramamosr.Hashing;

import java.util.HashMap;

public class ShaggyAndDistances {
/*
Problem Description
Shaggy has an array A consisting of N elements. We call a pair of distinct indices in that array as a special pair if elements at that index in the array are equal.
Shaggy wants you to find a special pair such that distance between that pair is minimum. Distance between two indices is defined as |i-j|. If there is no special pair in the array then return -1.

Problem Constraints
1 <= |A| <= 105
Input Format
First and only argument is the array A.
Output Format
Return one integer corresponding to the minimum possible distance between a special pair.
Example Input
Input 1:
A = [7, 1, 3, 4, 1, 7]
Input 2:
A = [1, 1]
Example Output
Output 1:
 3
Output 2:
 1
Example Explanation
Explanation 1:
Here we have 2 options:
1. A[1] and A[4] are both 1 so (1,4) is a special pair and |1-4|=3.
2. A[0] and A[5] are both 7 so (0,5) is a special pair and |0-5|=5.
Therefore the minimum possible distance is 3.
Explanation 2:
Only possibility is choosing A[1] and A[2].
 */
    /*
    Solution Approach
    Loop through the array. Add the elements as key and index as value to the hashmap.
    while adding the elements check if the element exists already.
    if it exists, calculate the abs distance between the indexes and store as answer.
    update the index to the latest index. (last seen index).
     */
    public int solve(int[] A) {
        // key is the element A[i]. value is the last seen index i;
        HashMap<Integer,Integer> hm = new HashMap<>();
        int result = Integer.MAX_VALUE;
        for(int i = 0; i<A.length;i++ ){
            //check if the value exists in the array.
            if(hm.containsKey(A[i])){
                result = Math.min(result,Math.abs(hm.get(A[i]) - i));
            }
                hm.put(A[i],i);
        }
        if(result==Integer.MAX_VALUE)
            return -1;
        else
            return result;
    }

    public static void main(String[] args) {
        ShaggyAndDistances sad = new ShaggyAndDistances();
        System.out.println(sad.solve(new int[]{7, 1, 3, 4, 1, 7}));
    }

}
