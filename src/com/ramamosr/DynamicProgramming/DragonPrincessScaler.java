package com.ramamosr.DynamicProgramming;

import java.util.Arrays;
import java.util.ArrayList;

public class DragonPrincessScaler {

    int dp[][];
    ArrayList <ArrayList< Integer >> A;
    int m, n;
    public int calculateMinimumHP(ArrayList < ArrayList < Integer >> A) {
        if (A == null)
            return 0;
        m = A.size();
        n = A.get(0).size();
        dp = new int[m][n];
        this.A = A;
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);
        rec(0, 0);
        if (dp[0][0] <= 0)
            return 1;
        return dp[0][0];
    }

    public int rec(int row, int col){
        if (row == m - 1 && col == n - 1) {
        int num = A.get(row).get(col);
        if (num < 0)
            return 1 - num;
        else
            return 1;
    }
        if (dp[row][col] != -1)
            return dp[row][col];
    int max = Integer.MAX_VALUE;
    int num = A.get(row).get(col);
        if (isValid(row + 1, col)) {
        max = rec(row + 1, col);
        max -= num;
        max = Math.max(1, max);
    }
        if (isValid(row, col + 1)) {
        int temp = rec(row, col + 1);
        temp -= num;
        temp = Math.max(1, temp);
        max = Math.min(temp, max);
    }
        return dp[row][col] = max;
}

    public boolean isValid(int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n)
            return false;
        return true;
    }
}
