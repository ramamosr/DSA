package com.ramamosr.BitManipulationProbs;

public class DifferentBitsSumPairwise {
    /*
    Different Bits Sum Pairwise
Problem Description

We define f(X, Y) as number of different corresponding bits in binary representation of X and Y.
For example, f(2, 7) = 2, since binary representation of 2 and 7 are 010 and 111, respectively. The first and the third bit differ, so f(2, 7) = 2.

You are given an array of N positive integers, A1, A2 ,..., AN. Find sum of f(Ai, Aj) for all pairs (i, j) such that 1 ≤ i, j ≤ N. Return the answer modulo 109+7.



Problem Constraints
1 <= N <= 105

1 <= A[i] <= 231 - 1



Input Format
First and only argument of input contains a single integer array A.



Output Format
Return a single integer denoting the sum.



Example Input
Input 1:

 A = [1, 3, 5]
Input 2:

 A = [2, 3]


Example Output
Ouptut 1:

 8
Output 2:

 2


Example Explanation
Explanation 1:

 f(1, 1) + f(1, 3) + f(1, 5) +
 f(3, 1) + f(3, 3) + f(3, 5) +
 f(5, 1) + f(5, 3) + f(5, 5)
 = 0 + 1 + 1
 + 1 + 0 + 2 +
  1 + 2 + 0 = 8
Explanation 2:

 f(2, 2) + f(2, 3) + f(3, 2) + f(3, 3) = 0 + 1 + 1 + 0 = 2

     */

    public int cntBits(int[] A) {
            long result = 0;
            int mod = 1000000007;
            // Since the given number is integer, lets check if the ith bit is set for
            // each element in the array.
            for(int i=0;i<32;i++){
                long countOfSetBits = 0;

                for(int j=0;j<A.length;j++){
                    // Check if the ith bit is set by left shifting 1 to the ith position
                    if((A[j] & (1<<i)) >0){
                        countOfSetBits++;
                    }
                }
                int countOfUnSetBits = A.length- (int)countOfSetBits;
                // based on the problem definition of  f(Ai,Aj) can include the same number as well as the reverse
                // F(1,1) is a valid pair with contribution 0.
                //f(1,3) and f(3,1) is also a valid pair. so the resultant count should be multiplied by 2.
                result+= (countOfSetBits * countOfUnSetBits *2);
            }
            return (int) (result%mod);
        }

        public int cntBitsScaler(int[] A) {
            long ans = 0;
            int n = A.length, Mod = 1000 * 1000 * 1000 + 7;
            //traverse over all bits
            for (int i = 0; i < 31; i++) {
                //count number of elements with ith bit = 0
                long cnt = 0;
                for (int j = 0; j < n; j++)
                    if ((A[j] & (1 << i)) == 0) cnt++;
                //add to answer cnt*(n-cnt)*2
                ans += (cnt * (n - cnt) * 2) % Mod;
                ans %= Mod;
            }
            return (int) ans;
        }

    public static void main(String[] args) {
        DifferentBitsSumPairwise dpsp = new DifferentBitsSumPairwise();
        System.out.println(dpsp.cntBits(new int[]{1,1}));
    }

}
