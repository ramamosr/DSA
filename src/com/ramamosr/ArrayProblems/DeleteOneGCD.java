package com.ramamosr.ArrayProblems;

public class DeleteOneGCD {
    /*
    Delete one
Problem Description

Given an integer array A of size N. You have to delete one element such that the GCD(Greatest common divisor) of the remaining array is maximum.

Find the maximum value of GCD.



Problem Constraints
2 <= N <= 105
1 <= A[i] <= 109



Input Format
First argument is an integer array A.



Output Format
Return an integer denoting the maximum value of GCD.



Example Input
Input 1:

 A = [12, 15, 18]
Input 2:

 A = [5, 15, 30]


Example Output
Output 1:

 6
Output 2:

 15


Example Explanation
Explanation 1:


 If you delete 12, gcd will be 3.
 If you delete 15, gcd will be 6.
 If you delete 18, gcd will 3.
 Maximum vallue of gcd is 6.
Explanation 2:

 If you delete 5, gcd will be 15.
 If you delete 15, gcd will be 5.
 If you delete 30, gcd will be 5.
     */
    static int gcd(int a, int b)
    {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public int solve(int[] A) {
        int[] prefix = new int[A.length];
        int[] suffix = new int[A.length];

        prefix[0]= A[0];
        suffix[A.length-1] = A[A.length-1];

        for(int i =1; i<A.length;i++){
            prefix[i] = gcd(prefix[i-1],A[i]);
        }

        for(int i =A.length-2; i>=0;i--){
            suffix[i] = gcd(suffix[i+1],A[i]);
        }

        int ans = 0;
        for(int i=0; i<A.length;i++){
            if(i==0)
                ans = Math.max(ans,suffix[i+1]);
            else if (i==A.length-1)
                ans = Math.max(ans,prefix[i-1]);
            else
                ans = Math.max(ans,gcd(prefix[i-1],suffix[i+1]));
        }
        return ans;
    }
    public static void main(String[] args) {
        DeleteOneGCD dog = new DeleteOneGCD();
        System.out.println(dog.solve(new int[]{5,15,30}));
    }
}
