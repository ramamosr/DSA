package com.ramamosr.MathProb;

public class GridUniquePaths {
    /*
    Grid Unique Paths
Problem Description

A robot is located at the top-left corner of an A x B grid (marked 'Start' in the diagram below).



The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?



Problem Constraints
A and B will be such that the resulting answer fits in a 32 bit signed integer.



Input Format
First argument of input will be single integer A.
Second argument of input will be single integer B.



Output Format
Return a single integer denoting the number of unique paths.



Example Input
 A = 2, B = 2


Example Output
 2


Example Explanation
 2 possible routes : (0, 0) -> (0, 1) -> (1, 1)
              OR  : (0, 0) -> (1, 0) -> (1, 1)
     */

    /*
    TA Suggestion
    The way you are is correct but it can be optimized further by using maths:-



 Note that you have to take m + n - 2 steps in total. You have to take (m - 1) steps going down and (n-1) steps going right.

 Let 0 denote a down step and 1 denote a right step.

 So we need to find out the number of strings of length m + n - 2 which have exactly m - 1 zeroes and n - 1 ones.

 Essentially we need to choose the positions of ‘1s’, and then ‘0s’ fall into the remaining positions.

 So, the answer becomes Choose(m+n-2, n - 1).

Just implement this :-

(m+n-2) C (n-1) = (m+n-2)! / (n-1)! (m-1)!
     */
    // Iterative approach
    public int uniquePathsOld(int A, int B) {
        int count[][] = new int[A][B];

        if(A==1 || B==1)
            return 1;
        //pre populate for 1st row there is only one path for any column in the first row.
        // Always move right
        for(int i =0; i<A;i++)
            count[i][0] = 1;

        // Similary for the first column across all rows, there is only 1 unique path.

        for(int i =0; i<B;i++)
            count[0][i] = 1;

        // Now calcualte the unique path for every cell in the matrix.

        for(int i=1;i<A;i++){
            for(int j=1; j<B;j++){
                // add the values of the cells one left and one above which forms a diagonal.
                // if the robot moves diagonally, include the count of the diagonal cell as well
                // which will count[i-1][j-1]
                count[i][j] = count[i-1][j] + count[i][j-1];
            }
        }
        return count[A-1][B-1];
    }


    //Dynamic Programming
    public int uniquePathsWithDP(int A, int B){
        // initialize result array with lenth of the column or row. We have taken column here
        int[] res = new int[B];
        //As in the old way for the first row & first column we can assume the number of unique paths is always 1.
        // So when 0,0 or 1,0 or 0,1 the path is always 1. Lets assume 0,0 is 1.
        // so 1,1 will be
        // the path to 1,1 is either thru 1,0 && 1,1 or 0,1 && 1,1.
        // we can add eiter one of those values to this 1,1 which will be the value of the column.
        // if we assume the start value for all the arrays is 1, we can add the values from the prev column value.
        res[0] = 1;
        for(int i= 0;i <A;i++) {
            for (int j = 1; j < B; j++)
                res[j] = res[j] + res[j - 1];
        }
        return res[B-1];
    }
// wait for combinatrics class
    public int uniquePaths(int A,int B){
        // Follow the permutation Combination formula
        //nCr -  N is the total possible steps that can be taken.
        // r represents the directions either right or down.
        // in our case, the total paths will be A+B-2
        // explanation for A+B-2 is EXCLUDING THE starting point, the robot has to traverse
        // A-1 steps to the right and B-1 steps to the bottom so A-1+B-1 becomes A+B-2

        /*
        Shortcut for getting the NCr calculation is if you want to calculate 10C3
        take the last 3 digits as the numerator and first 3 digits as denominator.
        for 10C3 it will be 8*9*10/3*2*1
        apply the same logic here. loop from 1 to R and divide by the number.
         */

        int N = A+B-2;
        int r = (A < B?A:B) -1;
        long result = 1;
        for(int j=1;j<=r;j++){
            result = result * (N-r+j)/j;
        }
        return (int) result;
    }

    //Recursive approach
    public int countPaths(int i, int j, int m, int n){
        if(i==m-1 && j==n-1) return 1;
        if(i>=m || j>=n) return 0;
        return countPaths(i,j+1,m,n) + countPaths(i+1,j,m,n);
    }

    public static void main(String[] args) {
        GridUniquePaths gup = new GridUniquePaths();
        System.out.println(gup.uniquePathsOld(15,9));
        System.out.println(gup.uniquePathsWithDP(15,9));
        System.out.println(gup.uniquePaths(15,9));
        System.out.println(gup.countPaths(0,0,15,9));
    }
}
