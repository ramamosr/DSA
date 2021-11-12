package com.ramamosr.MathProb;

public class PrimeModuloInverse {
    /*
    Prime Modulo Inverse
Problem Description

Given two integers A and B. Find the value of A-1 mod B where B is a prime number and gcd(A, B) = 1.

A-1 mod B is also known as modular multiplicative inverse of A under modulo B.



Problem Constraints
1 <= A <= 109
1<= B <= 109
B is a prime number



Input Format
First argument is an integer A.
Second argument is an integer B.



Output Format
Return an integer denoting the modulor inverse



Example Input
Input 1:

 A = 3
 B = 5
Input 2:

 A = 6
 B = 23


Example Output
Output 1:

 2
Output 2:

 4


Example Explanation
Explanation 1:

 Let's say A-1 mod B = X, then (A * X) % B = 1.
 3 * 2 = 6, 6 % 5 = 1.
Explanation 2:

 Similarly, (6 * 4) % 23 = 1.
     */
    public int solve(int A, int B) {
        //fermats little theorem
        /*
        a^p-1 congruent to 1 with respect to mod m if m is prime.
        a^-1 mod m can be solved with a^m-2 % m
        so we can solve this with O(logM)
        */
        return calculatePower(A,B-2, B);
    }

    public int calculatePower(int num, int pow, int mod){

        if(pow==0)
            return 1;
        int temp = calculatePower(num,pow/2,mod);
        temp = (((temp%mod) * (temp%mod)) % mod);
        if(pow%2==0){
            return  temp;
        }
        else{
            return (num * temp)%mod;
        }

    }

    long power(long x, long y, long p) {
        long res = 1;        // Initialize result
        x = x % p;        // Update x if it is more than or equal to p
        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == (long)1)
                res = (res*x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    public int solveScaler(int A, int B) {
        // Modular inverse of A Modulo B = pow(A, B - 2, B)
        return (int)power(A, B - 2, B);
    }

    public static void main(String[] args) {
        PrimeModuloInverse pmi = new PrimeModuloInverse();
        System.out.println(pmi.solveScaler(3,3268052));
    }
}
