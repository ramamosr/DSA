package com.ramamosr.BitManipulationProbs;

public class SubArrayOR {

    /*
    SUBARRAY OR
Problem Description

Given an array of integers A of size N.

Value of a subarray is defined as BITWISE OR of all elements in it.

Return the sum of Value of all subarrays of A % 109 + 7.



Problem Constraints
1 <= N <= 105

1 <= A[i] <= 108



Input Format
The first argument given is the integer array A.



Output Format
Return the sum of Value of all subarrays of A % 109 + 7.



Example Input
Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [7, 8, 9, 10]


Example Output
Output 1:

 71
Output 2:

 110


Example Explanation
Explanation 1:

 Value([1]) = 1
 Value([1, 2]) = 3
 Value([1, 2, 3]) = 3
 Value([1, 2, 3, 4]) = 7
 Value([1, 2, 3, 4, 5]) = 7
 Value([2]) = 2
 Value([2, 3]) = 3
 Value([2, 3, 4]) = 7
 Value([2, 3, 4, 5]) = 7
 Value([3]) = 3
 Value([3, 4]) = 7
 Value([3, 4, 5]) = 7
 Value([4]) = 4
 Value([4, 5]) = 5
 Value([5]) = 5
 Sum of all these values = 71
Explanation 2:

 Sum of value of all subarray is 110.
     */

    /*
    Approach.
    To find all the subarrays we will have to iterate from the start of the array.
    for each element in the array, we will have to run the through the rest of the array inclduing the element.
    as the element itself will be a subarray.
    So initiate 2 loops. the inner loop starting from the element of the outer loop.
    every element that we move through the innner loop forms the subarray.
    calculate the OR for every element and then it add to the total sum,

    Approach Optimized
    In this question , we will consider every bit as an unique bits and every bit will have independent contribution to the answer .
Thus we will iterate over whole array for all bits independently , and we will maintain an 2d array BitInfo[bits][index] , the value at any index ind for any bit will signify the next index on which this bit is set .
Thus after that index every subarray corresponding to ind will have that bit set .
In this manner the contribution of every bit corresponding to each bit can be determined independently and can be added to the overall answer.

Another way to solve this problem is to iterate over each bit and count the number of subarrays it won't have a contribution in.
After calculating that, we can simply deduct this from the total count of subarrays(which is same for each bit).
To count the number of subarrays in which a bit won't have contribution in, consider the following example for an arbitrary bit:
0 1 1 0 0 0 0 1 1 0 0 1 (0 is for the places where the bit is unset, and 1 is for the places where this bit is set)
If the starting till ending point of a subarray does not consist of even a single 1, then the bit won't contribute to the bitwise OR.
The number of subarrays that can be formed from an array of size x is x * (x + 1) / 2. So, we can simply iterate over this binary array
which we have precomputed for each bit before and apply the formula to get the total number of subarrays where the selected bit does not contribute to bitwise OR.
More details in the video are provided.
     */
    public int solveOld(int[] A) {
        long totalSum = 0;
        int mod = (1000 * 1000 * 1000) +7;
        for(int i =0; i<A.length;i++){
            int tempSum = 0;
            for(int j = i; j< A.length;j++){
                tempSum = (tempSum | A[j]);
                totalSum+= tempSum;
            }

        }
        return (int)(totalSum%mod);
    }

    public int solve(int[] A){
        long ans =   0;
        int mod = (1000 * 1000 * 1000) +7;
        int len = A.length%mod;
        long totalSubArrays = ((long)len * ((long)(len+1)))/2;


        for(int i=0; i<32;i++){
            int[] binArr = new int[len];
            for(int j =0;j <len;j++){
                binArr[j] = getBit(A[j],i);
            }
            long sumArray = subArraySumofOR(binArr);
            ans+= (totalSubArrays - sumArray) * Math.pow(2, i) % mod;
            ans%=mod;
        }
        return (int)(ans);
    }

    public long subArraySumofOR(int [] A){
        long zCount = 0;
        int mod = (1000 * 1000 * 1000) +7;
        long sum = 0;
        for(int i=0;i<A.length;i++){
            if(A[i]==0)
                zCount++;
            if(A[i]==1 || i==A.length-1) {
                if(zCount >0)
                    sum = sum + zCount * (zCount + 1) / 2;
                zCount = 0;
            }
        }
            return sum;
    }
    public int getBit(int element, int pos){
        return(element>>pos) & 1;
    }

    public int solveScaler(int[] A) {
        int n = A.length;
        int[] idx = new int[32];
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            long tmp = A[i - 1];
            for (int j = 0; j <= 31; ++j) {
                long pw = 1 << j;
                if ((tmp & pw) != 0) { //if jth bit is set
                    ans += pw * i; // add its contribution in ans for all subarrays ending at index i
                    idx[j] = i; // store the index for next elements
                } else if (idx[j] != 0) // if jth bit is not set
                {
                    ans += pw * idx[j]; // add its contribution in ans for all subarrays ending at index i using
                } // the information of last element having jth bit set
            }
        }
        return (int)(ans % 1000000007);
    }

    public static void main(String[] args) {
        SubArrayOR sa = new SubArrayOR();
        System.out.println(sa.solveOld( new int []{347148, 221001, 394957, 729925, 276769, 40726, 552988, 29952, 184491, 146773, 418965, 307, 219145, 183037, 178111, 81123, 109199, 683929, 422034, 346291, 11434, 7327, 340473, 316152, 364005, 359269, 170935, 105784, 224044, 22563, 48561, 165781, 9329, 357681, 169473, 175031, 605611, 374501, 6607, 329965, 76068, 836137, 103041, 486817, 195549, 107317, 34399, 56907, 37477, 189690, 36796, 376663, 39721, 177563, 174179, 183646, 217729, 408031, 429122, 631665, 282941, 526797, 262186, 306571, 63613, 57501, 70685, 226381, 1338, 9360, 130360, 20300, 400906, 87823, 180349, 108813, 18181, 119185, 1, 102611, 63591, 12889, 311185, 383896, 8701, 76077, 75481, 386017, 153553, 304913, 383455, 105948, 142885, 1, 12610, 137005, 119185, 16948, 66171, 123683}));
        System.out.println(sa.solve( new int []{347148, 221001, 394957, 729925, 276769, 40726, 552988, 29952, 184491, 146773, 418965, 307, 219145, 183037, 178111, 81123, 109199, 683929, 422034, 346291, 11434, 7327, 340473, 316152, 364005, 359269, 170935, 105784, 224044, 22563, 48561, 165781, 9329, 357681, 169473, 175031, 605611, 374501, 6607, 329965, 76068, 836137, 103041, 486817, 195549, 107317, 34399, 56907, 37477, 189690, 36796, 376663, 39721, 177563, 174179, 183646, 217729, 408031, 429122, 631665, 282941, 526797, 262186, 306571, 63613, 57501, 70685, 226381, 1338, 9360, 130360, 20300, 400906, 87823, 180349, 108813, 18181, 119185, 1, 102611, 63591, 12889, 311185, 383896, 8701, 76077, 75481, 386017, 153553, 304913, 383455, 105948, 142885, 1, 12610, 137005, 119185, 16948, 66171, 123683}));
        //System.out.println(sa.solveOptimized( new int []{1,2,3,4,5}));

    }
}
