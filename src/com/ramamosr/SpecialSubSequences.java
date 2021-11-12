package com.ramamosr;

import java.util.Locale;

public class SpecialSubSequences {
    /*
    Special Subsequences "AG"
Problem Description

You have given a string A having Uppercase English letters.

You have to find that how many times subsequence "AG" is there in the given string.

NOTE: Return the answer modulo 109 + 7 as the answer can be very large.



Problem Constraints
1 <= length(A) <= 105



Input Format
First and only argument is a string A.



Output Format
Return an integer denoting the answer.



Example Input
Input 1:

 A = "ABCGAG"
Input 2:

 A = "GAB"


Example Output
Output 1:

 3
Output 2:

 0


Example Explanation
Explanation 1:

 Subsequence "AG" is 3 times in given string
Explanation 2:

 There is no subsequence "AG" in the given string.
     */
    public int solve(String A) {
        int result = 0;
        int counter = 0;
        A = A.toUpperCase();
        String toCompare = "AG";
        char[] ch = toCompare.toCharArray();
        char[] arr = A.toCharArray();
        for(int i =0; i<arr.length;i++){
            if(arr[i] == ch[0]){
                counter++;
            }
            if(arr[i]==ch[1]){
                result = result + counter;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        String A = "b";
        SpecialSubSequences sps = new SpecialSubSequences();
        sps.solve(A);
    }
}
