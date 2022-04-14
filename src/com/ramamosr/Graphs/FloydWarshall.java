package com.ramamosr.Graphs;

public class FloydWarshall {
    /*
    Problem Description
Given a matrix of integers A of size N x N, where A[i][j] represents the weight of directed edge from i to j (i ---> j).

If i == j, A[i][j] = 0, and if there is no directed edge from vertex i to vertex j, A[i][j] = -1.

Return a matrix B of size N x N where B[i][j] = shortest path from vertex i to vertex j.

If there is no possible path from vertex i to vertex j , B[i][j] = -1

Note: Rows are numbered from top to bottom and columns are numbered from left to right.



Problem Constraints
1 <= N <= 200
-1 <= A[i][j] <= 1000000


Input Format
The first and only argument given is the integer matrix A.


Output Format
Return a matrix B of size N x N where B[i][j] = shortest path from vertex i to vertex j
If there is no possible path from vertex i to vertex j, B[i][j] = -1.


Example Input
A = [ [0 , 50 , 39]
          [-1 , 0 , 1]
          [-1 , 10 , 0] ]


Example Output
[ [0 , 49 , 39 ]
   [-1 , 0 , -1 ]
   [-1 , 10 , 0 ] ]


Example Explanation
Shortest Path from 1 to 2 would be 1 ---> 3 ---> 2 and not directly from 1 to 2,
All remaining distances remains same.
     */

    /*
    or all possible i, j and k
    if ( A[i][j] + A[j][k] < A[i][k] )
    {
        A[i][k]=A[i][j]+A[j][k]  // Update A[i][k]
    }
This is known as the Floyd Warshall algorithm. This algorithm is all pair shortest path algortithm.
Its initialization looks something like this
int n=A.size();
for(int i=0;i<n;i++)
{
for(int j=0;j<n;j++)
{
if(A[i][j]==-1)
{
A[i][j]=100000005;
}
}
}
     */
    public int[][] solve(int[][] A) {
        int r=A.length,c=A[0].length;
//copy the initial state
        int[][] ans=new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(A[i][j]==-1){
                    ans[i][j]=(int)(1e9);
                }
                else if(i==j){
                    ans[i][j]=0;
                }
                else{
                    ans[i][j]=A[i][j];
                }
            }
        }
        for(int k=0;k<r;k++){
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    if(i==j){
                        continue;
                    }
                    else{
                        ans[i][j]=Math.min(ans[i][j],ans[i][k]+ans[k][j]);
                    }
                }
            }
        }
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(ans[i][j]==(int)(1e9)){
                    ans[i][j]=-1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] A = {{0, 50, 39},{-1, 0, 1},{-1, 10, 0}};
        FloydWarshall fw = new FloydWarshall();
        System.out.println(fw.solve(A));
    }
}

//Scaler Solution
public class Solution {
    public int[][] solve(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == -1) {
                    A[i][j] = 100000005;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (A[i][k] + A[k][j] < A[i][j]) {
                        A[i][j] = A[i][k] + A[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 100000005) {
                    A[i][j] = -1;
                }
            }
        }
        return A;
    }
}
