package com.ramamosr.Tries;

import java.util.HashMap;
import java.util.ArrayList;

public class SpellChecker {

    /*
    Given an array of words A (dictionary) and another array B (which contain some words).

You have to return the binary array (of length |B|) as the answer where 1 denotes that the word is present in the dictionary and 0 denotes it is not present.

Formally, for each word in B, you need to return 1 if it is present in Dictionary and 0 if it is not.

Such problems can be seen in real life when we work on any online editor (like Google Documnet), if the word is not valid it is underlined by a red line.

NOTE: Try to do this in O(n) time complexity.



Problem Constraints
1 <= |A| <= 1000

1 <= sum of all strings in A <= 50000

1 <= |B| <= 1000



Input Format
First argument is array of strings A.

First argument is array of strings B.



Output Format
Return the binary array of integers according to the given format.



Example Input
Input 1:

A = [ "hat", "cat", "rat" ]
B = [ "cat", "ball" ]
Input 2:

A = [ "tape", "bcci" ]
B = [ "table", "cci" ]


Example Output
Output 1:

[1, 0]
Output 2:

[0, 0]


Example Explanation
Explanation 1:

Only "cat" is present in the dictionary.
Explanation 2:

None of the words are present in the dictionary.
     */
    /* SOLUTION APPROACH
    To this in O(n), you will need a different data structure called tries.
You can insert all dictionary strings in a trie and then try finding all
given strings in the trie, each of those will take O(length) time.
Thus you can do this without the additional log factor you
get while working with sets.
     */
    static final int ALPHABET_SIZE = 26;
    static TrieNode root;
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;
        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };
    public int[] solve(String[] A, String[] B) {
        root = new TrieNode();
        for (String temp: A)
            insert(temp);
        int[] ans = new int[B.length];
        for (int i = 0; i < B.length; i++) {
            if (search(B[i]) == true)
                ans[i] = 1;
            else ans[i] = 0;
        }
        return ans;
    }
    static void insert(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;
        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();
            pCrawl = pCrawl.children[index];
        }
        pCrawl.isEndOfWord = true;
    }
    static boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;
        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                return false;
            pCrawl = pCrawl.children[index];
        }
        return (pCrawl != null && pCrawl.isEndOfWord);
    }

    public ArrayList<Integer> solveWithHashMap(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<Integer> list=new ArrayList<>();
        HashMap<String,Integer> map=new HashMap<>();
        for(int i=0;i<A.size();i++){
            map.put(A.get(i),map.getOrDefault((A.get(i)+1),1));
        }
        for(int j=0;j<B.size();j++){
            if(map.containsKey(B.get(j))){
                if(map.get(B.get(j))>0) {
                    list.add(1);
                    map.put(B.get(j), map.get(B.get(j)) - 1);
                }
            }
            else{
                list.add(0);
            }
        }
        return list;
    }
    public static void main(String[] args) {
        SpellChecker sc = new SpellChecker();
        String[] A = {"hat", "cat", "rat"};
        String[] B = {"cat", "ball" };
        sc.solve(A,B);
    }
}
