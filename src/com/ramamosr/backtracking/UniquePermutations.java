package com.ramamosr.backtracking;

import java.util.ArrayList;
import java.util.Collections;

public class UniquePermutations {
    /*
    Problem Description

Given an array A of size N denoting collection of numbers that might contain duplicates, return all possible unique permutations.

NOTE: No 2 entries in the permutation sequence should be the same.

WARNING: DO NOT USE LIBRARY FUNCTION FOR GENERATING PERMUTATIONS. Example : next_permutations in C++ / itertools.permutations in python.
If you do, we will disqualify your submission retroactively and give you penalty points.


Problem Constraints

1 <= |A| <= 9

0 <= A[i] <= 10



Input Format

Only argument is an integer array A of size N.



Output Format

Return a 2-D array denoting all possible unique permutation of the array.



Example Input

Input 1:

A = [1, 1, 2]
Input 2:

A = [1, 2]


Example Output

Output 1:

[ [1,1,2]
  [1,2,1]
  [2,1,1] ]
Output 2:

[ [1, 2]
  [2, 1] ]


Example Explanation

Explanation 1:

 All the possible unique permutation of array [1, 1, 2].
Explanation 2:

 All the possible unique permutation of array [1, 2].

     */
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {

        if(A.isEmpty() || A.size()==0)
            return result;
        Collections.sort(A);
        boolean[] visited = new boolean[A.size()];
        for(int i=0;i <A.size();i++)
            visited[i] = false;
        ArrayList<Integer> temp = new ArrayList<>();
        findUniquePermutations(A,visited,temp, A.size());
        return result;
    }

    public void findUniquePermutations(ArrayList<Integer> A, boolean[] visited, ArrayList<Integer> temp, int length){

        if(length==0){
            ArrayList<Integer> current = new ArrayList<>();
            for(int i = 0; i <temp.size();i++){
                current.add(temp.get(i));
            }
            result.add(current);
            return;
        }
        for(int i= 0; i<visited.length;i++){
            if(visited[i] == true || ( i >0 && A.get(i)==A.get(i-1)  && visited[i-1]==true)){
                continue;
            }
            visited[i] = true;
            temp.add(A.get(i));
            findUniquePermutations(A,visited,temp,length - 1);
            temp.remove(temp.size()-1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) {
        UniquePermutations up = new UniquePermutations();
        ArrayList<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(1);
        A.add(2);
        up.permute(A);
    }
}
