package com.ramamosr;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.HashMap;

public class AlienDictionary {

    /*
    Is Dictionary?
Problem Description

In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given an array of words A of size N written in the alien language, and the order of the alphabet denoted by string B of size 26, return 1 if and only if the given words are sorted lexicographicaly in this alien language else return 0.



Problem Constraints
1 <= N, length of each word <= 105

Sum of length of all words <= 2 * 106



Input Format
First argument is a string array A of size N.

Second argument is a string B of size 26 denoting the order.



Output Format
Return 1 if and only if the given words are sorted lexicographicaly in this alien language else return 0.



Example Input
Input 1:

 A = ["hello", "scaler", "interviewbit"]
 B = "adhbcfegskjlponmirqtxwuvzy"
Input 2:

 A = ["fine", "none", "no"]
 B = "qwertyuiopasdfghjklzxcvbnm"


Example Output
Output 1:

 1
Output 2:

 0


Example Explanation
Explanation 1:

 The order shown in string B is: h < s < i for the given words. So return 1.
Explanation 2:

 "none" should be present after "no". Return 0.
     */

    public int solve(String[] A, String B) {

        if(A.length ==1) return 1;
        HashMap<Character,Integer> hm = new HashMap<>();
        for(int i=0;i < B.length();i++)
            hm.put(B.charAt(i),i);
        for(int j = 1; j<A.length;j++){
            String prevWord = A[j-1];
            String currWord = A[j];
            for(int k =0; k<prevWord.length();k++){
                if(k==currWord.length()) return 0;
                if(prevWord.charAt(k) != currWord.charAt(k)) {
                    if (hm.get(prevWord.charAt(k)) < hm.get(currWord.charAt(k)))
                        break;
                    if (hm.get(prevWord.charAt(k)) > hm.get(currWord.charAt(k)))
                        return 0;
                }
            }
        }
        return 1;

    }

    public static void main(String[] args) {
    String[] str = new String[]{"first","no", "none"};
    String B = "qwertyuiopasdfghjklzxcvbnm";

    AlienDictionary ad = new AlienDictionary();
    System.out.println(ad.solve(str,B));
    }
}
