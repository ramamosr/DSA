package com.ramamosr.StringManipulation;

import java.util.Arrays;

public class BoringSubstring {
    /*
    Problem Description
Given a string A of lowercase English alphabets. Rearrange the characters of the given string A such that there is no boring substring in A.
A boring substring has the following properties:
Its length is 2.
Both the characters are consecutive, for example - "ab", "cd", "dc", "zy" etc.(If the first character is C then the next character can be either (C+1) or (C-1)).
Return 1 if it possible to rearrange the letters of A such that there are no boring substring in A, else return 0.
Problem Constraints
1 <= |A| <= 105
Input Format
The only argument given is string A.
Output Format
Return 1 if it possible to rearrange the letters of A such that there are no boring substring in A, else return 0.
Example Input
Input 1:
 A ="abcd"
Input 2:
 A = "aab"
Example Output
Output 1:
 1
Output 2:
0
Example Explanation
Explanation 1:
 String A can be rearranged into "cadb" or "bdac"
Explanation 2:
 No arrangement of string A can make it free of boring substrings.
 */
    /*
    No specific knowledge is requires to solve this question you just need to observe and find an existing pattern hidden in the parities of ASCII value of characters.

‘a’ must be present near ‘c’ ,similarly ‘c’ must be near ‘e’ as we can see odd characters can be put aside each other and there will be no boring substring in it.

Like: “acegik…” No boring substring present in this string.

Similarly for even characters.

Now just traverse in the string and form two strings one containing the odd characters and other even characters.
Sort both of them and check if placing them together doesn’t make a boring substring at their join point.

For example:
A = “abcdefg”
So ,
odd = “aceg”
even= “bdf”

Check the string s= odd+even or s=even+odd doesn’t contain any boring substring.

Time Complexity : O(	A	)
     */

    public int solve(String A) {

        String even="";
        String odd="";
        //A = SortString(A);
        for(int i=0;i < A.length();i++){
            char c = A.charAt(i);
            if(c%2==0)
                even+=c;
            else
                odd+=c;
        }

        even = SortString(even);
        odd = SortString(odd);

        if(CheckBoring(odd,even) || CheckBoring(even,odd))
            return 1;
        else
            return 0;

    }

    public boolean CheckBoring(String first,String second){
        if(Math.abs(first.charAt(first.length()-1) - second.charAt(0))==1)
            return false;
        else
            return true;
    }

    public String SortString(String input){
        char[] temp = input.toCharArray();
        Arrays.sort(temp);
        return String.valueOf(temp);
    }
    public static void main(String[] args) {
        BoringSubstring bs = new BoringSubstring();
        System.out.println(bs.solve("abcd"));
    }
}
