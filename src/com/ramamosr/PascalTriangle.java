package com.ramamosr;

public class PascalTriangle {
    /*
    Pascal Triangle
Problem Description

Write a program to input an integer N from user and print pascal triangle up to N rows.
Problem Constraints
1 <= N <= 25

input Format
First line is an integer N.

output Format
N lines whose each row contains N+1 space separated integers.

Example Input
Input 1:

3
Input 2:

5

Example Output
Output 1:

1 0 0
1 1 0
1 2 1
Output 2:

1 0 0 0 0
1 1 0 0 0
1 2 1 0 0
1 3 3 1 0
1 4 6 4 1

Example Explanation
Explanation 1:

Row 1 = 1 0 0 0 0
Row 2 = 1C0 1C1 0 0 0= 1 1 0 0 0
Row 3 = 2C0 2C1 2C2 0 0= 1 2 1 0 0
Row 4 = 3C0 3C1 3C2 3C3 0= 1 3 3 1 0
     */
    public int[][] pascalTriangle(int A) {
        int[][] resultArr = new int[A][A];
        if (A == 0)
            return resultArr;
        resultArr[0][0] = 1;
        for(int i =0; i<A; i++){
            for(int j= 0;j<=i;j++){
                //resultArr[i][j] = factorial(i)/(factorial(j)*(factorial(i-j)));
                if (j == 0 || j == i) resultArr[i][j] = 1;
                else{
                    resultArr[i][j] = resultArr[i-1][j] + resultArr[i-1][j-1];
                }
            }
        }
        return resultArr;
    }

    public int factorial(int N){
        int result = 1;
        while(N > 0){
            result =  N * result;
            N--;
        }
        return result;
    }
    public static void main(String[] args) {
        PascalTriangle pt = new PascalTriangle();
        //System.out.println(pt.factorial(0));
        pt.pascalTriangle(25);
    }
}
