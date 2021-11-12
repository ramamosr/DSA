package com.ramamosr.PS4;

public class SubMatrixSumQueries {
    /*
    Sub-matrix Sum Queries
Problem Description

Given a matrix of integers A of size N x M and multiple queries Q, for each query find and return the submatrix sum.
Inputs to queries are top left (b, c) and bottom right (d, e) indexes of submatrix whose sum is to find out.

NOTE:
Rows are numbered from top to bottom and columns are numbered from left to right.
Sum may be large so return the answer mod 109 + 7.

Problem Constraints
1 <= N, M <= 1000
-100000 <= A[i] <= 100000
1 <= Q <= 100000
1 <= B[i] <= D[i] <= N
1 <= C[i] <= E[i] <= M



Input Format
The first argument given is the integer matrix A.
The second argument given is the integer array B.
The third argument given is the integer array C.
The fourth argument given is the integer array D.
The fifth argument given is the integer array E.
(B[i], C[i]) represents the top left corner of the i'th query.
(D[i], E[i]) represents the bottom right corner of the i'th query.



Output Format
Return an integer array containing the submatrix sum for each query.



Example Input
Input 1:

 A = [   [1, 2, 3]
         [4, 5, 6]
         [7, 8, 9]   ]
 B = [1, 2]
 C = [1, 2]
 D = [2, 3]
 E = [2, 3]
Input 2:

 A = [   [5, 17, 100, 11]
         [0, 0,  2,   8]    ]
 B = [1, 1]
 C = [1, 4]
 D = [2, 2]
 E = [2, 4]


Example Output
Output 1:

 [12, 28]
Output 2:

 [22, 19]


Example Explanation
Explanation 1:

 For query 1: Submatrix contains elements: 1, 2, 4 and 5. So, their sum is 12.
 For query 2: Submatrix contains elements: 5, 6, 8 and 9. So, their sum is 28.
Explanation 2:

 For query 1: Submatrix contains elements: 5, 17, 0 and 0. So, their sum is 22.
 For query 2: Submatrix contains elements: 11 and 8. So, their sum is 19.
     */

    /*
    First prepare the prefix sum matrix for the given matrix.
    All all the column and row level values.
    for matrix
                1   2
                3   4

         the value for 4 will be come 4+3+2+1 10
         2 will be 2, 3 will become 4.
         the values in the first row will not change.
         so copy the first row as it is to the new matrix.
     */
    public int[] solve(int[][] A, int[] B, int[] C, int[] D, int[] E) {
        int[] result = new int[B.length];
        int[][] PS = new int[A.length][A[0].length];
        PS[0] = A[0];
        int mod = (1000 * 1000 * 1000) + 7;

        // Sum row wise from row 1 to M.
        // take the sum of prev row from PS matrix and add to the current value from original matrix.
        for(int i = 1; i<A.length;i++){
            for(int j=0; j<A[i].length;j++) {
                PS[i][j] = Math.floorMod (PS[i-1][j] + A[i][j],mod);
            }
        }

        // sum column wise from col 1 to N and add to the current value of PS matrix.
        // because as per the row wise sum, the values are updated every row with all column values from
        //original matrix.
        for(int row = 0;row<A.length;row++){
            for(int col = 1; col<A[row].length;col++){
                PS[row][col] = Math.floorMod (PS[row][col] + PS[row][col-1],mod) ;
            }
        }
        for(int i =0; i< B.length;i++) {
            int leftRowIndex = B[i]-1;
            int lefColIndex = C[i]-1;
            int rightRowIndex = D[i]-1;
            int rightColIndex = E[i]-1;

            // initiate the current sum as the right most ps array [row,col]
            long sum = PS[rightRowIndex][rightColIndex];

            // if left Row index is greater than zero reduce the prefix sum from the rightrow index
            if(leftRowIndex >0)
                sum = sum - PS[leftRowIndex-1][rightColIndex];

            if(lefColIndex > 0)
                sum = sum - PS[rightRowIndex][lefColIndex-1];
                //sum = sum-A[rightRowIndex][lefColIndex-1];
            // Now the left column value would have been reduced more than intended.
            // the value at (leftRow-1,leftCold-1) was part of both columnwise pS and row wise PS
            // and it would have been deducted twice.
            // so add that back.
            if(leftRowIndex > 0 && lefColIndex >0)
                sum = sum + PS[leftRowIndex-1][lefColIndex-1];
            result[i] = (int)Math.floorMod(sum,mod);
        }
        return result;
    }
    /*
    The idea is to first create an auxiliary matrix arr[N+1][M+1] such that arr[i][j] stores sum of elements in submatrix from (0,0) to (i,j).
Once arr[][] is constructed, we can compute sum of submatrix between (x1, y1) and (x2, y2) in O(1) time.
We need to consider arr[x2][y2] and subtract all unncessary elements.
Below is complete expression to compute submatrix sum in O(1) time.

Sum between (x1, y1) and (x2, y2) is,
arr[x2][y2] - arr[x2][y1-1] - arr[x1-1][y2] + arr[x1-1][y1-1]

The submatrix aux[x1-1][x2-1] is added because elements of it are subtracted twice.
Remeber to return the ans%1000000007
     */
    public int[] solveScaler(int[][] A, int[] B, int[] C, int[] D, int[] E) {
        int n = A.length, m = A[0].length, mod = 1000000007;
        int sum = 0;
        long preSum[][] = new long[n+1][m+1];
        //preSum[i][j] denotes the sum of elements of matirx (0, 0) to (i, j)
        for(int i = 1; i <= n ; i++){
            for(int j = 1; j <= m; j++){
                preSum[i][j] = (A[i-1][j-1] + preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + mod) % mod;
            }
        }
        int res[] = new int[B.length];
        for(int i = 0; i < B.length; i++){
            int x1 = B[i], y1 = C[i], x2 = D[i], y2 = E[i];
            long ans = preSum[x2][y2] - preSum[x2][y1-1] - preSum[x1-1][y2] + preSum[x1-1][y1-1];
            while(ans < 0)
                ans += mod;
            ans = ans % mod;
            res[i] = (int)ans;
        }
        return res;
    }
    public static void main(String[] args) {
        SubMatrixSumQueries sbsm = new SubMatrixSumQueries();
        int[][] A = new int[][]{{1, 2, 3},{4, 5, 6},{7, 8, 9}};
        int[] B = {1, 2 };
        int[] C = {1, 2};
        int[] D = {2, 3};
        int[] E = {2,3};
        sbsm.solve(A,B,C,D,E);
    }
}
