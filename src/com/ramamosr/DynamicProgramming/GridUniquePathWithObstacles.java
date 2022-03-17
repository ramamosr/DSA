package com.ramamosr.DynamicProgramming;
public class GridUniquePathWithObstacles {

    /*
    Given a grid of size n * m, lets assume you are starting at (1,1) and your goal is to reach (n, m). At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y).

Now consider if some obstacles are added to the grids. How many unique paths would there be? An obstacle and empty space is marked as 1 and 0 respectively in the grid.



Problem Constraints

1 <= n, m <= 100
A[i][j] = 0 or 1



Input Format

Firts and only argument A is a 2D array of size n * m.



Output Format

Return an integer denoting the number of unique paths from (1, 1) to (n, m).



Example Input

Input 1:

 A = [
        [0, 0, 0]
        [0, 1, 0]
        [0, 0, 0]
     ]
Input 2:

 A = [
        [0, 0, 0]
        [1, 1, 1]
        [0, 0, 0]
     ]


Example Output

Output 1:

 2
Output 2:

 0


Example Explanation

Explanation 1:

 Possible paths to reach (n, m): {(1, 1), (1, 2), (1, 3), (2, 3), (3, 3)} and {(1 ,1), (2, 1), (3, 1), (3, 2), (3, 3)}
 So, the total number of unique paths is 2.
Explanation 2:

 It is not possible to reach (n, m) from (1, 1). So, ans is 0.
     */
    public int uniquePathsWithObstacles(int[][] A) {

        int a = A.length, b = A[0].length;

        if(A[a-1][b-1] ==1)
            return 0;
        int[][] paths = new int[a][b];
        return uniquePaths(A,a,b,0,0,paths);
        //return paths[a-1][b-1];
    }

    public int uniquePaths(int[][] A, int rows, int cols, int rowIndex,int colIndex, int[][] paths ){

        if(rowIndex>=rows || colIndex>=cols || A[rowIndex][colIndex]==1)
            return 0;
        if(rowIndex==rows-1 && colIndex==cols-1)
            return paths[rowIndex][colIndex] = 1;

        return paths[rowIndex][colIndex] = uniquePaths(A,rows,cols,rowIndex+1,colIndex,paths) +
                uniquePaths(A,rows,cols,rowIndex,colIndex+1,paths);

    }

    public static void main(String[] args) {

        GridUniquePathWithObstacles gpwb = new GridUniquePathWithObstacles();
        int[][] A = {{0, 0, 0},{0, 1, 0},{0, 0, 0}};
        System.out.println(gpwb.uniquePathsWithObstacles(A));
    }
}
