package com.ramamosr.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class GrayCode {
    /*
    Problem Description

    https://cp-algorithms.com/algebra/gray-code.html

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer A representing the total number of bits in the code, print the sequence of gray code.

A gray code sequence must begin with 0.



Problem Constraints
1 <= A <= 16



Input Format
First argument is an integer A.



Output Format
Return an array of integers representing the gray code sequence.



Example Input
Input 1:

A = 2
Input 1:

A = 1


Example Output
output 1:

[0, 1, 3, 2]
output 2:

[0, 1]


Example Explanation
Explanation 1:

for A = 2 the gray code sequence is:
    00 - 0
    01 - 1
    11 - 3
    10 - 2
So, return [0,1,3,2].
Explanation 1:

for A = 1 the gray code sequence is:
    00 - 0
    01 - 1
So, return [0, 1].
     */

    /*
    Solution Approach
    Let G(n) represent a gray code of n bits.
Note that reverse of G(n) is also a valid sequence.
Let R(n) denote the reverse of G(n).

Note that we can construct
G(n+1) as the following :
0G(n)
1R(n)

Where 0G(n) means all elements of G(n) prefixed with 0 bit and 1R(n) means all elements of R(n) prefixed with 1.
Note that last element of G(n) and first element of R(n) is same. So the above sequence is valid.

Example G(2) to G(3) :
0 00
0 01
0 11
0 10
1 10
1 11
1 01
1 00
     */
    int num;
    public void getGrayCode(ArrayList<Integer> result, int A){

        if(A==0){
            result.add(num);
            return;
        }
        // make the choice to keep the number as it is.
        getGrayCode(result,A-1);

        //Invert the number
        num = num ^ (1 << (A-1));
        getGrayCode(result,A-1);
    }

    public ArrayList<Integer> grayCode(int a) {
        num = 0;
        ArrayList<Integer> result = new ArrayList<>();
        getGrayCode(result,a);
        return result;
    }
    public ArrayList < Integer > grayCodeScaler(int A) {
        int n = A;
        ArrayList < Integer > result = new ArrayList < > ();
        result.add(0);
        for (int i = 0; i < n; i++) {
            int curSize = result.size();
            // push back all element in result in reverse order
            for (int j = curSize - 1; j >= 0; j--) {
                result.add(result.get(j) + (1 << i));
            }
        }
        return result;
    }
    public static void main(String[] args) {
        GrayCode gc = new GrayCode();
        gc.grayCode(4);
    }
}

