package com.ramamosr.DynamicProgramming;

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
    Doing things in place can be slightly trickier.

Note that if you create a graph with each cell as vertex with an edge from source cell to the destination cell, the graph ends up with cycles of length 4.

Which means something like this should work:

Divide the array into 4 along the diagonals
Place each element in the top quadrant into the slot 90 degrees clockwise
Place the old 90 in 180 degrees clockwise
Place the old 180 in 270 degrees
Lastly, place the old 270 in the original place
It might be easier to understand the solution if a few examples are tried out by hand.
Let me elaborate on a 3x3 example. You can try more examples of other size.
Lets say the array is

[
1 2 3
4 5 6
7 8 9
]
After rotation by 90 degree, it should be

[
7 4 1
8 5 2
9 6 3
]
Lets say you were allowed extra space and not required to do things in place.

Easier approach :
If you notice, if you read out the column ‘i’ in reverse order, it corresponds to the new row ‘i’ in resulting array. So, column 0 in original array read out in reverse order is 7 4 1 which is row 0 in answer. And so on. So you just simulate this approach and keep creating the result in another 2 D array.

However, this approach requires additional space of O(n^2) where n = number of rows in 2D array.

Now lets say we have to do things in place ( no extra space allowed ). This implies that we have to make things work with just moving elements around with constant extra memory.
So, lets try to observe where elements need to end up in the result array.

* 7 needs to end up in 1's position.
* If 7 goes to 1's position, then we need to check where 1 needs to go otherwise value 1 will be lost. 1 needs to go to 3's position.
* 3 needs to go to 9's position.
* 9 needs to go to 7's position.
* We already have placed 7 in 1's position.
So when we move these 4 elements, all 4 elements are in their correct position.

Lets look at 4 now.

4 -> 2 -> 6 -> 8.
Again, a group of 4. So, we can move these elements group by group without requiring creating a copy of the array.

You can try a few more examples to convince yourself.

The code just tries to simulate whats being discussed here.
     */

    public int[] solve(int[][] A, int[] B, int[] C, int[] D, int[] E) {
        int n = A.length, m = A[0].length, mod = 1000000007;
        int sum = 0;
        long preSum[][] = new long[n+1][m+1];
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
        com.ramamosr.PS4.SubMatrixSumQueries sbsm = new com.ramamosr.PS4.SubMatrixSumQueries();
        int[][] A = new int[][]{{1, 2, 3},{4, 5, 6},{7, 8, 9}};
        int[] B = {1, 2 };
        int[] C = {1, 2};
        int[] D = {2, 3};
        int[] E = {2,3};
        sbsm.solve(A,B,C,D,E);
    }
}
