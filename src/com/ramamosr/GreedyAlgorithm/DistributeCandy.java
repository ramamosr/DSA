package com.ramamosr.GreedyAlgorithm;

import java.util.Arrays;

public class DistributeCandy {

    /*
    Problem Description

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?



Problem Constraints

1 <= N <= 105
-109 <= A[i] <= 109



Input Format

First and only argument is an integer array A representing the rating of children.



Output Format

Return an integer, representing the minimum candies to be given.



Example Input

Input 1:

 A = [1, 2]
Input 2:

 A = [1, 5, 2, 1]


Example Output

Output 1:

 3
Output 2:

 7


Example Explanation

Explanation 1:

 The candidate with 1 rating gets 1 candy and candidate with rating 2 cannot get 1 candy as 1 is its neighbor.
 So rating 2 candidate gets 2 candies. In total, 2 + 1 = 3 candies need to be given out.
Explanation 2:

 Candies given = [1, 3, 2, 1]
     */
    
    /*
    Greedy works here ( Think of a supportive proof as as assignment ).

Start with the guy with the least rating. Obviously he will receive 1 candy.

If he did recieve more than one candy, we could lower it to 1 as none of the neighbor have higher rating.
Now lets move to the one which is second least. If the least element is its neighbor, then it receives 2 candies, else we can get away with assigning it just one candy.

We keep repeating the same process to arrive at optimal solution.
     */

    public int candy(int[] A) {
        int[] candies = new int[A.length];
        Arrays.fill(candies, 1);

        //From Left to right :  if child to right has higher rating
        // give 1 extra candy to the right child.
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        //From right to left :  if child to left has higher rating
        // then add 1 candy to the max of the current left child or right child
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int maxSum=0;
        for(int val:candies) maxSum += val;

        return maxSum;
    }

    public static void main(String[] args) {
        DistributeCandy dc = new DistributeCandy();
        System.out.println(dc.candy(new int[] {1,5,2,1}));
    }
}
