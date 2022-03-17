package com.ramamosr.DynamicProgramming;

public class ClimbStairs {

    /*
    Problem Description

You are climbing a stair case and it takes A steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Problem Constraints

1 <= A <= 36



Input Format

The first and the only argument contains an integer A, the number of steps.



Output Format

Return an integer, representing the number of ways to reach the top.



Example Input

Input 1:

 A = 2
Input 2:

 A = 3


Example Output

Output 1:

 2
Output 2:

 3


Example Explanation

Explanation 1:

 Distinct ways to reach top: [1, 1], [2].
Explanation 2:

 Distinct ways to reach top: [1 1 1], [1 2], [2 1].
     */
/*
This is the most basic dynamic programming problem.
We know that we can take 1 or 2 step at a time. So, to take n steps, we must have arrived at it immediately from n - 1 or n - 2th step.
If we knew the number of ways to reach n-1 and n-2th step, our answer would be the summation of their number of ways.

BONUS: Can you come up with O(logn) solution?
 */
    public int climbStairs(int A) {
        int[] ways = new int[A+1];
        if(A==1)
            return 1;

        ways[1] = 1; ways[2] = 2;

        for(int i =3;i<=A;i++){
                ways[i] = ways[i-1] + ways[i-2];
        }
        return ways[A];
    }

    public int climbStairsOld(int A) {
        if(A==1 || A==2)
            return A;
        return climbStairs(A-1)  + climbStairs(A-2);
    }

    public static void main(String[] args) {
        ClimbStairs cs = new ClimbStairs();
        System.out.println(cs.climbStairs(3));
    }
}
