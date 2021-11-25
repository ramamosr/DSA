package com.ramamosr.BinarySearch;

public class PaintersPartition {
    /*
    We have to paint n boards of length {A1, A2, .. An}.
    There are k painters available and each takes 1 unit time to paint 1 unit of board.
    The problem is to find the minimum time to get this job done under the constraints
     that any painter will only paint continuous sections of boards, say board {2, 3, 4} or
     only board {1} or nothing but not board {2, 4, 5}.
     */

    /*
    First step is to establish the search space for using binary search.
    The min amount of time to complete 1 job by a painter
    will be the max value of the elements in the array
    The max amount of time will be if all the work is assigned to 1 painter,
    which will be the sum of the array.
    So Max element in the array will become the lower bound and sum of elements will be upper bound.
    Now we will have to check in this search space, by assigning tasks to painters which falls below
    the mid range. Check the possibility, if possible with a result, move left to see if we can reduce the time.
    if its not possible if tasks are left, then move right.
     */

    public int solve(int[] A,int B){

        //lower will be the max element of the array.
        int maxElement = Integer.MIN_VALUE;
        int totalSum = 0;
        for(int i=0; i<A.length;i++){
            maxElement = Math.max(maxElement,A[i]);
            totalSum+=A[i];
        }
        return assignTasks(A,maxElement,totalSum,B);
    }

    public int assignTasks(int[] A, int lower, int higher,int B){

        int ans = -1;
        while(lower<=higher){
            int mid = (higher+lower) / 2;

            if(checkFeasibility(A,mid,B)){
                ans = mid;
                higher = mid -1;
            }
            else
                lower = mid + 1;
        }
        return ans;
    }

    public boolean checkFeasibility(int[] A, int maxTime, int B){
        int assignedWorkers = 1;
        int currTotal = A[0];
        //Assume one worker is assigned with the first task.
        // add further tasks to that same worker till it exceeds the maxTime.
        // once it exceeds the maxTime, reset the current Total to the current element
        //after incrementing the assigned workers by 1.
        // Reset is required to the current element as that will be first task assigned to the next worker.
        for(int i=1;i < A.length;i++){
            currTotal+=A[i];
            if(currTotal > maxTime){
                assignedWorkers++;
                currTotal = A[i];
            }
        }
        // if the assigned workers exceed the number of workers available, return false;
        if(assignedWorkers>B)
            return false;
        return true;
    }

    public static void main(String[] args) {

        PaintersPartition pp = new PaintersPartition();
        System.out.println(pp.solve(new int[] {3,5,1,7,8,2,5,3,10,1,4,7,5,4,6},3));
    }
}
