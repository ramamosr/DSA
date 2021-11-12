package com.ramamosr.BitManipulationProbs;

public class SingleNumber3 {

    /*
    Single Number III
Problem Description

Given an array of numbers A , in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Note: Output array must be sorted.



Problem Constraints
2 <= |A| <= 100000
1 <= A[i] <= 109



Input Format
First argument is an array of interger of size N.



Output Format
Return an array of two integers that appear only once.



Example Input
Input 1:

A = [1, 2, 3, 1, 2, 4]
Input 2:

A = [1, 2]


Example Output
Output 1:

[3, 4]
Output 2:

[1, 2]


Example Explanation
Explanation 1:

 3 and 4 appear only once.
Explanation 2:

 1 and 2 appear only once.
     */
    /*
    If extra memory is used, we can directly store the count and find the elements which occur only once.

To solve without extra memory
We can use the xor operation , as the xor of two elements gives 0. Take the xor of all the elements of the array. Elements
which occurs twice will not contribute anything to the xor value.

Suppose that the ith bit is set in the xor value which means that exactly one of the two required number has that bit set.

If we the divide the array elements int two groups: one group contain all elements which have the ith bit set and the other group
contains elements which have 0 at the ith bit.

Each group contains one of the two required numbers and for other numbers both of their occurrences will be in the same group.

Now, Xor of elements in the first group gives a number which occurs exactly once.
Xor of elements in the second group gives another number which occurs exactly once.
     */
    public int[] solve(int[] A) {
        int[] res = new int[2];
        int sumXOR = 0;

        for(int i=0;i <A.length;i++){
            sumXOR = sumXOR ^ A[i];
        }

        // From the final result find out the most right most set bit.

        sumXOR = (sumXOR & -sumXOR);

        int num1 = 0;
        int num2 = 0;

        for(int j=0; j<A.length;j++){

            // Check if one of the 2 elements has 1 at the set bit.
            if((A[j] & sumXOR) >0){
                num1 = num1 ^ A[j];
            }
            else{
                num2 = num2 ^ A[j];
            }
       }
        res[0] = Math.min(num1,num2);
        res[1] = Math.max(num1,num2);
        return res;
    }

    public static void main(String[] args) {
        SingleNumber3 sn3 = new SingleNumber3();
        sn3.solve(new int[]{1, 2, 3, 1, 2, 4});
    }

}
