package com.ramamosr.backtracking;

import java.util.ArrayList;

public class UniquePathsIII {
    /*
    Problem Description

Given a matrix of integers A of size N x M . There are 4 types of squares in it:

1. 1 represents the starting square.  There is exactly one starting square.
2. 2 represents the ending square.  There is exactly one ending square.
3. 0 represents empty squares we can walk over.
4. -1 represents obstacles that we cannot walk over.
Find and return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

Note: Rows are numbered from top to bottom and columns are numbered from left to right.



Problem Constraints
2 <= N * M <= 20
-1 <= A[i] <= 2



Input Format
The first argument given is the integer matrix A.



Output Format
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.



Example Input
Input 1:

A = [   [1, 0, 0, 0]
        [0, 0, 0, 0]
        [0, 0, 2, -1]   ]
Input 2:

A = [   [0, 1]
        [2, 0]    ]


Example Output
Output 1:

2
Output 2:

0


Example Explanation
Explanation 1:

We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Explanation 1:

Answer is evident here
     */

    int ans = 0;
    int zeroCount = 0;

    public boolean isValidMove(int[][] A, int rowIndex, int colIndex, int rows, int cols, boolean[][] visited){

        if(rowIndex <0 || rowIndex>= rows || colIndex <0 ||
                colIndex>=cols || visited[rowIndex][colIndex] || A[rowIndex][colIndex] == -1 )
            return false;
        return true;
    }

    public void findUniquePaths(int[][] A, int rowIndex, int colIndex, int rows, int cols, boolean[][] visitedCells,int cntZero){
        // the first condition checks for the validity of the first placement of 0,0
        // from the driver method
        if(rowIndex <0 || rowIndex>= rows || colIndex <0 ||
                colIndex>=cols || visitedCells[rowIndex][colIndex] || A[rowIndex][colIndex] == -1 )
            return;

        // if we reach the column where the end cell which is marked as 2, we have reached the destination.
        // Add to the count.
        if(A[rowIndex][colIndex]==2 && cntZero-1 == zeroCount){
            ans++;
            return;
        }
        // first mark the cell as visited.
        visitedCells[rowIndex][colIndex] = true;

        // first move to the next row move down
        if(isValidMove(A,rowIndex+1,colIndex,rows,cols,visitedCells)){
            findUniquePaths(A,rowIndex+1,colIndex,rows,cols,visitedCells,cntZero+1);
        }

        // move to the prev column move to the left
        if(isValidMove(A,rowIndex,colIndex-1,rows,cols,visitedCells)){
            findUniquePaths(A,rowIndex,colIndex-1,rows,cols,visitedCells,cntZero+1);
        }

        // move to the right
        if(isValidMove(A,rowIndex,colIndex+1,rows,cols,visitedCells)){
            findUniquePaths(A,rowIndex,colIndex+1,rows,cols,visitedCells,cntZero+1);
        }
        // move to the prev row or towards the top
        if(isValidMove(A,rowIndex-1,colIndex,rows,cols,visitedCells)){
            findUniquePaths(A,rowIndex-1,colIndex,rows,cols,visitedCells,cntZero+1);
        }
        visitedCells[rowIndex][colIndex] = false;

    }

    public int solve(int[][] A) {
        boolean[][] visitedCells = new boolean[A.length][A[0].length];
        int startCol = 0, startRow = 0;
        for(int i=0; i< A.length;i++){
            for(int j=0; j<A[0].length;j++){
                if(A[i][j]==1){
                    startRow = i;
                    startCol = j;
                }
                if(A[i][j]==0)
                    zeroCount++;
            }
        }
        findUniquePaths(A,startRow,startCol,A.length,A[0].length,visitedCells,0);
        return ans;
    }

    public static void main(String[] args) {
        UniquePathsIII up = new UniquePathsIII();
        int[][] A = {{1, 0, 0, 0},{0, 0, 0, 0},{0, 0, 2, -1}};
        //int[][] A = {{0,1},{2,0}};
        up.solve(A);
    }
}
