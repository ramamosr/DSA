/*
Problem Description

Given a set of distinct integers, A, return all possible subsets.

NOTE:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Also, the subsets should be sorted in ascending ( lexicographic ) order.
The list is not necessarily sorted.


Problem Constraints

1 <= |A| <= 16
INTMIN <= A[i] <= INTMAX


Input Format

First and only argument of input contains a single integer array A.



Output Format

Return a vector of vectors denoting the answer.



Example Input

Input 1:

A = [1]
Input 2:

A = [1, 2, 3]


Example Output

Output 1:

[
    []
    [1]
]
Output 2:

[
 []
 [1]
 [1, 2]
 [1, 2, 3]
 [1, 3]
 [2]
 [2, 3]
 [3]
]


Example Explanation

Explanation 1:

 You can see that these are all possible subsets.
Explanation 2:

You can see that these are all possible subsets.
*/
package com.ramamosr.backtracking;
import java.util.ArrayList;
import java.util.Collections;

public class subsets {
   public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            Collections.sort(A);
            generateSubSets(list,A,new ArrayList<Integer>(),0);

            // sort the final list to get the sorted output.
            Collections.sort(list,(o1, o2) -> {
                int len = Math.min(o1.size(),o2.size());
                for(int i=0;i < len ;i++) {
                    if (o1.get(i) == o2.get(i))
                        continue;
                    else
                        return o1.get(i) - o2.get(i);
                }
                return 1;
            });
            return list;
        }

        public void generateSubSets(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> source, ArrayList<Integer> temp,int index){
            if(index==source.size()){
                //Collections.sort(temp);
                list.add(temp);
                return;
            }
            // recursive call without adding the current element.
            generateSubSets(list,source,new ArrayList<>(temp),index+1);
            //recusrive call adding the current element.
            temp.add(source.get(index));
            generateSubSets(list,source,new ArrayList<>(temp),index+1);
        }
   
           public static void main(String[] args) {

            Subsets ss = new Subsets();
            ArrayList<Integer> al = new ArrayList<>();
            al.add(15);
            al.add(20);
            al.add(12);
            al.add(19);
            al.add(4);
            Collections.sort(al);
            ss.subsets(al);

        }
}

/*
        ArrayList < ArrayList < Integer >> ans;
        void solve(int idx, ArrayList < Integer > cur, ArrayList < Integer > A) {
            if (idx == A.size()) {
                ans.add(new ArrayList < > (cur));
                return;
            }
            solve(idx + 1, cur, A); // not take
            int element = A.get(idx);
            cur.add(element); // DO
            solve(idx + 1, cur, A); // take
            cur.remove(cur.size() - 1); // UNDO
        }
        public ArrayList < ArrayList < Integer >> subsets(ArrayList < Integer > A) {
            Collections.sort(A);
            ans = new ArrayList < > ();
            ArrayList < Integer > cur = new ArrayList < > ();
            solve(0, cur, A);
            //sort list of list
            Collections.sort(ans, (ArrayList < Integer > first, ArrayList < Integer > second) -> {
                for (int i = 0; i < first.size() && i < second.size(); i++) {
                    if (first.get(i) < second.get(i))
                        return -1;
                    if (first.get(i) > second.get(i))
                        return 1;
                }
                if (first.size() > second.size())
                    return 1;
                return -1;
            });
            return ans;
        }*/
