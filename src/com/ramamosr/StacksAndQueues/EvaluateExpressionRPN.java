package com.ramamosr.StacksAndQueues;

import java.util.Stack;

public class EvaluateExpressionRPN {
/*
Problem Description

An arithmetic expression is given by a charater array A of size N. Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each character may be an integer or an operator.



Problem Constraints

1 <= N <= 105



Input Format

The only argument given is character array A.



Output Format

Return the value of arithmetic expression formed using reverse Polish Notation.



Example Input

Input 1:
    A =   ["2", "1", "+", "3", "*"]
Input 2:
    A = ["4", "13", "5", "/", "+"]


Example Output

Output 1:
    9
Output 2:
    6


Example Explanation

Explaination 1:
    starting from backside:
    * : () * ()
    3 : () * (3)
    + : (() + ()) * (3)
    1 : (() + (1)) * (3)
    2 : ((2) + (1)) * (3)
    ((2) + (1)) * (3) = 9
Explaination 2:
    + : () + ()
    / : () + (() / ())
    5 : () + (() / (5))
    1 : () + ((13) / (5))
    4 : (4) + ((13) / (5))
    (4) + ((13) / (5)) = 6
 */
    /*
    This is pretty much a simulation problem.
Think stack.

When you encounter an operator is when you need the top 2 numbers on the stack, perform the operation on them and put them on the stack.
     */

    public int evalRPN(String[] A)
    {
        // Initialize the stack and the variable
        Stack<String> stack = new Stack<String>();
        int x, y;
        String result = "";
        int get = 0;
        String ops="";
        int value = 0;
        String p = "";

        for (int i = 0; i < A.length; i++) {
            if (!A[i].equals("+") && !A[i].equals("-") && !A[i].equals("*") && !A[i].equals("/")) {
                stack.push(A[i]);
                continue;
            }
            else {
                ops = A[i];
            }

            // Switch-Case
            switch (ops) {
                case "+":
                    x = Integer.parseInt(stack.pop());
                    y = Integer.parseInt(stack.pop());
                    value = x + y;
                    result = p + value;
                    stack.push(result);
                    break;
                case "-":
                    x = Integer.parseInt(stack.pop());
                    y = Integer.parseInt(stack.pop());
                    value = y - x;
                    result = p + value;
                    stack.push(result);
                    break;
                case "*":
                    x = Integer.parseInt(stack.pop());
                    y = Integer.parseInt(stack.pop());
                    value = x * y;
                    result = p + value;
                    stack.push(result);
                    break;

                case "/":
                    x = Integer.parseInt(stack.pop());
                    y = Integer.parseInt(stack.pop());
                    value = y / x;
                    result = p + value;
                    stack.push(result);
                    break;
                default:
                    continue;
            }
        }
        return Integer.parseInt(stack.pop());
    }


    public static void main(String[] args) {
        EvaluateExpressionRPN eer = new EvaluateExpressionRPN();
        System.out.println(eer.evalRPN(new String[] { "2", "1", "+", "3", "*" }));
    }
    }

