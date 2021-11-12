package com.ramamosr.backtracking;

import java.util.ArrayList;

public class GenerateAllParanthesis {
    /*
    Problem Description

Given an integer A pairs of parentheses, write a function to generate all combinations of well-formed parentheses of length 2A.



Problem Constraints
1 <= A <= 20



Input Format
First and only argument is integer A.



Output Format
Return a sorted list of all possible paranthesis.



Example Input
Input 1:

A = 3
Input 2:

A = 1


Example Output
Output 1:

[ "((()))", "(()())", "(())()", "()(())", "()()()" ]
Output 2:

[ "()" ]


Example Explanation*
Explanation 1:

 All paranthesis are given in the output list.
Explanation 2:

 All paranthesis are given in the output list.
     */
    ArrayList<String> result = new ArrayList<>();

    public void findParanthesis(int A, char[] combination,int index, int countLeftP, int countRightP){

        // base condition check if the count of left paranthesie and right paranthesis match
        // and the current index  is equal 2*A as mentioned in the problem statement.

        if(index == 2*A && countLeftP==countRightP){
            String temp = new String(combination);
            result.add(temp);
        }
        if(countLeftP > 0){
            combination[index]='(';
            findParanthesis(A,combination,index+1,countLeftP-1,countRightP);
        }
        // Check if left paranthesis is less than right paranthesis.
        // since count is the availabe balance to be included
        // both right and left should be balanced all the time to get a valid closed () paranthesis.
        if(countLeftP < countRightP && countRightP >0){
            combination[index]=')';
            findParanthesis(A,combination,index+1,countLeftP,countRightP-1);
        }

    }

    public ArrayList<String> generateParenthesis(int A) {
        char[] combination = new char[2*A];
        //setting the initial value of left paranthesis count and right paranthesis count to A.
        //for valid paranthesis string both left and right have to be of equal count.
        findParanthesis(A,combination,0,A,A);
        return result;
    }

    public static void main(String[] args) {
        GenerateAllParanthesis gap = new GenerateAllParanthesis();
        gap.generateParenthesis(3);
    }
}
