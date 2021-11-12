package com.ramamosr.MathProb.Combinatorics;

public class ComputenCrmodEvenOrPrime {

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
    Approach:
    Since the mod can be odd or even, we cannot use fermats theorem. We will have to use the formula
    nCr = n-1Cr-1 + n-1Cr.
    Form a pascal triangle.
    nC0 is always 1.
    0C0 is always 1
    nCn is always 1.
    We will construct a 2 dimensional array

    If we calculate nCr by calculating factorial of each number and then doing n! / (r! * (n-r)!) % m. This will not work as the factorial can be very large and will cause overflow.

As we know nCr = n-1Cr-1 + n-1Cr.

So we will use Dynamic Programming approach and calculate the value of nCr.
     */
    public int findPower(int x, int y, int p) {
        long res = 1;        // Initialize result
        x = x % p;        // Update x if it is more than or equal to p
        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == (long)1)
                res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return (int)res;
    }


    public int solve(int A, int B, int C) {
        int[][] nCrArr = new int[A+1][B+1];

        for(int i=0; i<=A;i++){

            for(int j = 0; j<=B && j<=i;j++){
                if(j==0 || j==i)
                    nCrArr[i][j] = 1;
                else{
                    nCrArr[i][j] = (nCrArr[i-1][j-1] + nCrArr[i-1][j])%C;
                }
            }
        }
        return nCrArr[A][B];
    }

    public static void main(String[] args) {
        ComputenCrmodEvenOrPrime ncr  = new ComputenCrmodEvenOrPrime();
        System.out.println(ncr.solve(5,2,13));
    }
}
