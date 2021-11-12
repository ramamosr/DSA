package com.ramamosr.GreedyAlgorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

public class WhenWillAntsDie {
    /*
    When will the ants die
Problem Description

We have a wooden plank of the length A units. Some ants are walking on the plank, each ant moves with speed 1 unit per second. Some of the ants move to the left, the other move to the right.
When two ants moving in two different directions meet at some point, they change their directions and continue moving again. Assume changing directions doesn't take any additional time.

When an ant reaches one end of the plank at a time t, it falls out of the plank imediately.

Given an integer A and two integer arrays B signifying left going ants and C signifying right going ants, the positions of the ants moving to the left and the right. Return the moment when the last ant(s) fall out of the plank.





Problem Constraints
1 <= A <= 10^4
0 <= B.length <= A + 1
0 <= B[i] <= A
0 <= C.length <= A + 1
0 <= C[i] <= A
1 <= B.length + C.length <= A + 1
All values of left and right are unique, and each value can appear only in one of the two arrays.


Input Format
First argument contains integer A.

Second argument contains B denoting ants moving left.

Third argument contains C denoting ants moving right.


Output Format
Return a single integer.


Example Input
Input 1:
n = 4
left = [4,3]
right = [0,1]

Input 2:

n = 9
left = [5]
right = [4]


Example Output
Output 1:
4

Output 2:

5


Example Explanation
Explanation 1:
In the image above:
-The ant at index 0 is named A and going to the right.
-The ant at index 1 is named B and going to the right.
-The ant at index 3 is named C and going to the left.
-The ant at index 4 is named D and going to the left.
Note that the last moment when an ant was on the plank is t = 4 second, after that it falls imediately out of the plank. (i.e. We can say that at t = 4.0000000001, there is no ants on the plank).
Explaination 2

At t = 1 second, both ants will be at the same intial position but with different direction.
     */
    public int solve(int A, int[] B, int[] C) {
        // Ant is moving from left to right.
        // Reduce the max element from 0 and take ABS.
        // COnsider the plank as array. the right most index starts as 0.
        int maxTime = 0;
        if(B.length > 0){
            Arrays.sort(B);
                maxTime = Math.max(maxTime, Math.abs(0-(B[B.length-1])));
        }
        if(C.length > 0){
            Arrays.sort(C);
            maxTime = Math.max(maxTime, Math.abs(A-(C[0])));
        }
        return maxTime;

    }
    public int solveScaler(int A, int[] B, int[] C) {
        int ans=0;
        for(int i=0;i<B.length;i++){
            ans=Math.max(ans,B[i]);
        }
        for(int i=0;i<C.length;i++){
            ans=Math.max(ans,A-C[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        WhenWillAntsDie wwad = new WhenWillAntsDie();
        System.out.println(wwad.solve(4, new int[]{5},new int[]{4}));
    }
}
