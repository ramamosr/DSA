package com.ramamosr;

public class PalindromeInteger {

    /*
    Palindrome Integer
Problem Description

Determine whether an integer is a palindrome. Do this without extra space.

A palindrome integer is an integer x for which reverse(x) = x where reverse(x) is x with its digit reversed. Negative numbers are not palindromic.

Example :

Input : 12121
Output : True

Input : 123
Output : False
     */
    public int isPalindrome(int A) {
        int result = 0;
        if(A<0)
            return 0;
        if(A<10)
            return 1;
        int divisor = 1;
        while(A/divisor>=10)
            divisor = divisor * 10;

        while(A>0){
            int firstNum = 0, lastNum = 0;
            firstNum = A/divisor;
            lastNum = A % 10;
            if(firstNum!=lastNum)
                return 0;
            A = (A%divisor)/10; // mod removes the first number. division removes the last number.
            // Divide by 100. as 2 numbers will be removed. first and last from the previous step.
            divisor = divisor/100;
        }
        return 1;
    }

    public static void main(String[] args) {
        PalindromeInteger pi = new PalindromeInteger();
        pi.isPalindrome(1000000001);
    }
}
