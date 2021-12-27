package com.ramamosr.Hashing;

import java.util.HashMap;

public class CountRightTriangles {
    /*
    Problem Description

Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in 2D Cartesian plane.

Find and return the number of unordered triplets (i, j, k) such that (A[i], B[i]), (A[j], B[j]) and (A[k], B[k]) form a right angled triangle with the triangle having one side parallel to the x-axis and one side parallel to the y-axis.

NOTE: The answer may be large so return the answer modulo (109 + 7).



Problem Constraints

1 <= N <= 105

0 <= A[i], B[i] <= 109



Input Format

The first argument given is an integer array A.
The second argument given is the integer array B.



Output Format

Return the number of unordered triplets that form a right angled triangle modulo (109 + 7).



Example Input

Input 1:

 A = [1, 1, 2]
 B = [1, 2, 1]
Input 2:

 A = [1, 1, 2, 3, 3]
 B = [1, 2, 1, 2, 1]


Example Output

Output 1:

 1
Output 2:

 6


Example Explanation

Explanation 1:

 All three points make a right angled triangle. So return 1.
Explanation 2:

 6 quadruplets which make a right angled triangle are: (1, 1), (1, 2), (2, 2)
                                                       (1, 1), (3, 1), (1, 2)
                                                       (1, 1), (3, 1), (3, 2)
                                                       (2, 1), (3, 1), (3, 2)
                                                       (1, 1), (1, 2), (3, 2)
                                                       (1, 2), (3, 1), (3, 2)

     */

    public int solve(int[] A, int[] B) {

        int mod = 1000000007;
        long result = 0;

        // Create two hashmaps to find all points that have the same X or Y axis points.
        // For a line parallel to X axis both Y points will be the same.
        // for a line parallel to Y axis both X points will be the same.
        // the total number of triangles will be
        // freq(X points) - 1 * fre(YPoints)-1
        HashMap<Integer,Integer> xAxis = new HashMap<>();
        HashMap<Integer,Integer> yAxis = new HashMap<>();
        int n = A.length;

        for(int i=0; i<n;i++){
            if(xAxis.containsKey(A[i]))
                xAxis.put(A[i],xAxis.get(A[i]) + 1);
            else
                xAxis.put(A[i],1);
            if(yAxis.containsKey(B[i]))
                yAxis.put(B[i],yAxis.get(B[i]) + 1);
            else
                yAxis.put(B[i],1);
        }

        for(int i=0;i<n;i++){
            if(xAxis.get(A[i]) > 0 && yAxis.get(B[i]) >0 ){
                result+= (long)((xAxis.get(A[i]) - 1) * (yAxis.get(B[i]) - 1));
            }
        }
        return (int)(result%mod);
    }


    public static void main(String[] args) {
        CountRightTriangles crt = new CountRightTriangles();
        int[] A = {1, 1, 2, 3, 3};
        int[] B = {1, 2, 1, 2, 1};
        System.out.println(crt.solve(A,B));
    }
}
