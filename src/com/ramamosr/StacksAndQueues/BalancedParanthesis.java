package com.ramamosr.StacksAndQueues;

import java.util.Stack;
public class BalancedParanthesis {

    /*
    Problem Description

Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in A.

Refer to the examples for more clarity.



Problem Constraints

1 <= |A| <= 100



Input Format

The first and the only argument of input contains the string A having the paranthesis sequence.



Output Format

Return 0, if the paranthesis sequence is not balanced.

Return 1, if the paranthesis sequence is balanced.



Example Input

Input 1:

 A = {([])}
Input 2:

 A = (){
Input 3:

 A = ()[]


Example Output

Output 1:

 1
Output 2:

 0
Output 3:

 1


Example Explanation

You can clearly see that the first and third case contain valid paranthesis.

In the second case, there is no closing bracket for {, thus the paranthesis sequence is invalid.
     */
    public int solve(String A) {

        Stack<Character> st = new Stack<>();
        if(A.length()<=1)
            return 0;
        int result = 0;
        for(int i=0; i<A.length();i++){
            char temp = A.charAt(i);
            if(temp=='(' || temp=='{' || temp =='['){
                st.push(temp);
                continue;
            }
            char c;
            switch (temp){
                case ')' :
                    c = st.pop();
                    if(c!= '(')
                        return 0;
                    break;
                case '}' :
                    c = st.pop();
                    if(c!='{')
                        return 0;
                    break;
                case ']' :
                    c = st.pop();
                    if(c !='[')
                        return 0;
                    break;
            }
        }
        if(st.isEmpty()) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        String A = "([{}])";
        BalancedParanthesis bp = new BalancedParanthesis();
        System.out.println(bp.solve(A));
    }
}
