package com.ramamosr.StringManipulation;

public class MinWindowString {
    /*
    Problem Description

Given a string A and a string B, find the window with minimum length in A which will contain all the characters in B in linear time complexity.
Note that when the count of a character c in B is x, then the count of c in minimum window in A should be at least x.

Note:

If there is no such window in A that covers all characters in B, return the empty string.
If there are multiple such windows, return the first occurring minimum window ( with minimum start index )


Problem Constraints

1 <= size(A), size(B) <= 106



Input Format

First argument is a string A.
Second argument is a string B.



Output Format

Return a string denoting the minimum window.



Example Input

Input 1:

 A = "ADOBECODEBANC"
 B = "ABC"
Input 2:

 A = "Aa91b"
 B = "ab"


Example Output

Output 1:

 "BANC"
Output 2:

 "a91b"


Example Explanation

Explanation 1:

 "BANC" is a substring of A which contains all characters of B.
Explanation 2:

 "a91b" is the substring of A which contains all characters of B.
     */

    public String minWindow(String A, String B) {

        if(A.length() < B.length()) return "";
        int[] freqA = new int[256];
        int[] freqB = new int[256];
        // Fill the frequency array for the elements in String B.
        for( int i=0; i<B.length();i++){
            freqB[B.charAt(i)]++;
        }

        int start = 0;
        int startIndex = -1; // start index of the result substring.
        int minWindowLength = Integer.MAX_VALUE;
        // Counter to check if the number of characters in B are matching in the
        // characters in A.
        int matchingCount = 0;

        for(int j =0; j<A.length();j++){
            freqA[A.charAt(j)]++;

            if(freqB[A.charAt(j)] !=0 && freqA[A.charAt(j)]<= freqB[A.charAt(j)]) {
                matchingCount++;
            }
            // check if count is equal to length of B string
            // if it does it means we have found all the characters of string B in string A
            if(matchingCount==B.length()){
                //Now decide move the start pointer. The start pointer is at 0 now.
                // if the freqA count is greater than freqB or if the freqB count is 0,
                // we will have to reduce the freqA, as we have one character in excess of what we need.
                while((freqA[A.charAt(start)]>freqB[A.charAt(start)]) || freqB[A.charAt(start)]==0){
                    if(freqA[A.charAt(start)]>freqB[A.charAt(start)]){
                        freqA[A.charAt(start)]--;
                    }
                    start++;
                }
                int temp = j - start + 1;
                if (temp < minWindowLength ){
                    minWindowLength = temp;
                    startIndex = start;
                }
            }
        }

        if(startIndex == -1)
            return "";

        return A.substring(startIndex, startIndex + minWindowLength);

    }

    public static void main(String[] args) {
        String A = "ABCD";
        String B = "ABC";
        MinWindowString mws = new MinWindowString();
        System.out.println(mws.minWindow(A,B));
    }
}
