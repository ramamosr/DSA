package com.ramamosr;

public class BitManipulation {

    /*
    Number of 1 Bits
    Problem Description
    Write a function that takes an integer and returns the number of 1 bits it has.
    Problem Constraints
    1 <= A <= 109
    Input Format
    First and only argument contains integer A
    Output Format
    Return an integer as the answer
    Example Input
    Input1:
        11
    Example Output
    Output1:
    3
     */
    public int countSetBits(int A){
        int result = 0;
        while(A>0){
            result = result + (A & 1);
            A=A>>1;
        }
        return result;
    }

    /*Given an array of integers A, every element appears twice except for one. Find that single one.
    NOTE: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
    Problem Constraints
        2 <= |A| <= 2000000
        0 <= A[i] <= INTMAX
    Input Format
    First and only argument of input contains an integer array A.
            Output Format
    Return a single integer denoting the single element.
    Example Input
    Input 1:
    A = [1, 2, 2, 3, 1]
    Input 2:
    A = [1, 2, 2]
    Example Output
    Output 1:
            3
    Output 2:
            1
    Example Explanation
    Explanation 1:
            3 occurs once.
    Explanation 2:
            1 occurs once.*/
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int singleNumber(final int[] A) {
        int result = 0;
        for(int i=0;i<A.length;i++){
            result = result ^ A[i];
        }
        return result;
    }

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
    public int countTotalBitsInArray(int A){
        int finalResult = 0;
        for(int i=1; i<=A;i++){
            finalResult = finalResult + countSetBits(i);
        }
        return finalResult;
    }

    /*Add Binary Strings
    Given two binary strings, return their sum (also a binary string).
    Example:
    a = "100"
    b = "11"
    Return a + b = "111".*/

    public String addBinaryStrings(String A, String B){

        int aL = A.length()-1;
        int bL = B.length()-1;
        String result = "";
        int sum = 0;
        while(aL>=0 || bL>=0 || sum==1){
            sum = sum + ((aL>=0)?A.charAt(aL)-'0':0);
            sum = sum + ((bL>=0)?B.charAt(bL)-'0':0);
            result = (char)(sum%2 + '0') + result;
            sum = sum/2;
            aL--;bL--;
        }

    return result;
    }


    public static void main(String[] args) {
        BitManipulation bitMan = new BitManipulation();
        //System.out.println(bitMan.countSetBits(8));
        int[] singleNumberArr = {1,1,3,3,2,4,5,5,4};
        //System.out.println(bitMan.singleNumber(singleNumberArr));
        //System.out.println((bitMan.countTotalBitsInArray(3)));
        System.out.println(bitMan.addBinaryStrings("111","11"));
    }
}
