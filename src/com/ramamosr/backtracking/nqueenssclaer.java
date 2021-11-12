package com.ramamosr.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class nqueenssclaer {

        ArrayList<ArrayList<String>> res;
        ArrayList<Integer> mRow;
        ArrayList<Integer> mCol;
        char board[][];
        int N;

        public ArrayList<ArrayList<String>> solveNQueens(int A) {
            res = new ArrayList<>();
            N = A;
            board = new char[N][N];
            mRow = new ArrayList<>();
            mCol = new ArrayList<>();

            for (int i = 0; i < N; i++)
                Arrays.fill(board[i], '.');

            backtrack(0);

            return res;
        }

        public void backtrack(int row) {

            if (row == N) {
                addState();
                return;
            }

            for (int i = 0; i < N; i++) {
                board[row][i] = 'Q';
                mRow.add(row);
                mCol.add(i);
                if (validState()) {
                    backtrack(row + 1);
                }
                mRow.remove(mRow.size() - 1);
                mCol.remove(mCol.size() - 1);
                board[row][i] = '.';
            }

        }

        private boolean validState() {
            int row, col;
            int nextRow, nextCol;

            for (int i = 0; i < mRow.size(); i++) {
                row = mRow.get(i);
                col = mCol.get(i);

                for (int j = i + 1; j < mRow.size(); j++) {

                    nextRow = mRow.get(j);
                    nextCol = mCol.get(j);

                    if (row == nextRow || col == nextCol)
                        return false;

                    if (Math.abs(row - nextRow) == Math.abs(col - nextCol))
                        return false;

                }
            }

            return true;

        }


        private void addState() {
            ArrayList<String> state = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                state.add(new String(board[i]));
            }
            res.add(state);
        }
    }


