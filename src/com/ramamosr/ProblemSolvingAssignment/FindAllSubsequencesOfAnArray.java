package com.ramamosr.ProblemSolvingAssignment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class FindAllSubsequencesOfAnArray {
    /*
    Given an array. The task is to generate and print all of the non empty possible subsequences of the given array using recursion.

Examples:

Input : [1, 2, 3]
Output : [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]

Input : [1, 2]
Output : [2], [1], [1, 2]
     */
    long sum = 0;
    int counter = 0;
    public int solve(int[] A){
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        getSubSequence(A,0,arr,list);
        System.out.println(list);
        int mod = (1000*1000*1000) + 7;
        System.out.println(sum);
        return (int) sum % mod;

    }

    public void getSubSequence(int[] A, int index, ArrayList<Integer> arr,ArrayList<ArrayList<Integer>> list)
    {
        if (index == A.length)
        {
            if (arr.size() > 0){
                list.add(arr);
                if(arr.size() >1){
                    ArrayList<Integer> a = new ArrayList<>();
                    a = (ArrayList)arr.clone();
                    a.sort(Comparator.naturalOrder());
                    sum+= a.get(a.size()-1) - a.get(0);
                }
            }
        }
        else
        {
            getSubSequence(A, index + 1,new ArrayList<Integer>(arr),list);
            arr.add(A[index]);
            getSubSequence(A, index +1, new ArrayList<Integer>(arr),list);
        }
        return;
    }

    public static void main(String[] args) {
        FindAllSubsequencesOfAnArray fsa = new FindAllSubsequencesOfAnArray();
        fsa.solve(new int[] {1,2,3});
    }


}
