package com.ramamosr.MathProb.Combinatorics;

public class ComputenCrmodPrime {

    /*
    Compute nCr % p
Problem Description

Given three integers A, B and C, where A represents n, B represents r and C represents p and p is a prime number greater than equal to n, find and return the value of nCr % p where nCr % p = (n! / ((n-r)! * r!)) % p.

x! means factorial of x i.e. x! = 1 * 2 * 3... * x.

NOTE: For this problem, we are considering 1 as a prime.



Problem Constraints
1 <= A <= 106
1 <= B <= A
A <= C <= 109+7


Input Format
The first argument given is the integer A ( = n).
The second argument given is the integer B ( = r).
The third argument given is the integer C ( = p).



Output Format
Return the value of nCr % p.



Example Input
Input 1:

 A = 5
 B = 2
 C = 13
Input 2:

 A = 6
 B = 2
 C = 13


Example Output
Output 1:

 10
Output 2:

 2


Example Explanation
Explanation 1:

 nCr( n=5 and r=2) = 10.
 p=13. Therefore, nCr%p = 10.
     */

    /*
    We will use the fermats theorem to calculate the inverse.
    nCr = n!/r!(n-r)!
    Calculate n%
    calculate Inverse of r! which is r!^mod-2 % mod
    calculate Inverse of r! which is (n-r)!^mod-2 % mod
     */
    public long findPower(long x, int y, int p) {
        long res = 1;

        x = x % p;

        while (y > 0) {
            if (y % 2 == 1)
                res = (res * x) % p;

            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }

        return res;
    }

    public long inverseMod(long n, int mod){
        return findPower(n,mod-2,mod);
    }



    public int solve(int A, int B, int C) {

        // fill the factorial array so that it can be resused for all the 3 factorial calculations.
        if (A<B)
            return 0;
        // Base case
        if (B == 0)
            return 1;

        // Fill factorial array so that we
        // can find all factorial of r, n
        // and n-r
        long[] fac = new long[A + 1];
        fac[0] = 1;

        for (int i = 1; i <= A; i++)
            fac[i] = fac[i - 1] * i % C;

        long result =  (fac[A] * inverseMod(fac[B], C) % C* inverseMod(fac[A - B], C)% C)%C;
        return (int)(result);
    }

    public static void main(String[] args) {
        ComputenCrmodPrime ncr  = new ComputenCrmodPrime();
        System.out.println(ncr.solve(149,12,48157));
    }
}
