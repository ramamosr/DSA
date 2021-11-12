package com.ramamosr.backtracking;

import java.util.ArrayList;

/*
Again, think recursion.
At every step, you have 2 options :
1) Add ‘(‘ to the string.
2) Add ‘)’ to the string.
However, you need to make sure, that the number of closing brackets do not exceed number of opening brackets at any point of time.
Also, make sure that the number of opening brackets never exceeds n.
 */
public class GenerateParanthesisScaler {
    public class Solution {
        ArrayList < String > ans;
        // cnt denotes remaining open brackets (
        //diff denotes the difference between open and closed brackets
        void solve(int cnt, int dif, int n, String s) {
            if (n == 0) {
                ans.add(s);
                return;
            }
            if (cnt > 0)
                solve(cnt - 1, dif + 1, n - 1, s + '(');
            if (dif > 0)
                solve(cnt, dif - 1, n - 1, s + ')');
        }
        public ArrayList< String > generateParenthesis(int A) {
            ans = new ArrayList < > ();
            solve(A, 0, 2 * A, "");
            return ans;
        }
    }
}
