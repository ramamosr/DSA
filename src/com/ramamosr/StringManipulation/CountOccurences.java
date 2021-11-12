package com.ramamosr.StringManipulation;

public class CountOccurences {
    /*
    Count Occurrences
Problem Description

Find number of occurrences of bob in the string A consisting of lowercase english alphabets.



Problem Constraints
1 <= |A| <= 1000


Input Format
The first and only argument contains the string A consisting of lowercase english alphabets.


Output Format
Return an integer containing the answer.


Example Input
Input 1:

  "abobc"
Input 2:

  "bobob"


Example Output
Output 1:

  1
Output 2:

  2


Example Explanation
Explanation 1:

  The only occurrence is at second position.
Explanation 2:

  Bob occures at first and third position.
     */
    public int solve(String A) {
        String toCompare = "bob";
        int count = 0;
        for(int i =0; i<A.length()-2;i++){
            if(A.substring(i,i+3).compareTo(toCompare)==0){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        CountOccurences co = new CountOccurences();
        System.out.println(co.solve("abobob"));
    }
}
