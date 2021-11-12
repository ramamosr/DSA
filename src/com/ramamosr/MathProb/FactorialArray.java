package com.ramamosr.MathProb;

import java.util.ArrayList;
import java.util.Arrays;

public class FactorialArray {
    /*
    Factorial Array
Problem Description

Groot has an array A of size N. Boring right? Groot thought so too, so he decided to construct another array B of the same size and defined elements of B as:

B[i] = factorial of A[i] for every i such that 1<= i <= N.

factorial of a number X denotes (1 x 2 x 3 x 4......(X-1) x (X)).
Now Groot is interested in the total number of non-empty subsequences of array B such that every element in the subsequence has the same non empty set of prime factors.

Since the number can be very large, return it modulo 109 + 7.

NOTE: A set is a data structure having only distinct elements. Eg : the set of prime factors of Y=12 will be {2,3} and not {2,2,3}



Problem Constraints
1 <= N <= 105
1 <= A[i] <= 106
Your code will run against a maximum of 5 test cases.



Input Format
Only argument is an integer array A of size N.



Output Format
Return an integer deonting the total number of non-empty subsequences of array B
such that every element in the subsequence has the same set of prime factors modulo 109+7.



Example Input
Input 1:

 A = [2, 3, 2, 3]
Input 2:

 A = [2, 3, 4]


Example Output
Output 1:

 6
Output 2:

 4


Example Explanation
Explanation 1:

 Array B will be : [2, 6, 2, 6]
 The total possible subsequences are 6 : [2], [2], [2, 2], [6], [6], [6, 6].
Input 2:

 Array B will be : [2, 6, 24]
 The total possible subsequences are 4 : [2], [6], [24], [6, 24].
     */

    /*
    Approach
    We can say that factorial of any number between two consective prime number, say (x, y) will have equal set of prime numbers as that of set of prime numbers in x.
Since the factorial of x will contain all prime number less than equal to x.

We will store all the prime number less than equal to highest value in the given array in a sorted order in an auxilary array, say v.

Iterate over all prime number in array v and for each v[i], count the number of values in the array which are less than v[i].
Let cnt denotes that value of each prime number v[i].

All the subsequences of this will be pow(2, cnt) - 1.

Sum all the values and return the answer.

Note that we are talking of non-empty set. So the number 1 will be discarded.
     */
    int[] primeArr = new int[1000001];
    int nonPrimeCount = 0;
    // Find all prime numbers upto the max limit using sieve of eratosthenes method
    public void fillPrimeNumbers(){
        for (int i = 1; i < 1000001; i++)
            primeArr[i] = 1;

        for(int i= 2; i*i < 1000001;i++){
            if(primeArr[i]==1){
                for(int j=i*i; j<1000001;j+=i){
                    primeArr[j] = 0;
                    nonPrimeCount++;
                }
            }
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

    public int solve(int[] A) {
        int mod = 1000000007;
        fillPrimeNumbers();
        ArrayList<Integer> primeOnlyArr = new ArrayList<>();
        int len = A.length;
        Arrays.sort(A);
        // get the prime numbers within the range of numbers in the original array.
        for(int i= 2; i<=A[len-1];i++){
            if(primeArr[i]==1){
                primeOnlyArr.add(i);
            }
        }

        long ans = 0;
        int j = 0, i = 0;
        while(i < len && j < primeOnlyArr.size()){
            int cnt =0;
            if(A[i] == 1){
                i++;
                continue;
            }
            // Find all the numbers in the original array that are less than the
            //current prime factorial.
            while(i < len && A[i] < primeOnlyArr.get(j)){
                i++;
                cnt++;
            }
            long temp = power(2, cnt,mod) - 1;
            ans += temp;
            ans %= mod;
            j++;
        }
        if(i < len){
            long temp = power(2, len-i,mod) -1;
            ans += temp;
            ans %= mod;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        FactorialArray fa = new FactorialArray();
        System.out.println(fa.solve(new int[] {2,3,2,3}));
    }
}
