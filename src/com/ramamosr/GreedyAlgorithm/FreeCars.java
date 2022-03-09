package com.ramamosr.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class FreeCars {

    /*
    Problem Description

Given two arrays A and B of size N. A[i] represents the time by which you can buy ith car without paying any money.

B[i] represents the profit you can earn by buying ith car. It takes 1 minute to buy a car so, you can only buy the ith car when the current time <= A[i] - 1.

Your task is to find maximum profit one can earn by buying cars considering that you can only buy one car at a time.

NOTE:

You can stary buying from time = 0.
Return you answer modulo 109 + 7.


Problem Constraints

1 <= N <= 105
1 <= A[i] <= 109
0 <= B[i] <= 109



Input Format

First argument is an integer array A represents the deadline of buying the cars.
Second argument is an integer array B represents the profit obtained after buying the cars.



Output Format

Return an integer denoting the maximum profit you can earn.



Example Input

Input 1:

 A = [1, 3, 2, 3, 3]
 B = [5, 6, 1, 3, 9]
Input 2:

 A = [3, 8, 7, 5]
 B = [3, 1, 7, 19]


Example Output

Output 1:

 20
Output 2:

 30


Example Explanation

Explanation 1:

 At time 0, buy car with profit 5.
 At time 1, buy car with profit 6.
 At time 2, buy car with profit 9.
 At time = 3 or after , you can't buy any car, as there is no car with deadline >= 4.
 So, total profit that one can earn is 20.
Explanation 2:

 At time 0, buy car with profit 3.
 At time 1, buy car with profit 1.
 At time 2, buy car with profit 7.
 At time 3, buy car with profit 19.
 We are able to buy all cars within their deadline. So, total profit that one can earn is 30.
     */
/*
Sort the array in ascending order according to the deadline. Complete the action with least deadline.
if the deadlines are same, find the car which has the maximum profit.
If the car with a similar deadline is added to the queue already, poll it from the queue
if the profit is lesser than the current car.
 */
    class Pair {
        int deadline;
        int profit;
        Pair(int d, int p) {
            this.deadline=d;
            this.profit=p;
        }
    }
    public int solve(int[] A, int[] B) {
        Pair p[]=new Pair[A.length];
        int mod=(int) (1e9+7);
        for(int i=0;i<A.length;i++) {
            p[i]=new Pair(A[i]-1, B[i]);
        }
        Arrays.sort(p, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                if(p1.deadline<p2.deadline) {
                    return -1;
                }
                return 1;
            }
        });
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        int ans=0, day=-1;
        for(int i=0;i<A.length;i++) {
            if(day<p[i].deadline) {
                day++;
                pq.add(p[i].profit);
                ans=(ans%mod+p[i].profit%mod)%mod;
            }
            else {
                if(p[i].profit>pq.peek()) {
                    int min=pq.poll();
                    ans=(ans%mod - min%mod + mod)%mod;
                    pq.add(p[i].profit);
                    ans=(ans%mod+p[i].profit%mod)%mod;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {3, 8, 7, 5};
        int[] B = {3, 1, 7, 19};
        FreeCars fc = new FreeCars();
        System.out.println(fc.solve(A,B));

    }
}
