package com.ramamosr.ProblemSolvingAssignment;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MinDifferencePuzzle {
    /*
    Minimum Difference Puzzle
Problem Description

There is a shop whose assistant told you that there are A puzzles in the shop and you want to buy B of them. They might differ in difficulty and size. The first jigsaw puzzle consists of A1 pieces, the second one consists of A2 pieces and so on.

You decided that the difference between the numbers of pieces in bought puzzles must be as small as possible. Let x be the number of pieces in the largest puzzle that the you buy and y be the number of pieces in the smallest such puzzle. You want to choose such B puzzles that x-y is as minimum as possible. Find the least possible value of x-y.



Problem Constraints
1 <= A <= 103

1 <= B <= A

1 <= A[i] <= 106



Input Format
First argument is a vector A whose ith element represents number of pieces of ith puzzle.

Second argument is an integer B as per the question.



Output Format
Return an integer showing minimum possible value of x-y.



Example Input
Input 1:

A={10, 12, 10, 7, 5, 22}, B=4


Example Output
Output 1:

5


Example Explanation
Explanation 1:

Selected puzzles are 10, 10, 12, 7: (Max-Min) = (12-7) = 5.
     */

    public int solve(int[] A, int B) {
        if(A.length==1) return 0;
        Arrays.sort(A);
        int diff = 0;
        for(int i=0; i<=A.length-B;i++){
            if(diff!=0)
                diff = Math.min(A[i+B-1]-A[i],diff);
            else
                diff = A[i+B-1]-A[i];
        }
        return diff;
    }
    public static void main(String[] args) {
        MinDifferencePuzzle mdp = new MinDifferencePuzzle();
        System.out.println(mdp.solve(new int[] {759, 106, 827, 279, 722, 954, 80, 577, 273, 538, 963, 296, 693, 694, 684, 406, 603, 192, 166, 233, 310, 969, 980, 36, 322, 409, 392, 824, 422, 729, 76, 532, 186, 902, 811, 907, 855 },16));
    }
}
