package com.ramamosr.BitManipulationProbs;

public class CountSetBits {

    /*
    /*Count Total Set Bits
Problem Description
Given a positive integer A, the task is to count the total number of set bits in the binary representation of all the numbers from 1 to A.
Return the count modulo 109 + 7.
Problem Constraints
1 <= A <= 109
Input Format
First and only argument is an integer A.
Output Format
Return an integer denoting the ( Total number of set bits in the binary representation of all the numbers from 1 to A )modulo 109 + 7.
Example Input
Input 1:
A = 3
Input 2:
A = 1
Example Output
Output 1:
4
Output 2:
1
Example Explanation
Explanation 1:
DECIMAL    BINARY  SET BIT COUNT
    1          01        1
    2          10        1
    3          11        2
 1 + 1 + 2 = 4
Answer = 4 % 1000000007 = 4
Explanation 2:
A = 1
DECIMAL    BINARY  SET BIT COUNT
    1          01        1
Answer = 1 % 1000000007 = 1
 */
    public int solve(int A) {
        if(A<=1) return A;
        int mod = (1000*1000*1000) + 7;
        long ans = 0;
        int msb = (int)Math.floor((Math.log(A)/Math.log(2)));
        // Count the bits till the absolute power 2 -1, for 8, this step counts till 7.
        ans+= (long)Math.pow(2,msb-1) * msb;
        // this step counts the left most 1s msb between 8 and 9
        ans+= (long) (A - (Math.pow(2,msb) - 1));
        // recursion to count the bits between 8 and 9 after removing the msb.
        ans+= solve((int)(A - (Math.pow(2,msb))));
        ans = ans % mod;
        return (int)(ans);
    }

    public static void main(String[] args) {
        CountSetBits cb = new CountSetBits();
        System.out.println(cb.solve(9));
    }
}
