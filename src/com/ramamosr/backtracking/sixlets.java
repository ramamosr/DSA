package com.ramamosr.backtracking;

import java.net.Inet4Address;
import java.util.ArrayList;

public class sixlets {
    /*
    Problem Description

Given a array of integers A of size N and an integer B.

Return number of non-empty subsequences of A of size B having sum <= 1000.



Problem Constraints
1 <= N <= 20

1 <= A[i] <= 1000

1 <= B <= N



Input Format
The first argument given is the integer array A.

The second argument given is the integer B.



Output Format
Return number of subsequences of A of size B having sum <= 1000.



Example Input
Input 1:

    A = [1, 2, 8]
    B = 2
Input 2:

    A = [5, 17, 1000, 11]
    B = 4


Example Output
Output 1:

3
Output 2:

0


Example Explanation
Explanation 1:

 {1, 2}, {1, 8}, {2, 8}
Explanation 1:

 No valid subsequence
     */
    int ans = 0;

    public int calculateSum(ArrayList<Integer> subSequence){
        int subSum =0;
        for(int i= 0; i <subSequence.size();i++)
            subSum+=subSequence.get(i);
        return subSum;
    }

    public void getSubSequenceAns(ArrayList<Integer> A, int B, ArrayList<Integer> subseq, int index){

        if( (subseq.size() > B) || calculateSum(subseq)>1000 )
            return;

        if(calculateSum(subseq)<=1000 && subseq.size()==B){
            ans++;
            return;
        }
        if(index==A.size())
            return;

        subseq.add(A.get(index));
        getSubSequenceAns(A,B,subseq,index+1);
        subseq.remove(A.get(index));
        getSubSequenceAns(A,B,subseq,index+1);
    }

    public int solve(ArrayList<Integer> A, int B) {
        ans = 0;
        ArrayList<Integer> sub = new ArrayList<>();
        getSubSequenceAns(A,B,sub,0);
        return ans;
    }

    int sixlets(int a[], int i, int sum, int cnt) {
        if (sum > 1000)
            return 0;
        if (cnt == 0)
            return 1;
        if (i == a.length)
            return 0;
        // Take ith element or leave it.
        return sixlets(a, i + 1, sum, cnt) + sixlets(a, i + 1, sum + a[i], cnt - 1);
    }
    public int solveScaler(int[] A, int B) {
        return sixlets(A, 0, 0, B);
    }

    public static void main(String[] args) {
        sixlets six = new sixlets();
        ArrayList<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(2);
        A.add(8);
        System.out.println(six.solve(A,2));
    }
}


