package com.ramamosr.DynamicProgramming;

import java.util.Scanner;

public class FIbonacciNumber {

    public static void main(String[] args) {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output

        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int firstNum=0,secondNum=1;
        int ans=0;
        if(a==1 || a==0){
            System.out.println(a);
        }
        else{
            for(int i=2;i<=a;i++){
                // Fibonacci is the sum of two previous numbers.
                // so after computing the answer 
                // the prev second number becomes first number.
                // the current answer becomes the second number
                ans=firstNum+secondNum;
                firstNum=secondNum;
                secondNum=ans;

            }
            System.out.println(ans);
        }
    }
}
