package com.ramamosr.StringManipulation;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestCommonPrefix {
    /*
     Get on call with TA  Ask for Help
Bookmark

bookmark-empty
Longest Common Prefix
Problem Description

Given the array of strings A, you need to find the longest string S which is the prefix of ALL the strings in the array.

Longest common prefix for a pair of strings S1 and S2 is the longest string S which is the prefix of both S1 and S2.

For Example: longest common prefix of "abcdefgh" and "abcefgh" is "abc".



Problem Constraints
0 <= sum of length of all strings <= 1000000



Input Format
The only argument given is an array of strings A.



Output Format
Return the longest common prefix of all strings in A.



Example Input
Input 1:

A = ["abcdefgh", "aefghijk", "abcefgh"]
Input 2:

A = ["abab", "ab", "abcd"];


Example Output
Output 1:

"a"
Output 2:

"ab"


Example Explanation
Explanation 1:

Longest common prefix of all the strings is "a".
Explanation 2:

Longest common prefix of all the strings is "ab".
     */
    public String solve(String[] A){
        if(A.length==0)
            return "";
        if(A.length==1)
            return A[0];
        Arrays.sort(A);

        int len = Math.min(A[0].length(),A[A.length-1].length());
        int i =0;
        while(i < len && A[0].charAt(i)==A[A.length-1].charAt(i))
            i++;
        return A[0].substring(0,i);
    }
    public String longestCommonPrefix(ArrayList < String > A) {
        if (A.size() == 0)
            return "";
        String str;
        String res = "";
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.size(); i++) {
            min = Math.min(min, A.get(i).length());
        }
        for (int i = 0; i < min; i++) {
            char c = A.get(0).charAt(i);
            // check if c character is same in every string or not
            for (int j = 1; j < A.size(); j++) {

                if (c != A.get(j).charAt(i))
                    return res;
            }
            res += c;
        }
        return res;
    }

    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        System.out.println(lcp.solve(new String[] {"abab", "ab", "abcd"}));
    }
}
