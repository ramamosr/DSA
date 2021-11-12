package com.ramamosr.backtracking;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class CombinationalSum {
    /*
    Problem Description

Given a set of candidate numbers A and a target number B, find all unique combinations in A where the candidate numbers sums to B.

The same repeated number may be chosen from A unlimited number of times.

Note:

1) All numbers (including target) will be positive integers.

2) Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).

3) The combinations themselves must be sorted in ascending order.

4) CombinationA > CombinationB iff (a1 > b1) OR (a1 = b1 AND a2 > b2) OR ... (a1 = b1 AND a2 = b2 AND ... ai = bi AND ai+1 > bi+1)

5) The solution set must not contain duplicate combinations.



Problem Constraints
1 <= |A| <= 20

1 <= A[i] <= 50

1 <= B <= 500



Input Format
First argument is the vector A.

Second argument is the integer B.



Output Format
Return a vector of all combinations that sum up to B.



Example Input
Input 1:

A = [2, 3]
B = 2
Input 2:

A = [2, 3, 6, 7]
B = 7


Example Output
Output 1:

[ [2] ]
Output 2:

[ [2, 2, 3] , [7] ]


Example Explanation
Explanation 1:

All possible combinations are listed.
Explanation 2:

All possible combinations are listed.
     */
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        ArrayList<Integer> temp = new ArrayList<>();
        // the below code is to take care of the duplicates in the array list.
        // if there are duplicates we will have to remove them first to avoid duplicates in the result.
        // Use the hashset to remove the duplicates. clear the original array and then add the elements
        // back to the arraylist
        HashSet tempSet = new HashSet(A);
        A.clear();
        A.addAll(tempSet);

        Collections.sort(A);
        findCombinations(A,B,0,temp);
        return result;
    }

    public void findCombinations(ArrayList<Integer> list, int sum, int index, ArrayList<Integer> combination){

        if(sum==0){
            result.add(new ArrayList<Integer>( combination));
            return;
        }
        // loop through all the elements in the array. for every element in the array,
        // add the element to the list only if the sum is greater than 0 when deducting from existing sum
        // add the element first and then remove it back to take a different branch.
        for(int i= index; i<list.size();i++){
            if(sum-list.get(i)>=0){
                combination.add(list.get(i));
                findCombinations(list,sum-list.get(i),i,combination);
                combination.remove(list.get(i));
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>();
        input.add(2);
        input.add(3);
        input.add(6);
        input.add(7);
        int B = 7;

        CombinationalSum cs = new CombinationalSum();
        cs.combinationSum(input,B);
    }
}
