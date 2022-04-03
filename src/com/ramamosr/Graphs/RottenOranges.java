package com.ramamosr.Graphs;

import java.util.Queue;
import java.util.LinkedList;

public class RottenOranges {

    /*
    Problem Description

Given a matrix of integers A of size N x M consisting of 0, 1 or 2.

Each cell can have three values:

The value 0 representing an empty cell.

The value 1 representing a fresh orange.

The value 2 representing a rotten orange.

Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom) to a rotten orange becomes rotten. Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1 instead.

Note: Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.



Problem Constraints

1 <= N, M <= 1000

0 <= A[i][j] <= 2



Input Format

The first argument given is the integer matrix A.



Output Format

Return the minimum number of minutes that must elapse until no cell has a fresh orange.

If this is impossible, return -1 instead.



Example Input

Input 1:

A = [   [2, 1, 1]
        [1, 1, 0]
        [0, 1, 1]   ]
Input 2:


A = [   [2, 1, 1]
        [0, 1, 1]
        [1, 0, 1]   ]


Example Output

Output 1:

 4
Output 2:

 -1


Example Explanation

Explanation 1:

 Max of 4 using (0,0) , (0,1) , (1,1) , (1,2) , (2,2)
Explanation 2:

 Task is impossible
     */

    /*
    Every turn, the rotting spreads from each rotting orange to other adjacent oranges.
Initially, the rotten oranges have ‘depth’ 0, and every time they rot a neighbor,
the neighbors have 1 more depth. We want to know the largest possible depth.

Use multi-source BFS to achieve this with all cells containing rotten oranges as starting nodes.
At the end check if there are fresh oranges left or not.

    */

    class Pair {
        // coordinate of orange
        int i;
        int j;
        // min distance to get rotten
        int minDist;

        public Pair(int i, int j, int minDist) {
            this.i = i;
            this.j = j;
            this.minDist = minDist;
        }
    }

    public int solve(int[][] A) {

        int n = A.length;
        int m = A[0].length;
        int[][] minDistance = new int[n][m];
        // Queue for BFS traversal
        Queue<Pair> queue = new LinkedList<Pair>();

        // fill queue with rotten oranges first
        // initialize the minDistance array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 2) {
                    minDistance[i][j] = 0;
                    // store the initial rotten oranges in Queue
                    queue.add(new Pair(i, j, 0));
                } else if (A[i][j] == 0) {
                    // initialize empty spaces with INT_MIN
                    minDistance[i][j] = Integer.MIN_VALUE;
                } else {
                    minDistance[i][j] = -1;
                }
            }
        }

        // allowed directions
        int[] rows = { -1, 0, 0, 1 };
        int[] cols = { 0, -1, 1, 0 };

        while (!queue.isEmpty()) {

            Pair x = queue.poll();

            // new distance
            int dist = x.minDist + 1;

            for (int k = 0; k < rows.length; k++) {
                int new_i = x.i + rows[k];
                int new_j = x.j + cols[k];

                // insert into queue only when orange is fresh and valid coordinates are present
                if (new_i >= 0 && new_j >= 0 && new_i < n && new_j < m && A[new_i][new_j] == 1) {
                    // mark the orange as rotten
                    A[new_i][new_j] = 2;
                    // add new rotten orange to queue
                    queue.add(new Pair(new_i, new_j, dist));
                    // update distance
                    minDistance[new_i][new_j] = dist;
                }
            }
        }

        // find max element from matrix
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (minDistance[i][j] == -1) {
                    // if it is not possible to rot any of the oranges
                    max = -1;
                    break;
                }
                max = Math.max(minDistance[i][j], max);
            }
            if (max == -1)
                break;
        }

        return max;
    }

    public int solveScaler(int[][] grid) {
        Queue< int[] > queue = new LinkedList< >();
        int fresh = 0;
        int time = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int[][] direction = {
                { 1, 0 },
                { 0, 1 },
                {-1, 0 },
                { 0, -1 } };
        while (!queue.isEmpty() && fresh > 0) {
            time++;
            int size = queue.size();
            while (size > 0) {
                int[] bad = queue.poll();
                for (int[] dir: direction) {
                    int nrow = bad[0] + dir[0];
                    int ncol = bad[1] + dir[1];

                    if (nrow < 0 || nrow >= grid.length || ncol < 0 || ncol >= grid[0].length || grid[nrow][ncol] == 2 || grid[nrow][ncol] == 0) {
                        continue;
                    }
                    grid[nrow][ncol] = 2;
                    fresh--;
                    queue.add(new int[] {
                            nrow,
                            ncol
                    });
                }
                size--;
            }
        }

        if (fresh == 0) {
            return time;
        } else
            return -1;
    }

    public int solveIterative(int[][] A) {

        int ROWS = A.length, COLS = A[0].length;
        int timestamp = 2;
        while (runRottingProcess(timestamp, A, ROWS, COLS))
            timestamp++;

        // end of process, to check if there are still fresh oranges left
        for (int[] row : A)
            for (int cell : row)
                // still got a fresh orange left
                if (cell == 1)
                    return -1;

        // return elapsed minutes if no fresh orange left
        return timestamp - 2;
    }

    // run the rotting process, by marking the rotten oranges with the timestamp
    public boolean runRottingProcess(int timestamp, int[][] grid, int ROWS, int COLS) {
        int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // flag to indicate if the rotting process should be continued
        boolean toBeContinued = false;

        for (int row = 0; row < ROWS; ++row)
            for (int col = 0; col < COLS; ++col)
                if (grid[row][col] == timestamp)
                    // current contaminated cell
                    for (int[] d : directions) {
                        int nRow = row + d[0], nCol = col + d[1];
                        if (nRow >= 0 && nRow < ROWS && nCol >= 0 && nCol < COLS)
                            if (grid[nRow][nCol] == 1) {
                                // this fresh orange would be contaminated next
                                grid[nRow][nCol] = timestamp + 1;
                                toBeContinued = true;
                            }
                    }
        return toBeContinued;
    }

    public static void main(String[] args) {
        RottenOranges ro = new RottenOranges();
        int[][] A = {{2, 1, 1},{1, 1, 0},{0, 1, 1}};
        System.out.println(ro.solve(A));
    }
}
