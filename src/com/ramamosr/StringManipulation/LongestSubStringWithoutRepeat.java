package com.ramamosr.StringManipulation;

import java.util.HashMap;

public class LongestSubStringWithoutRepeat {
    /*
    Problem Description

Given a string A, find the length of the longest substring without repeating characters.

Note: Users are expected to solve in O(N) time complexity.



Problem Constraints

1 <= size(A) <= 106

String consists of lowerCase,upperCase characters and digits are also present in the string A.



Input Format

Single Argument representing string A.



Output Format

Return an integer denoting the maximum possible length of substring without repeating characters.



Example Input

Input 1:

 A = "abcabcbb"
Input 2:

 A = "AaaA"


Example Output

Output 1:

 3
Output 2:

 2


Example Explanation

Explanation 1:

 Substring "abc" is the longest substring without repeating characters in string A.
Explanation 2:

 Substring "Aa" or "aA" is the longest substring without repeating characters in string A.

     */

    public int lengthOfLongestSubstring(String A) {
        if(A.length()<=1) return A.length();

        HashMap<Character,Integer> hm = new HashMap<>();

        int start = 0;
        int minLength = 1;
        for(int i=0; i<A.length();i++){
            if(hm.containsKey(A.charAt(i))){
                // if the char exists in map,
                // move the start element to the last known index of the current element +1
                start = Math.max(start,hm.get(A.charAt(i))+1);
            }
            hm.put(A.charAt(i),i);
            minLength = Math.max(minLength,i-start+1);
        }
        return minLength;
    }

    public static void main(String[] args) {
        LongestSubStringWithoutRepeat lswr = new LongestSubStringWithoutRepeat();
        System.out.println(lswr.lengthOfLongestSubstring("abcad"));
    }
}
