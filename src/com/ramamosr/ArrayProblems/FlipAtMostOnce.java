package com.ramamosr.ArrayProblems;

import java.util.ArrayList;

public class FlipAtMostOnce {

    /*
    Flip
Problem Description

You are given a binary string A(i.e. with characters 0 and 1) consisting of characters A1, A2, ..., AN. In a single operation, you can choose two indices L and R such that 1 ≤ L ≤ R ≤ N and flip the characters AL, AL+1, ..., AR. By flipping, we mean change character 0 to 1 and vice-versa.

Your aim is to perform ATMOST one operation such that in final string number of 1s is maximised.

If you don't want to perform the operation, return an empty array. Else, return an array consisting of two elements denoting L and R. If there are multiple solutions, return the lexicographically smallest pair of L and R.

NOTE: Pair (a, b) is lexicographically smaller than pair (c, d) if a < c or, if a == c and b < d.



Problem Constraints
1 <= size of string <= 100000



Input Format
First and only argument is a string A.



Output Format
Return an array of integers denoting the answer.



Example Input
Input 1:

A = "010"
Input 2:

A = "111"


Example Output
Output 1:

[1, 1]
Output 2:

[]


Example Explanation
Explanation 1:

A = "010"

Pair of [L, R] | Final string
_______________|_____________
[1 1]          | "110"
[1 2]          | "100"
[1 3]          | "101"
[2 2]          | "000"
[2 3]          | "001"

We see that two pairs [1, 1] and [1, 3] give same number of 1s in final string. So, we return [1, 1].
Explanation 2:

No operation can give us more than three 1s in final string. So, we return empty array [].
     */

    public int[] flip(String A) {
       if(A.length()<=1){
            return new int[0];
        }
        // first convert the string to int [];

        int[] arr = new int[A.length()];
        int totalsum = 0;
        for(int i=0; i<A.length();i++){
            // take the character at ith position and reduce by the ascii value of 0.
            // the string contains only 0 or 1.
            arr[i] = A.charAt(i) - '0';
            totalsum += arr[i];
        }
        int startIndex = 0, endIndex = 0, currSum = 0, maxSum = 0,left = 0,right=0;

        for(int i= 0; i<arr.length;i++){

            // switch all 1s to -1s and 0s to +1s. because when flipped, 0 becomes 1 and 1 becomes 0
            // and thus 0 has a positive effect and 1 has a negative effect.

            if(arr[i]==1)
                currSum += -1;
            else if(arr[i]==0)
                currSum+=1;

            if(currSum <0){
                currSum = 0;
                startIndex = i+1;
            }
            else if(currSum > 0){
                endIndex = i;
            }
            if(currSum > maxSum) {
                maxSum = currSum;
                left = startIndex;
                right = endIndex;
            }

        }
        if(maxSum > 0)
            return new int[]{left+1,right +1};
        else
            return  new int[0];
    }

    public ArrayList<Integer> flipScaler(String A) {
        int cur = 0;
        int maxx = 0, l = 0, r = 0;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ans.add(-1);
        ans.add(-1);
        for(int i = 0 ; i < A.length() ; i++){
            if(A.charAt(i)=='1')
                cur--;
            else cur++;
            if(cur > maxx){
                ans.set(0, l+1);
                ans.set(1, r+1);
                maxx = cur;
            }
            if(cur < 0) {
                cur = 0;
                l = i+1;
                r = i+1;
            }
            else r++;
        }
        if(maxx == 0){
            return new ArrayList<Integer>();
        }
        else return ans;
    }
    public static void main(String[] args) {
        FlipAtMostOnce fmo = new FlipAtMostOnce();
        fmo.flip( "011");
    }
}
