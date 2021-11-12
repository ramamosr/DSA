package com.ramamosr;

import java.util.HashMap;

public class PermutationsOfAInB {
    /*
    Permutations of A in B
Problem Description

You are given two strings A and B of size N and M respectively.
You have to find the count of all permutations of A present in B as a substring. You can assume a string will have only lowercase letters.
Problem Constraints
1 <= N < M <= 105
Input Format
Given two argument, A and B of type String.
Output Format
Return a single Integer, i.e number of permutations of A present in B as a substring.
Example Input
Input 1:

 A = "abc"
 B = "abcbacabc"
Input 2:

 A = "aca"
 B = "acaa"
Example Output
Output 1:

 5
Output 2:

 2
Example Explanation
Explanation 1:

 Permutations of A that are present in B as substring are:
    1. abc
    2. cba
    3. bac
    4. cab
    5. abc
    So ans is 5.
Explanation 2:

 Permutations of A that are present in B as substring are:
    1. aca
    2. caa
     */
    public static int totalChars = 256;
    public int solve(String A, String B) {
        int result = 0;
        char[] aChars = new char[totalChars];
        char[] bChars = new char[totalChars];

        // Get the list of characters in String A and the count of individual character.
        // Do the same for the first set of characters in String B  - same length as String A.

        for(int i =0; i< A.length();i++){
            aChars[A.charAt(i)]++;
            bChars[B.charAt(i)]++;
        }

        // loop for the rest of the string B.
        //compare the count of letters in compare string A and given String B.
        // if the counts match they are equal. if they dont then they are not.
        // add the next letter to the B char array count. and remove the first one.
        // moving on to the next position in the given string. remove the first added one and add the next one.
        for(int j =A.length(); j<B.length();j++){

            if(checkArrays(bChars,aChars))
                result++;

            (bChars[B.charAt(j)])++;
            (bChars[B.charAt(j-A.length())])--;
        }
        // do the comparison for the last letter as well.
        if(checkArrays(bChars,aChars))
            result++;

        return result;
    }

    public boolean checkArrays(char[] source, char[] compare){

        for(int i =0; i <totalChars;i++){
            if(source[i] != compare[i])
               return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String A = "abc";
        String B = "abcbacabc";

        PermutationsOfAInB perm = new PermutationsOfAInB();
        System.out.println(perm.solve(A,B));


    }
}
