package com.ramamosr.backtracking;

public class RatInAMaze {

    /*
    Problem Description

Given a grid A, a rat is at position (1, 1). He wants to go to the position (n, n) where n is size of the square matrix A.

1 represents a traversable cell and 0 represents a non-traversable cell. Rat can only move right or down.

Return a path that the rat can take.



Problem Constraints

1 <= |A| <= 4



Input Format

First and only argument is the vector of vectors A.



Output Format

Return a vector of vectors that denotes a path that can be taken.



Example Input

Input 1:

A = [   [1, 0]
        [1, 1]
    ]
Input 2:

A = [    [1, 1, 1]
         [1, 0, 1]
         [0, 0, 1]
    ]


Example Output

Output 1:

[   [1, 0]
    [1, 1]
]
Output 2:

[    [1, 1, 1]
     [0, 0, 1]
     [0, 0, 1]
]


Example Explanation

Explanation 1:

 Path is shown in output.
Explanation 2:

 Path is shown in output.
     */
  
  /*
  Create a solution matrix, initially filled with 0’s.
Create a recursive funtion, which takes initial matrix, output matrix and position of rat (i, j).
if the position is out of the matrix or the position is not valid then return.
Mark the position output[i][j] as 1 and check if the current position is destination or not. If destination is reached print the output matrix and return.
Recursively call for position (i+1, j) and (i, j+1).
Unmark position (i, j), i.e output[i][j] = 0.
    public int[][] solve(int[][] A) {
        int N = A.length;
        int M = A[0].length;
        int[][] path = new int[N][M];
        move(0, 0, A, path, N, M);
        return path;
    }

    public boolean move(int i, int j, int[][] A, int[][] path, int n, int m) {

        // base condition check if we have reached the bottom right corner
        if (i + 1 == n && j + 1 == m) {
            path[i][j] = A[i][j];
            return true;
        }

        // check if the move is valid or not.
        if (i < 0 || j < 0 || i >= n || j >= m || A[i][j] == 2 || A[i][j] == 0) {
            return false;
        }
        path[i][j] = A[i][j];
        // mark the path as visited
        A[i][j] = 2;

        if (move(i + 1, j, A, path, n, m) || move(i, j + 1, A, path, n, m)) {
            return true;
        }
        // revert back to original state as we are backtracking
        // mark path value as 0 and mark the position as unvisited.
        path[i][j] = 0;
        A[i][j] = 1;
        return false;
    }



    public static void main(String[] args) {
        int[][] A ={{1, 1, 1},{1, 0, 1},{0, 0, 1}};

        RatInAMaze rim = new RatInAMaze();
        rim.solve(A);
    }
}
