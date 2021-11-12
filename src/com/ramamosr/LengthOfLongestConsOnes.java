package com.ramamosr;

public class LengthOfLongestConsOnes {
    /*
    Length of longest consecutive ones
Given a binary string A. It is allowed to do at most one swap between any 0 and 1. Find and return the length of the longest consecutive 1’s that can be achieved.


Input Format

The only argument given is string A.
Output Format

Return the length of the longest consecutive 1’s that can be achieved.
Constraints

1 <= length of string <= 1000000
A contains only characters 0 and 1.
For Example

Input 1:
    A = "111000"
Output 1:
    3

Input 2:
    A = "111011101"
Output 2:
    7
     */

    public int solve(String A){
        int[] left = new int[A.length()];
        int[] right = new int[A.length()];
        int oneCount = 0;
        int maxCount = 0,tmp = 0;

        // maxCount will indicate the most number of continuous ones without swapping.
        // use this for final comparison after swapping.
        for(int cnt = 0; cnt < A.length();cnt++){
            if(A.charAt(cnt)=='1') {
                oneCount++;
                tmp++;
            }
            else{
                maxCount = Math.max(maxCount,tmp);
                tmp = 0;
            }
        }
        maxCount = Math.max(maxCount,tmp);

        if(oneCount <=1){
            return oneCount;
        }
        if(A.charAt(0)=='1')
            left[0] = 1;
        else
            left[0] = 0;

        if(A.charAt(A.length()-1)=='1')
            right[A.length()-1] = 1;
        else
            right[A.length()-1] = 0;
        // Find all ones to the left of the digit including the current one.
        // if the current digit is also 1, add 1 to the value of the prev
        // if the current is 0, then reset to 0.

        for(int i =1; i<A.length();i++){
            if(A.charAt(i)=='1'){
                left[i] = left[i-1] + 1;
            }
            else
                left[i] = 0;
        }
        // do the above, but this time from the right side.
        for(int j =A.length()-2; j>=0;j--){
            if(A.charAt(j)=='1'){
                right[j] = right[j+1] + 1;
            }
            else
                right[j] = 0;
        }
        // loop starts from 1st element and ends are last but 2
        // to avert the index out of range exceptions.
        // thats the reason we take the maxCount earlier without swapping.
        for(int k = 1; k<A.length()-1;k++){
            int result = 0;
            if(A.charAt(k)=='0') {
                result = left[k - 1] + right[k + 1] + 1;
                maxCount = Math.max(maxCount,result);
            }
        }
        if (maxCount > oneCount)
            maxCount--;
        return maxCount;
    }
    public static void main(String[] args) {
        LengthOfLongestConsOnes llo = new LengthOfLongestConsOnes();
        System.out.println(llo.solve("01110"));
    }
}
