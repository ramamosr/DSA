package com.ramamosr;

public class MultiplicationPreviousNextArray {

    /*
    Multiplication of previous and next
Given an array of integers A, update every element with multiplication of previous and next elements with following exceptions. a) First element is replaced by multiplication of first and second. b) Last element is replaced by multiplication of last and second last.


Input Format

The only argument given is the integer array A.
Output Format

Return the updated array.
Constraints

1 <= length of the array <= 100000
-10^4 <= A[i] <= 10^4
For Example

Input 1:
    A = [1, 2, 3, 4, 5]
Output 1:
    [2, 3, 8, 15, 20]

Input 2:
    A = [5, 17, 100, 11]
Output 2:
    [85, 500, 187, 1100]


     */
    public int[] solve(int[] A) {
        if(A.length<=1)
            return A;
        else{
            int first = A[0];
            int current = 0;
            A[0] = A[0] * A[1];
            for(int i=1;i <A.length-1;i++){
                current = A[i];
                A[i] = first * A[i+1];
                first = current;
            }
            A[A.length-1] = current * A[A.length-1];
        }
        return A;
    }

    public static void main(String[] args) {
        MultiplicationPreviousNextArray mpn = new MultiplicationPreviousNextArray();
        System.out.println(mpn.solve(new int[]{1, 2, 3, 4, 5}));
    }
}
