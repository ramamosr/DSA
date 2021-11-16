package com.ramamosr.sorting;

import java.util.ArrayList;
import java.util.List;

public class MergeSortedArrays {
/*
    Problem Description

    Given two sorted integer arrays A and B, merge B and A as one sorted array and return it as an output.
    Problem Constraints

-1010 <= A[i],B[i] <= 1010
    Input Format
    First Argument is a 1-D array representing A.
    Second Argument is also a 1-D array representing B.

    Output Format
    Return a 1-D vector which you got after merging A and B.

    Example Input
    Input 1:

    A = [4, 7, 9 ]
    B = [2 ,11, 19 ]
    Input 2:

    A = [1]
    B = [2]
    Example Output
    Output 1:
            [2, 4, 7, 9, 11, 19]
    Output 2:
            [1, 2]
    Example Explanation
    Explanation 1:

    Merging A and B produces the output as described above.
    Explanation 2:

    Merging A and B produces the output as described above.
    */

    public ArrayList<Integer> solve(final List<Integer> A, final List<Integer> B) {
        ArrayList<Integer> result = new ArrayList<>();

        int aLen = A.size(), bLen = B.size();
        int i = 0, j=0;

        while(i<aLen && j < bLen){

            if(A.get(i) <= B.get(j)){
                result.add(A.get(i));
                i++;
            }
            else{
                result.add(B.get(j));
                j++;
            }
        }
        while(i < aLen){
            result.add(A.get(i));
            i++;
        }

        while(j < bLen){
            result.add(B.get(j));
            j++;
        }
        return result;
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList < Integer > solveScaler(final List < Integer > A, final List < Integer > B) {
        int m = A.size(), n = B.size();
        ArrayList < Integer > res = new ArrayList < Integer > ();
        if (A == null && B == null)
            return null;
        if (A == null) {
            for (int i = 0; i < n; i++)
                res.add(B.get(i));
            return res;
        }
        if (B == null) {
            for (int i = 0; i < m; i++)
                res.add(A.get(i));
            return res;
        }
        int i, j;
        int k = 0;
        for (i = 0, j = 0; k < m + n; k++) {
            if (i >= m)
                res.add(B.get(j++));
            else if (j >= n)
                res.add(A.get(i++));
            else if (A.get(i) <= B.get(j))
                res.add(A.get(i++));
            else
                res.add(B.get(j++));
        }
        return res;
    }

    public static void main(String[] args) {
        MergeSortedArrays msa = new MergeSortedArrays();

        List<Integer> first = new ArrayList<>();
        first.add(4);
        first.add(7);
        first.add(9);
        List<Integer> second = new ArrayList<>();
        second.add(2);
        second.add(11);
        second.add(19);
        msa.solve(first,second);

    }
}
