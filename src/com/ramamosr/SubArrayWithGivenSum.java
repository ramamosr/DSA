
package com.ramamosr;

        import java.util.Arrays;
        import java.util.HashMap;

public class    SubArrayWithGivenSum {
/*
Subarray with given sum
Problem Description

Given an array of positive integers A and an integer B, find and return first continuous subarray which adds to B.

If the answer does not exist return an array with a single element "-1".

First sub-array means the sub-array for which starting index in minimum.



Problem Constraints
1 <= length of the array <= 100000
1 <= A[i] <= 109
1 <= B <= 109



Input Format
The first argument given is the integer array A.

The second argument given is integer B.



Output Format
Return the first continuous sub-array which adds to B and if the answer does not exist return an array with a single element "-1".



Example Input
Input 1:

 A = [1, 2, 3, 4, 5]
 B = 5
Input 2:

 A = [5, 10, 20, 100, 105]
 B = 110


Example Output
Output 1:

 [2, 3]
Output 2:

 -1


Example Explanation
Explanation 1:

 [2, 3] sums up to 5.
Explanation 2:

 No subarray sums up to required number.
 */

    public int[] solve(int[] A, int B) {
        int startIndex = 0;
        int endIndex = -1;
        HashMap<Long,Integer> hm = new HashMap<Long,Integer>();
        long total = 0;
        for(int i = 0; i <A.length; i++){
            total = total + A[i];
            if(total-B ==0){
                startIndex = 0;
                endIndex = i;
                break;
            }
            if(hm.containsKey(total-B)){
                startIndex = hm.get(total-B) + 1;
                endIndex = i;
                break;
            }
            hm.put(total,i);

        }
        int[] result;
        if(endIndex == -1)
            result = new int[] {-1};
        else
           result =  Arrays.copyOfRange(A,startIndex,endIndex+1);

        return result;
    }

    public static void main(String[] args) {

        SubArrayWithGivenSum sub = new SubArrayWithGivenSum();
        System.out.println(sub.solve(new int[]{15, 2, 48, 19, 28, 22, 44, 2, 32, 46, 46, 24, 1, 23, 49, 26, 23, 17, 17, 46, 4, 30, 40, 36, 20, 5 },82));
    }
}
