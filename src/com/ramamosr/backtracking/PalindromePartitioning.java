package com.ramamosr.backtracking;

import java.util.ArrayList;

public class PalindromePartitioning {
    /*
    Problem Description

Given a string A, partition A such that every string of the partition is a palindrome.

Return all possible palindrome partitioning of A.

Ordering the results in the answer : Entry i will come before Entry j if :
len(Entryi[0]) < len(Entryj[0]) OR
(len(Entryi[0]) == len(Entryj[0]) AND len(Entryi[1]) < len(Entryj[1])) OR * *
(len(Entryi[0]) == len(Entryj[0]) AND ... len(Entryi[k] < len(Entryj[k]))


Problem Constraints
1 <= len(A) <= 15



Input Format
First argument is a string A of lowercase characters.



Output Format
Return a list of all possible palindrome partitioning of s.



Example Input
Input 1:

A = "aab"
Input 2:

A = "a"


Example Output
Output 1:

 [
    ["a","a","b"]
    ["aa","b"],
  ]
Output 2:

 [
    ["a"]
  ]


Example Explanation*
Explanation 1:

In the given example, ["a", "a", "b"] comes before ["aa", "b"] because len("a") < len("aa").
Explanation 2:

In the given example, only partition possible is "a" .
     */
    ArrayList<ArrayList<String>> result = new ArrayList<>();

    public void findPartition(String A, int index,ArrayList<String> partString){

        //base case if the index reaches the length of the partString add it to result set
        if(index == A.length()) {
            result.add(new ArrayList<>(partString));
            return;
        }

        // from the current index through the length of the string
        // check if the substring is palindrome
        // add if the substring is palindrome to the temp list
        // then do recursion from next index and clear the last added element
        // from the list for backtracking.
        for(int i= index; i<A.length();i++){
            if(checkPalindrome(A,index,i)){
                partString.add(A.substring(index,i+1));
                findPartition(A,i+1,partString);
                partString.remove(partString.size()-1);
            }
        }

    }

    public ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<String> partString = new ArrayList<>();
        findPartition(a,0,partString);
        return result;
    }

    public boolean checkPalindrome(String A, int start, int end){
        if(start>=end) return true;
        else
            return ((A.charAt(start) == A.charAt(end)) && checkPalindrome(A, ++start, --end));
    }
    public static void main(String[] args) {
        String a = "aab";
        PalindromePartitioning pp = new PalindromePartitioning();
        pp.partition(a);
    }
}
