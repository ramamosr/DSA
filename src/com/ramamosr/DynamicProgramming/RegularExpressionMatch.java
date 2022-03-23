package com.ramamosr.DynamicProgramming;

public class RegularExpressionMatch {

    /*
    Problem Description

Implement wildcard pattern matching with support for ' ? ' and ' * ' for strings A and B.

' ? ' : Matches any single character.
' * ' : Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).



Problem Constraints

1 <= length(A), length(B) <= 104



Input Format

The first argument of input contains a string A.
The second argument of input contains a string B.



Output Format

Return 1 if the patterns match else return 0.



Example Input

Input 1:

 A = "aaa"
 B = "a*"
Input 2:

 A = "acz"
 B = "a?a"


Example Output

Output 1:

 1
Output 2:

 0


Example Explanation

Explanation 1:

 Since '*' matches any sequence of characters. Last two 'a' in string A will be match by '*'.
 So, the pattern matches we return 1.
Explanation 2:

 '?' matches any single character. First two character in string A will be match.
 But the last character i.e 'z' != 'a'. Return 0.
     */
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int isMatch(final String A, final String B) {

        // if the pattern string is empty
        // return true if the source string is empty, else return false;
        if(B.length()==0)
            return (A.length()==0?1:0);
        int[][] dp = new int[A.length()+1][B.length()+1];

        // if both string and pattern are empty the value will be 1.
        dp[0][0] = 1;

        // if the given string is empty, then only a * can match the string
        // so for an empty string check if there are any other characters other than
        // * in the pattern. if the pattern has only *, update the value of the current element
        // with the value of the prev elemt.
        for(int j=1;j<=B.length();j++){
            if(B.charAt(j-1)=='*')
                dp[0][j] = dp[0][j-1];
        }

        for(int i =1;i<=A.length();i++){
            for(int j=1;j<=B.length();j++){
                // if the character matches between string and pattern
                // or if the pattern at that index is a '?' return whatever value was
                // there at the prev index;
                if(A.charAt(i-1)==B.charAt(j-1) || B.charAt(j-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                // if the character in the pattern is a '*'
                // take the max value which is either from the the prev element in same row
                // or same column from prev row.
                else if(B.charAt(j-1)=='*'){
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
                else
                    dp[i][j] = 0;
            }
        }
        return dp[A.length()][B.length()];
    }

    public int isMatchOptimized(final String A, final String B) {
        int n = A.length();
        int m = B.length();
        int[] first = new int[m+1];
        int[] second = new int[m+1];

        for(int i=1; i<=m; i++)
        {
            if(B.charAt(i-1) == '*')
                first[i] = 1;
            else
                break;
        }

        first[0] = 1;
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=m; j++)
            {
                if(A.charAt(i-1) == B.charAt(j-1) || B.charAt(j-1) == '?')
                    second[j] = first[j-1];
                else if(B.charAt(j-1) == '*')
                    second[j] = (first[j] == 1 || second[j-1] == 1) ? 1 : 0;
                else
                    second[j] = 0;
            }
            first[0] = 0;
            int[] temp = first;
            first = second;
            second = temp;
        }

        return first[m];
    }

    /*
    Think about the bruteforce solution.

When you encounter ‘*’, you would try to call the same isMatch function in the following manner:

If p[0] == ‘*’, then isMatch(s, p) is true if isMatch(s+1, p) is true OR isMatch(s, p+1) is true.

else if p[0] is not ‘*’ and the characters s[0] and p[0] match ( or p[0] is ‘?’ ), then isMatch(s,p) is true only if isMatch(s+1, p+1) is true.
If the characters don’t match isMatch(s, p) is false.

This appraoch is exponential. Think why.
Lets see how we can make this better. Note that isMatch function can only be called with suffixes of s and p. As such, there could only be length(s) * length(p) unique calls to isMatch. Lets just memoize the result of the calls so we only do processing for unique calls. This makes the time and space complexity O(len(s) * len(p)).

There could be ways of optimizing the approach rejecting certain suffixes without processing them. For example, if len(non star characters in p) > len(s), then we can return false without checking anything.
     */

    public int isMatchScaler(final String s, final String p) {
        boolean[][] d = new boolean[s.length() + 1][p.length() + 1];
        d[0][0] = true;
        if (p.charAt(0) == '*') {
            d[0][1] = true;
        }
        for (int i = 0; i < p.length(); ++i) {
            if (p.charAt(i) == '*') {
                d[0][i + 1] = d[0][i];
                for (int j = 0; j < s.length(); ++j) {
                    d[j + 1][i + 1] = d[j][i] || d[j + 1][i] || d[j][i + 1];
                }
            } else if (p.charAt(i) == '?') {
                for (int j = s.length() - 1; j >= 0; --j) {
                    d[j + 1][i + 1] = d[j][i];
                }
            } else {
                for (int j = 0; j < s.length(); ++j) {
                    if (p.charAt(i) == s.charAt(j)) {
                        d[j + 1][i + 1] = d[j][i];
                    }
                }
            }
        }
        return d[s.length()][p.length()] ? 1 : 0;
    }

    public static void main(String[] args) {
        String A = "aaaaaa";
        String B = "*";
        RegularExpressionMatch rem = new RegularExpressionMatch();
        System.out.println(rem.isMatch(A,B));
    }
}
