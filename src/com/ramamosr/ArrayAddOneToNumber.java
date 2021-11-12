package com.ramamosr;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayAddOneToNumber {
    /*
    Add One To Number
Problem Description

Given a non-negative number represented as an array of digits, add 1 to the number ( increment the number represented by the digits ).

The digits are stored such that the most significant digit is at the head of the list.

NOTE: Certain things are intentionally left unclear in this question which you should practice asking the interviewer. For example: for this problem, following are some good questions to ask :

Q : Can the input have 0's before the most significant digit. Or in other words, is 0 1 2 3 a valid input?
A : For the purpose of this question, YES
Q : Can the output have 0's before the most significant digit? Or in other words, is 0 1 2 4 a valid output?
A : For the purpose of this question, NO. Even if the input has zeroes before the most significant digit.


Problem Constraints
1 <= size of the array <= 1000000



Input Format
First argument is an array of digits.



Output Format
Return the array of digits after adding one.



Example Input
Input 1:

[1, 2, 3]


Example Output
Output 1:

[1, 2, 4]


Example Explanation
Explanation 1:

Given vector is [1, 2, 3].
The returned vector should be [1, 2, 4] as 123 + 1 = 124.
     */

    public int[] plusOne(int[] A){
        if(A.length==0)
            return A;
        if(A.length==1 && A[0]==0){
            A[0] = 1;
            return A;
        }
        int len = A.length;
        int startIndex = 0;
        int carry = 0;
        for(int i =0; i<A.length;i++){
            if(A[i]==0)
                startIndex++;
            else
                break;
        }
        int[] result = new int[A.length-startIndex];

        if(A[len-1]==9){
            carry =1;
        }
        result[result.length-1] = (A[len-1] +1)%10;

        for(int j = len-2;j>=startIndex&& result.length >1;j--){
            result[j-startIndex] = A[j] + carry;
            carry = (A[j]+carry)/10;
            result[j-startIndex] = (result[j-startIndex])%10;
        }
        int[] finalresult;
        if(carry>0) {
            finalresult = new int[result.length + 1];
            finalresult[0] = carry;
            for(int k=0;k<result.length;k++){
                finalresult[k+1] = result[k];
            }
            return finalresult;
        }
        return result;
    }
    public static void main(String[] args) {
        ArrayAddOneToNumber addOne = new ArrayAddOneToNumber();
        addOne.plusOne(new int[] {1, 9, 9, 9, 9, 9, 9});
    }
}
