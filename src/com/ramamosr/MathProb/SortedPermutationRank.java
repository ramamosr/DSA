package com.ramamosr.MathProb;

import java.util.Arrays;

public class SortedPermutationRank {
    /*
    Sorted Permutation Rank
Problem Description

Given a string A. Find the rank of the string amongst its permutations sorted lexicographically.
Assume that no characters are repeated.

Note: The answer might not fit in an integer, so return your answer % 1000003



Problem Constraints
1 <= |A| <= 1000



Input Format
First argument is a string A.



Output Format
Return an integer denoting the rank of the given string.



Example Input
Input 1:

A = "acb"
Input 2:

A = "a"


Example Output
Output 1:

2
Output 2:

1


Example Explanation
Explanation 1:

Given A = "acb".
The order permutations with letters 'a', 'c', and 'b' :
abc
acb
bac
bca
cab
cba
So, the rank of A is 2.
Explanation 2:

Given A = "a".
Rank is clearly 1.
     */
    //Approach:
    /*
    Copy and sort the array. Find the number of elements before the starting element of the given array.
    if the given string is CBA after sorting it will become ABC. A&B are lesser than C.
     A can form 2! of words, similarly B can form 2! of letters.

     */
    int[] factArr = new int[52];
    int mod = 1000003;
    public void populateFactorial(){
        factArr[0] = 0;
        factArr[1] = 1;
        for(int i = 2;i <factArr.length;i++){
            factArr[i] = ((i%mod) * (factArr[i-1]%mod)%mod);
        }
    }

    public int findRank(String A) {
        int rank = 1;
        String B = A;
        int len = A.length();
        int[] rankArr = new int[len];
        populateFactorial();
        char[] bArr = B.toCharArray();
        for(int i = 0; i < len;i++){
            for(int j=i+1; j< len;j++){
                if(bArr[i]>bArr[j])
                    rankArr[i]++;
            }
        }
        int[] result = new int[len];
        int count = 0;
        for(int j = len-1; j>=0;j--){
            result[j] = (rankArr[j] * factArr[count])%mod;
            rank += result[j]%mod;
            count++;
        }
        return rank%mod;
    }

    public static void main(String[] args) {
        SortedPermutationRank spr = new SortedPermutationRank();
        System.out.println(spr.findRank("DTNGJPURFHYEW"));
    }
}
