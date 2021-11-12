package com.ramamosr.backtracking;

public class UniquePathScaler {

    /*
    We can perform the dfs from the starting square and maintain a visited matrix to walk every non-empty square exactly once.

When we reach the ending square by visiting all the non-empty squares, increment the answer.

We can use backtracking technique to find all possible walks.

Time complexity of the solution: O(4 N*M) because at every possible square we can move in 4 directionns.

This time complexity will give TLE but bad walks (walks which will not contribute to the answer) tend to stuck quickly and run out of free squares which make this solution to pass in the given input limits.
     */

    int n, m, ans;
    int xx[] = new int[]{1, 0, 0, -1};
    int yy[] = new int[]{0, 1, -1, 0};

    boolean isV(int u, int v, int a[][]) {
        return 0 <= u && u < n && 0 <= v && v < m && a[u][v] != -1;
    }

    void recur(int x, int y, int cnt, int a[][]) {
        if(a[x][y] == 2) {
            if(cnt == 0)    ans++;
            return;
        }
        a[x][y] = -1;
        for(int i = 0; i < 4; i++) {
            int u = x + xx[i];
            int v = y + yy[i];
            if(isV(u, v, a)) {
                recur(u, v, cnt - 1, a);
            }
        }
        a[x][y] = 0;
    }
    public int solve(int[][] a) {
        n = a.length;   m = a[0].length;
        ans = 0;
        int u = -1, v = -1, cnt = 0;
        // find starting point and count number of non-obstacle squares.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(a[i][j] == 1) {
                    u = i;
                    v = j;
                } else if(a[i][j] == 0)
                    cnt++;
            }
        }
        // Ending square is also counted in cnt so pass cnt + 1
        recur(u, v, cnt + 1, a);
        return ans;
    }
}
