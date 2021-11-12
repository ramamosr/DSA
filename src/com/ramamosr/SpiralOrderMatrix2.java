package com.ramamosr;

public class SpiralOrderMatrix2 {
    /*
    Spiral Order Matrix II
Problem Description

Given an integer A, generate a square matrix filled with elements from 1 to A2 in spiral order.
Problem Constraints
1 <= A <= 1000

Input Format
First and only argument is integer A
Output Format
Return a 2-D matrix which consists of the elements in spiral order.
Example Input
Input 1:
1
Input 2:
2

Example Output
Output 1:
[ [1] ]
Output 2:
[ [1, 2], [4, 3] ]

Example Explanation
Explanation 1:

Only 1 is to be arranged.
Explanation 2:

1 --> 2
      |
      |
4<--- 3
     */
    public int[][] generateMatrix(int A) {
        int[][] result = new int[A][A];
        int currentVal = 1;
        int top = 0, right= A-1,bottom =A-1,left = 0;
        while(top <= bottom && left <= right){

            for(int i=left;i<=right;i++){
                result[top][i] = currentVal++;
                //System.out.println(A[top][i]);
            }
            top++;
            for(int i=top;i<=bottom;i++){
                //System.out.println(A[i][right]);
                result[i][right] = currentVal++;
            }
            right--;

            for(int i=right;i>=left;i--){
               // System.out.println(A[bottom][i]);
                result[bottom][i] = currentVal++;
            }
            bottom--;

            for(int i=bottom;i>=top;i--){
               // System.out.println(A[i][left]);
                result[i][left] = currentVal++;
            }
            left++;

        }
        return result;
    }
    public static void main(String[] args) {
        SpiralOrderMatrix2 sp2 = new SpiralOrderMatrix2();
        int[][] result = sp2.generateMatrix(4);

    }
}
