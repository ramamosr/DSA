package com.ramamosr;

public class SumOfDigitsInANumber {

    /*
    Sum of Digits!
Problem Description

Given a number A, we need to find sum of its digits using recursion.



Problem Constraints
1 <= A <= 109



Input Format
First and only argument is an integer A.



Output Format
Return an integer denoting the sum of digits of the number A.



Example Input
Input 1:

 A = 46
Input 2:

 A = 11


Example Output
Output 1:

 10
Output 2:

 2


Example Explanation
Explanation 1:

 Sum of digits of 46 = 4 + 6 = 10
Explanation 2:

 Sum of digits of 11 = 1 + 1 = 2
     */

    public int solve(int A) {

        //base case when A ==0 return A
        if(A==0)
            return A;
        else
            // get the last digit by taking the mod.
            // send the remaining digits as recursive by division
            // 45%10 will give 5
            // 45/10 will give 4 4+5 = 9
            return ((A % 10) + solve(A/10));
    }

    public static void main(String[] args) {
        SumOfDigitsInANumber sod = new SumOfDigitsInANumber();
        System.out.println(sod.solve(123456789));

    }
}
