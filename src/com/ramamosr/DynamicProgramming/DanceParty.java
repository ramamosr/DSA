package com.ramamosr.DynamicProgramming;

public class DanceParty {
    /*
    Problem Description

In Danceland, one person can party either alone or can pair up with another person.

Can you find in how many ways they can party if there are A people in Danceland?

Note: Return your answer modulo 10003, as the answer can be large.



Problem Constraints

1 <= A <= 105



Input Format

Given only argument A of type Integer, number of people in Danceland.



Output Format

Return an integer denoting the number of ways people of Danceland can party.



Example Input

Input 1:

 A = 3
Input 2:

 A = 5


Example Output

Output 1:

 4
Output 2:

 26


Example Explanation

Explanation 1:

 Let suppose three people are A, B, and C. There are only 4 ways to party
 (A, B, C) All party alone
 (AB, C) A and B party together and C party alone
 (AC, B) A and C party together and B party alone
 (BC, A) B and C party together and A
 here 4 % 10003 = 4, so answer is 4.

Explanation 2:

 Number of ways they can party are: 26.
     */

    /*
    Every person can either pair with another person or can remain single.
Let us consider the kth person, he can either remain single or he can pair up with someone from [1, k-1].

So here recurrence relation is :
Number_of_ways(k-1) + (k-1) * Number_of_ways(k-2)

and by using dynamic programming we can solve overlapping subproblems.
i.e. dp[i] = dp[i-1] + dp[i-2] * (i-1)
     */

    public int solve(int A) {
        if(A==1)
            return 1;
        if(A==2)
            return 2;
        int[] choices = new int[A+1];
        choices[1] = 1;
        choices[2] = 2;

        for(int i=3;i<=A;i++){
            choices[i] = ((i-1) * choices[i-2] + choices[i-1])%10003;
        }
        return choices[A];
    }

    public static void main(String[] args) {
        DanceParty dp = new DanceParty();
        System.out.println(dp.solve(465));
    }
}
