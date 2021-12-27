package com.ramamosr.StringManipulation;

public class PeriodOfAString {
    /*
    Problem Description
Given a string A of length N consisting of lowercase alphabets. Find the period of the string.
Period of the string is the minimum value of k (k >= 1), that satisfies A[i] = A[i % k] for all valid i.
Problem Constraints
1 <= N <= 106
Input Format
First and only argument is a string A of length N.
Output Format
Return an integer, denoting the period of the string.
Example Input
Input 1:
 A = "abababab"
Input 2:
 A = "aaaa"
Example Output
Output 1:
 2
Output 2:
 1
Example Explanation
Explanation 1:
 Period of the string will be 2:
 Since, for all i, A[i] = A[i%2].
Explanation 2:
 Period of the string will be 1.
     */
    /*
    A simple approach is to check for all value of K from 1 to n-1, but this will take O(N2).

We can do this in linear time using pattern matching algorithm (Z algorithm).

First we will construct the Z array, i.e. for a string str[0..n-1], Z array is of same length as string. An element Z[i] of Z array stores length of the longest substring starting from str[i] which is also a prefix of str[0..n-1]. The first entry of Z array is meaning less as complete string is always prefix of itself.

This can be used, for any i (1 <= i < n), if i + Z[i] == N , then the period of the string is i.

If there is no such period, then N will be the period.
     */

      public int solve(String A) {
        int result = A.length();
        int len = A.length();
        int[] zArr = PopulateZArray(A);
        for(int i=1; i<zArr.length;i++){
            if(i+zArr[i]==len)
                return i;
            //To optimize further for completely periodic strings
            // with odd or even number of length, check if i the factor
            // of the length of the string.
//            if(i+zArr[i]==len && isFactor(i,len))
//                return i;
        }
        return result;
    }

    public boolean isFactor(int index, int length){
        for (int i=1; i<=Math.sqrt(length); i++)
        {
            if (length%i==0)
            {
                if (i == index)
                    return true;
            }
        }
        return false;
    }

    public int[] PopulateZArray(String A){
        int[] z = new int[A.length()];
        int left = 0, right = 0;
        int n = A.length();
        z[0] = n;

        for(int i=1; i<n;i++){

            if(i>right){
                left = i;
                right = i;
                while(right<n && A.charAt(right)==A.charAt(right-left)){
                    right++;
                }
                z[i] = right - left;
                // since right pointer would have move to the next item, reduce r by 1.
                right--;
            }
            else{
                int j = i - left;
                if(z[j] < right-i+1){
                    z[i] = z[j];
                }
                else{
                    left = i;
                    while(right<n && A.charAt(right)==A.charAt(right-left)){
                        right++;
                    }
                    z[i] = right - left;
                    // since right pointer would have move to the next item, reduce r by 1.
                    right--;

                }
            }
        }
        return z;
    }
    public static void main(String[] args) {
        PeriodOfAString posa = new PeriodOfAString();
        System.out.println(posa.solve("abcaabcaab"));

    }
}
