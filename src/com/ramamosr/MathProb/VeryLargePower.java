package com.ramamosr.MathProb;


public class VeryLargePower {
    /*
    Very Large Power
Problem Description

Given two Integers A, B. You have to calculate (A ^ (B!)) % (1e9 + 7).

"^" means power ,

"%" means "mod", and

"!" means factorial.



Problem Constraints
1 <= A, B <= 5e5



Input Format
First argument is the integer A

Second argument is the integer B



Output Format
Return one integer, the answer to the problem



Example Input
Input 1:

A = 1
B = 1
Input 2:

A = 2
B = 2


Example Output
Output 1:

1
Output 2:

4


Example Explanation
Explanation 1:

 1! = 1. Hence 1^1 = 1.
Explanation 2:

 2! = 2. Hence 2^2 = 4.
     */


    public int solve(int A, int B) {
        /*
        B factorial could be a very large number. we will have to reduce the factorial to a smaller number.
        Calculate the factorial with mod 1000000007.
        then we can apply the principle (a^b)%m = a^(b%(m-1)) %m
         */
        int mod = 1000000007;

        long power = (factorial(B,mod-1));
        long result = findPower(A,power,mod);
        return (int)(result%mod);

    }
    public long findPower(long x, long y, long p) {
        long res = 1;        // Initialize result
        x = x % p;        // Update x if it is more than or equal to p
        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == (long)1)
                res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    public long factorial(long fact, int mod){
        if(fact==0)
            return 1;
        else{
            long temp =  (fact * factorial((fact-1),mod))%mod;
            return  (temp);
        }
    }
    public static void main(String[] args) {
        VeryLargePower vlp = new VeryLargePower();
        System.out.println(vlp.solve(2,4));
    }
}
