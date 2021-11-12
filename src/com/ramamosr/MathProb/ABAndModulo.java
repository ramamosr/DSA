package com.ramamosr.MathProb;

public class ABAndModulo {
    /*
    A, B and Modulo
Problem Description

Given two integers A and B, find the greatest possible positive M, such that A % M = B % M.



Problem Constraints
1 <= A, B <= 109
A != B



Input Format
The first argument given is the integer, A.
The second argument given is the integer, B.



Output Format
Return an integer denoting greatest possible positive M.



Example Input
Input 1:

A = 1
B = 2
Input 2:

A = 5
B = 10


Example Output
Output 1:

1
Output 2:

5


Example Explanation
Explanation 1:

1 is the largest value of M such that A % M == B % M.
Explanation 2:

For M = 5, A % M = 0 and B % M = 0.

No value greater than M = 5, satisfies the condition.
     */

    public int solve(int A, int B) {
        /*
        if A%M = B%M then M should be in range between A-B or should be a factor of A-B.
        since the problem asks for highest, then it should be the differnce between A and B.
         */
        return Math.abs(A-B);
    }

    public static void main(String[] args) {
        ABAndModulo abam = new ABAndModulo();
        System.out.println(abam.solve(5,10));
    }
}
