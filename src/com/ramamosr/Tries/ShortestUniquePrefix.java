package com.ramamosr.Tries;

/*
Problem Description

Given a list of N words. Find shortest unique prefix to represent each word in the list.

NOTE: Assume that no word is prefix of another. In other words, the representation is always possible



Problem Constraints

1 <= Sum of length of all words <= 106



Input Format

First and only argument is a string array of size N.



Output Format

Return a string array B where B[i] denotes the shortest unique prefix to represent the ith word.



Example Input

Input 1:

 A = ["zebra", "dog", "duck", "dove"]
Input 2:

A = ["apple", "ball", "cat"]


Example Output

Output 1:

 ["z", "dog", "du", "dov"]
Output 2:

 ["a", "b", "c"]


Example Explanation

Explanation 1:

 Shortest unique prefix of each word is:
 For word "zebra", we can only use "z" as "z" is not any prefix of any other word given.
 For word "dog", we have to use "dog" as "d" and "do" are prefixes of "dov".
 For word "du", we have to use "du" as "d" is prefix of "dov" and "dog".
 For word "dov", we have to use "dov" as "d" and do" are prefixes of "dog".

Explanation 2:

 "a", "b" and c" are not prefixes of any other word. So, we can use of first letter of each to represent.
 */

public class ShortestUniquePrefix {

    static final int ALPHABET_SIZE = 26;

    static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;
        //count of words this letter can make
        int count;

        TrieNode(){
            isEndOfWord = false;
            count=0;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;

        }
    };

    static TrieNode root;

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    static void insert(String key)
    {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null){
                pCrawl.children[index] = new TrieNode();}

            pCrawl = pCrawl.children[index];
            pCrawl.count++;
        }

        // mark last node as leaf
        pCrawl.isEndOfWord = true;

    }

    static String getPrefix(String key)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;
        String ans="";

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (pCrawl.count==1){
                return ans;
            }
            else{
                ans = ans + key.charAt(level) + "";
                pCrawl = pCrawl.children[index];
            }


        }

        return (ans);
    }

    public String[] prefix(String[] A) {

        String pref[] = new String[A.length];
        root = new TrieNode();

        for(int i = 0; i < A.length; i++) insert(A[i]);

        for (int i = 0; i < A.length ; i++){

            pref[i] = getPrefix(A[i]);
        }

        return pref;

    }

}
