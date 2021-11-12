package com.ramamosr.MathProb;

public class CountOfDivisors {
    /*
    Count of divisors
Problem Description

Given an array of integers A, find and return the count of divisors of each element of the array.

NOTE: Order of the resultant array should be same as the input array.



Problem Constraints
1 <= length of the array <= 100000
1 <= A[i] <= 106



Input Format
The only argument given is the integer array A.



Output Format
Return the count of divisors of each element of the array in the form of an array.



Example Input
Input 1:

 A = [2, 3, 4, 5]
Input 2:

 A = [8, 9, 10]


Example Output
Output 1:

 [2, 2, 3, 2]
Output 1:

 [4, 3, 4]


Example Explanation
Explanation 1:

 The number of divisors of 2 : [1, 2], 3 : [1, 3], 4 : [1, 2, 4], 5 : [1, 5]
 So the count will be [2, 2, 3, 2].
Explanation 2:

 The number of divisors of 8 : [1, 2, 4, 8], 9 : [1, 3, 9], 10 : [1, 2, 5, 10]
 So the count will be [4, 3, 4].
     */

    public int[] solve(int[] A) {
        int[] result = new int[A.length];

        // precompute the divisor count for the max number
        // in this case, problem statement is 10^6.
        // Initialize the entire array with 1 as all numbers will have a factor  as 1.
        // Then apply the sieve to increment the count by 1 for every i.

        // For element 6, the initial value will be 1.
        // when i becomes 2, the inner loop for j will increment the count to 1+1 = 2.
        // 2 is also a divisor of 6.
        // when i becomes 3, the inner loop increments the count by 1 to 3.
        /// Since 4 and 5 are not divisors those loops will not affect 6.
        //Again when i becomes 6, the value gets incremented by 1. so 6 will have 4 factors.

        int max = 1000*1000+1;
        int[] divisorArr = new int[max+1];
        divisorArr[1] = 1;

        for(int i = 1; i<divisorArr.length;i++)
            divisorArr[i] = 1;

        for(int i = 2; i<=max;i++){
            for(int j=i; j<=max;j+=i){
                divisorArr[j]++;
            }
        }

        for(int i =0; i<A.length;i++){
            result[i] = divisorArr[A[i]];
        }

        return result;
    }

    /*
    Using seive, we can store the smallest prime factor for all the numbers upto the maximum no (here it is 106).
This above information helps in determining the prime factors for any no in O(log n) time-complexity for each query.

We take each no in the input array. Then prime factorise it to count the powers of each prime factors in a number.

N = (p1n1) * (p2n2) * (p3n3).

Here, N can be represented as p1 prime raised to the power n1 muliply p2 prime raised to n2 multiply p3 raised to n3.

So, total factors of N will be (n1 + 1) * (n2 + 1) * (n3 + 1).

For eg: 18 = (21) * (32).

So, total factors = 2*3 = 6.
     */
    int S[], SZ, NP = 1001001;

    void sieve() {
        int n = NP;
        S = new int[n];
        for(int i = 1; i < n; i++)
            S[i] = i;
        for(int i = 2; i * i <= n; i++)
        {
            if( S[i] != i )
                continue;
            for(int j = i * i; j < n; j += i)
            {
                if(S[j] == j)
                    S[j] = i;
            }
        }
    }

    int countDivisors(int x) {
        int ans = 1;
        while(S[x] > 1) {
            int cnt = 1, u = S[x];
            while(S[x] == u) {
                cnt++;
                x /= u;
            }
            ans *= cnt;
        }
        return ans;
    }

    public int[] solvescaler(int[] a) {
        sieve();
        int n = a.length;
        int ans[] = new int[n];
        for(int i = 0; i < n; i++)
            ans[i] = countDivisors(a[i]);
        return ans;
    }
    public static void main(String[] args) {
        int[] A = {2, 3, 4, 5};
        CountOfDivisors cod = new CountOfDivisors();
        cod.solve(A);
    }
}
