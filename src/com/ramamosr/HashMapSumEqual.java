
package com.ramamosr;

import java.util.*;
public class HashMapSumEqual {
// Equal
// Problem Description
//
// Given an array A of N integers, find the index of values that satisfy P + Q = R + S, where P,Q,R & S are integers values in the array
//
// Expected time complexity O(N2)
//
// NOTE:
//
// 1) Return the indices `A1 B1 C1 D1`, so that
//   A[A1] + A[B1] = A[C1] + A[D1]
//   A1 < B1, C1 < D1
//   A1 < C1, B1 != D1, B1 != C1
//
// 2) If there are more than one solutions,
//    then return the tuple of values which are lexicographical smallest.
//
// Assume we have two solutions
// S1 : A1 B1 C1 D1 ( these are values of indices in the array )
// S2 : A2 B2 C2 D2
//
// S1 is lexicographically smaller than S2 if:
//   A1 < A2 OR
//   A1 = A2 AND B1 < B2 OR
//   A1 = A2 AND B1 = B2 AND C1 < C2 OR
//   A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 < D2
// If no solution is possible, return an empty list.
//
//
//
// Problem Constraints
// 1 <= N <= 1000
//
// 0 <= A[i] <= 1000
//
//
//
// Input Format
// Single argument which is an integer array A of size N.
//
//
//
// Output Format
// Return an array of size 4 which indexes of values P,Q,R and S.
//
//
//
// Example Input
// Input 1:
//
//  A = [3, 4, 7, 1, 2, 9, 8]
// Input 2:
//
//  A = [2, 5, 1, 6]
//
//
// Example Output
// Output 1:
//
//  [0, 2, 3, 5]
// Output 2:
//
//  [0, 1, 2, 3]
//
//
// Example Explanation
// Explanation 1:
//
//  A[0] + A[2] = A[3] + A[5]
//  Note: indexes returned should be 0-based.
// Explanation 2:
//
//  A[0] + A[1] = A[2] + A[3]


    public class Pair{
        int first,next;

        Pair(int a, int b){
            first = a;
            next = b;
        }
    }
    public int[] equal(int[] A) {
        HashMap<Integer, ArrayList<Pair>> hm = new HashMap<Integer,ArrayList<Pair>>();
        for(int i =0;i <A.length;i++){
            for(int j =i+1; j<A.length; j++){
                Pair p = new Pair(i,j);
                ArrayList<Pair> temp = new ArrayList<Pair>();
                if(hm.containsKey(A[i] + A[j])){
                    ArrayList<Pair> tp = hm.get(A[i] + A[j]);
                    if((tp.get(tp.size()-1).first < tp.get(tp.size()-1).next)
                            && (p.first < p.next)
                            && (tp.get(tp.size()-1).first < p.first)
                            && tp.get(tp.size()-1).next != p.first
                            && tp.get(tp.size()-1).next != p.next){
                        temp = hm.get((A[i] + A[j]));
                        temp.add(p);
                        hm.put((A[i] + A[j]),temp);
                    }
                }
                else {
                    temp = new ArrayList<>();
                    temp.add(p);
                    hm.put((A[i] + A[j]),temp);
                }
            }
        }
        int validPair = 0;
        for(Integer key: hm.keySet()){
            ArrayList<Pair> temp = hm.get(key);
            if(temp.size() > 2){
                validPair++;
                ArrayList<Pair> t = new ArrayList<Pair>();
                for(int i=0; i < temp.size()-1; i++){
                    if((temp.get(i).first < temp.get(i).next)
                            && (temp.get(i+1).first < temp.get(i+1).next)
                            && (temp.get(i).first < temp.get(i+1).first)
                            && temp.get(i).next != temp.get(i+1).first
                            && temp.get(i).next != temp.get(i+1).next){
                        t.add(temp.get(i));
                    }
                    else
                        t.add(temp.get(i+1));
                }
                hm.put(key,t);
            }
            else if(temp.size()==2)
                validPair++;
        }
        int[][] arr = new int[validPair][4];
        //ArrayList<int[]> aL = new ArrayList<>();
        int count = 0;
        for(Integer finalKeys : hm.keySet()){
            ArrayList<Pair> pairs = hm.get(finalKeys);
            if(pairs.size()>=2){
                arr[count] = new int[] {pairs.get(0).first,pairs.get(0).next,pairs.get(1).first,pairs.get(1).next};
                count++;
            }
        }
        int[][] sortedArray = SortArray(arr);
        if(count ==0)
            return new int[0];
        else
            return arr[0];
    }

    public int[][] SortArray(int[][] sorts){

        int rows = sorts.length;
        int cols = sorts[0].length;

        for(int i = 0; i < cols; i++){
            for(int j = 0; j< rows-1;j++){
                int[] temp;
                int startRow = 0;
                if(i==0){
                    while(startRow < rows-1) {
                        if (sorts[startRow][i] > sorts[startRow + 1][i]) {
                            //swap rows
                            temp = sorts[startRow];
                            sorts[startRow] = sorts[startRow + 1];
                            sorts[startRow + 1] = temp;
                        }
                        startRow++;
                    }
                }
                else {
                    while(startRow < rows-1) {
                        int y = 0;
                        boolean allPrev = true;
                        while(y<i){
                            if(sorts[startRow][y] != sorts[startRow + 1][y]){
                                allPrev = false;
                                break;
                            }
                            y++;
                        }
                        if (allPrev && sorts[startRow][i] > sorts[startRow + 1][i]) {
                            temp = sorts[startRow];
                            sorts[startRow] = sorts[startRow + 1];
                            sorts[startRow + 1] = temp;
                        }

                        startRow++;
                    }
                }

            }
        }
        return sorts;
    }
    public static void main(String[] args) {

        HashMapSumEqual hmse = new HashMapSumEqual();
        //{1, 5, 2, 4, 2, 0, 2, 5, 1, 0, 5, 0}
        // 4, 0, 6, 4, 3, 5, 6, 2, 0, 6, 0, 8, 0, 9, 6, 6, 0, 1, 0, 8, 1, 2, 5
        hmse.equal(new int[]{1, 5, 2, 4, 2, 0, 2, 5, 1, 0, 5, 0});
    }
}
