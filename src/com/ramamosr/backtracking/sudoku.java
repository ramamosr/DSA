package com.ramamosr.backtracking;

import java.util.ArrayList;
import java.util.HashSet;

public class sudoku {
    /*
    /*
    Sudoku
Problem Description

Write a program to solve a Sudoku puzzle by filling the empty cells. Empty cells are indicated by the character '.' You may assume that there will be only one unique solution.



A sudoku puzzle,



and its solution numbers marked in red.



Problem Constraints
N = 9


Input Format
First argument is an array of array of characters representing the Sudoku puzzle.


Output Format
Modify the given input to the required answer.


Example Input
Input 1:

A = [[53..7....], [6..195...], [.98....6.], [8...6...3], [4..8.3..1], [7...2...6], [.6....28.], [...419..5], [....8..79]]


Example Output
Output 1:

[[534678912], [672195348], [198342567], [859761423], [426853791], [713924856], [961537284], [287419635], [345286179]]


Example Explanation
Explanation 1:

Look at the diagrams given in the question.
     */

    private int n;
    private ArrayList <ArrayList< Character >> A;
    private HashSet< Character > hashSet;

    public void solveSudokuScaler(ArrayList < ArrayList < Character >> A) {
        n = A.size();
        this.A = A;
        hashSet = new HashSet <> ();
        backtrack(0, 0);
    }
    public boolean backtrack(int row, int col) {
        if (row == n)
            return true;
        char c = A.get(row).get(col);
        int rr, cc;
        rr = row;
        cc = col + 1;
        if (cc == n) {
            rr++;
            cc = 0;
        }
        if (c == '.') {
            for (char ch = '1'; ch <= '9'; ch++) {
                A.get(row).set(col, ch);
                if (isValid(row, col)) {
                    boolean status = backtrack(rr, cc);
                    if (status)
                        return true;
                }
                A.get(row).set(col, '.');
            }
        } else {
            return backtrack(rr, cc);
        }
        return false;
    }
    public boolean isValid(int row, int col) {
        hashSet.clear();
        char c;
        for (int i = 0; i < n; i++) {
            c = A.get(row).get(i);
            if (hashSet.contains(c))
                return false;
            if (c != '.')
                hashSet.add(c);
        }
        hashSet.clear();
        for (int i = 0; i < n; i++) {
            c = A.get(i).get(col);
            if (hashSet.contains(c))
                return false;
            if (c != '.')
                hashSet.add(c);
        }
        int sRow = (row / 3) * 3;
        int sCol = (col / 3) * 3;
        hashSet.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int rr = sRow + i;
                int cc = sCol + j;
                c = A.get(rr).get(cc);

                if (hashSet.contains(c))
                    return false;
                if (c != '.')
                    hashSet.add(c);
            }
        }
        return true;
    }
    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        solve(a);
    }

    public boolean solve(ArrayList<ArrayList<Character>> a){
        int n = a.size();

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){

                if(a.get(i).get(j) == '.'){
                    for(char c='1'; c<='9'; c++){
                        if(isValid(a, i, j, c)){
                            a.get(i).set(j, c);

                            if(solve(a))
                                return true;
                            else
                                a.get(i).set(j, '.');
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValid(ArrayList<ArrayList<Character>> a, int row, int col, char c){
        int regionRow = 3 * (row/3);
        int regionCol = 3 * (col/3);

        for (int i = 0; i < 9; i++) {
            if (a.get(i).get(col) == c) return false;
            if (a.get(row).get(i) == c) return false;
            if (a.get(regionRow + i/3).get(regionCol + i%3) == c) return false;
        }
        return true;

    }
    public static void main(String[] args) {

    }
}
