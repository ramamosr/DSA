package com.ramamosr.BinarySearch;

public class AllocateBooks {
    /*
    Problem Description

Given an array of integers A of size N and an integer B.

College library has N books,the ith book has A[i] number of pages.

You have to allocate books to B number of students so that maximum number of pages alloted to a student is minimum.

A book will be allocated to exactly one student.
Each student has to be allocated at least one book.
Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.
Calculate and return that minimum possible number.



NOTE: Return -1 if a valid assignment is not possible.



Problem Constraints

1 <= N <= 105
1 <= A[i], B <= 105



Input Format

The first argument given is the integer array A.
The second argument given is the integer B.



Output Format

Return that minimum possible number



Example Input

A = [12, 34, 67, 90]
B = 2


Example Output

113


Example Explanation

There are 2 number of students. Books can be distributed in following fashion :
        1) [12] and [34, 67, 90]
        Max number of pages is allocated to student 2 with 34 + 67 + 90 = 191 pages
        2) [12, 34] and [67, 90]
        Max number of pages is allocated to student 2 with 67 + 90 = 157 pages
        3) [12, 34, 67] and [90]
        Max number of pages is allocated to student 1 with 12 + 34 + 67 = 113 pages
        Of the 3 cases, Option 3 has the minimum pages = 113.

     */
    public int books(int[] A, int B) {
        //lower will be the max element of the array.
        if(B>A.length)
            return -1;
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

        AllocateBooks ab = new AllocateBooks();
        System.out.println(ab.books(new int[] {31, 14, 19, 75},2));
    }
}
