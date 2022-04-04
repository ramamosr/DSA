package com.ramamosr.Graphs;

public class ColorCycleGraph {
    /*
    Problem Description
Given the number of vertices A in a Cyclic Graph.

Your task is to determine the minimum number of colors required to color the graph so that no two Adjacent vertices have the same color.



Problem Constraints
3 <= A <= 109



Input Format
First argument is an integer A denoting the number of vertices in the Cyclic Graph.



Output Format
Return an single integer denoting the minimum number of colors required to color the graph so that no two Adjacent vertices have the same color.



Example Input
Input 1:

 5
Input 2:

 4


Example Output
Output 1:

 3
Output 2:

 2
     */

    /*


    Cycle:- cycle is a path of edges and vertices wherein a vertex is reachable from itself. or in other words, it is a Closed walk.

Even Cycle:- In which Even number of vertices is present is known as Even Cycle.

Odd Cycle:- In which Odd number of Vertices is present is known as Odd Cycle.

Approach:

If the no. of vertices is Even then it is Even Cycle and to color such graph we require 2 colors.
If the no. of vertices is Odd then it is Odd Cycle and to color such graph we require 3 colors.
     */

    public int solve(int A) {
        if(A==0) return 0;

        if(A%2== 0) return 2;
        else
            return 3;
    }

    public static void main(String[] args) {
        ColorCycleGraph ccg = new ColorCycleGraph();
        System.out.println(ccg.solve(5));
    }

}
