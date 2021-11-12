package com.ramamosr.StringManipulation;

import java.util.Arrays;
import java.util.Comparator;

public class LexicographicallyLargest {
    /*
    Lexicographically Largest
You are given a string S. You want to change it to the lexicographically largest possible string by changing some of its characters.
 But you can only use characters of the string T as a replacement for characters of S. Formally, find the lexicographically largest string you can form by replacing some(or none) of the characters of S by using only the characters of string T. Note: Each character of T can be used at the most once.

Constraints:

1.   1 <= Length of S and T <= 50
2.   All the alphabets of S and T are lower case (a - z)
Input: A string A containing S and T separated by "_" character. (See example below)

Output: Lexicographically largest string P that can be formed by changing some or (none) characters of S using the characters of T.

Example:

Input:

A : "abb_c"
This implies S is "abb" and T is "c".

Output:

P = "cbb"
     */
    /*
    We will apply a greedy approach.
This is how.
We have hashed all the characters of string T with their counts, so we know which character can be used how many number of times.
We iterate over the string S and find the biggest character greater than the current character of S and replace it. We also decrease the
count of that character in the hash as it has been used.
If no such character is found, we let the original S[i] be as it is.
Once we are done traversing the string S, we have our output.
     */
    public String getLargest(String A) {
        String[] sourceArr = A.split("[_]");
        String source = sourceArr[0];
        String replace = sourceArr[1];
        int freq[] = new int[26];
        for(int i =0; i<replace.length();i++){
            freq[(replace.charAt(i))-'a']++;
        }
        int j = 0;
        for(int i= 25; i>=0;i--){
            int count = freq[i];
            if(count > 0){
                while(j<source.length() && count >0){
                    if(source.charAt(j) < (char)(i + 'a')){
                        source = source.replaceFirst("["+source.charAt(j)+"]", Character.toString((char) ('a' + i)));
                        count--;
                    }
                    j++;
                    if(count==0) break;
                }
            }
        }
        return source;
    }

    public static void main(String[] args) {
        LexicographicallyLargest ll = new LexicographicallyLargest();
        System.out.println(ll.getLargest("ittmcsvmoa_jktvvblefw"));
    }
}
