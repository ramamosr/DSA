package com.ramamosr.MathProb;

public class ReverseInteger {
    /*
    Reverse integer
Problem Description

You are given an integer N and the task is to reverse the digits of the given integer. Return 0 if the result overflows and does not fit in a 32 bit signed integer

Look at the example for clarification.



Problem Constraints
N belongs to the Integer limits.



Input Format
Input an Integer.



Output Format
Return a single integer denoting the reverse of the given integer.



Example Input
Input 1:

 x = 123

Input 2:

 x = -123


Example Output
Output 1:

 321

Ouput 2:

 -321


Example Explanation
 If the given integer is negative like -123 the output is also negative -321.
     */

    public int reverse(int A) {

        int reverseNum = 0;
        int prevReverseNum = 0;
        boolean isNegative = false;
        if(A<0) {
            isNegative = true;
            A = Math.abs(A);
        }

        while(A!=0){
            int currDigit = A%10;
            reverseNum = (reverseNum*10) + currDigit;

            // Checking for overflow
            if((reverseNum-currDigit)/10 !=prevReverseNum)
                return 0;
            prevReverseNum = reverseNum;
            A = A/10;
        }
        if(isNegative)
            return -(reverseNum);
        else
            return reverseNum;
    }

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverse(-1234567891));
    }
}
