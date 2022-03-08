package com.ramamosr.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;


public class FinishMaximumJobs {
    /*
    Problem Description

There are N jobs to be done but you can do only one job at a time.

Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.

Your aim is to select jobs in such a way so that you can finish maximum number of jobs. Return the maximum number of jobs you can finish.



Problem Constraints

1 <= N <= 105

1 <= A[i] < B[i] <= 109



Input Format

First argument is an integer array A of size N denoting the start time of the jobs.
Second argument is an integer array B of size N denoting the finish time of the jobs.



Output Format

Return an integer denoting the maximum number of jobs you can finish.



Example Input

Input 1:

 A = [1, 5, 7, 1]
 B = [7, 8, 8, 8]
Input 2:

 A = [3, 2, 6]
 B = [9, 8, 9]


Example Output

Output 1:

 2
Output 2:

 1


Example Explanation

Explanation 1:

 We can finish the job in the period of time: (1, 7) and (7, 8).
Explanation 2:

 Since all three jobs collide with each other. We can do only 1 job.
     */

    /*
    We will think of the greedy approach.

Sort the given jobs according to start time.
Initialize the answer variable to one i.e ans = 1.
Loop through the jobs.
IF, the start time of the job is greater than or equal to the finsh time of the current job we are working on. Increament the answer and update the finish time.
Else if, the finish time is lesser than the current finish time update the current finish time.

Return the answer.
     */
    class Activity{
        int start=0;
        int finish=0;
        Activity(int start,int finish){
            this.start=start;
            this.finish=finish;
        }
    }

    public class CustomComparator implements Comparator<Activity> {
        @Override
        public int compare(Activity o1, Activity o2) {
            return o1.finish-o2.finish;
        }
    }
    public int solve(ArrayList<Integer> A, ArrayList<Integer> B) {

        int maxJobCount=1;
        ArrayList<Activity> activityList=new ArrayList();
        for(int i=0;i<B.size();i++){
            activityList.add(new Activity(A.get(i),B.get(i)));
        }
        Collections.sort(activityList,new CustomComparator());
        int i=0;
        for(int j=1;j<activityList.size();j++){
            if(activityList.get(j).start>=activityList.get(i).finish){
                i=j;
                maxJobCount++;
            }
        }
        return maxJobCount;
    }

    class pair {
        int S, E;
        pair(int s, int e) {
            S = s;
            E = e;
        }
    }

    public int solveScaler(int[] A, int[] B) {
        int n = A.length;
        pair a[] = new pair[n];
        for (int i = 0; i < n; i++) {
            a[i] = new pair(A[i], B[i]);
            assert(A[i] != B[i]);
        }
        Arrays.sort(a, (pair u, pair v) -> (u.E - v.E));
        int end = 0, ans = 0;
        for (pair job: a) {
            if (job.S >= end) {
                ans++;
                end = job.E;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        A.add(1);
        A.add(5);
        A.add(7);
        A.add(1);
        B.add(7);
        B.add(8);
        B.add(8);
        B.add(8);

        FinishMaximumJobs fmj = new FinishMaximumJobs();
        System.out.println(fmj.solve(A,B));

    }

}
