package com.ramamosr;

public class SortByColor {
/*
Sort by Color
Problem Description

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: Using library sort function is not allowed.



Problem Constraints
1 <= N <= 1000000
0 <= A[i] <= 2


Input Format
First and only argument of input contains an integer array A.


Output Format
Return an integer array in asked order


Example Input
Input 1 :
    A = [0 1 2 0 1 2]
Input 2:

    A = [0]


Example Output
Output 1:
    [0 0 1 1 2 2]
Output 2:

    [0]

 */
    public int[] sortColors(int[] A) {
        int[] result = new int[A.length];
        int[] arr = new int[3];
        int count = 0;
        for(int i =0; i<A.length;i++){
            arr[A[i]]++;
        }
        for(int j =0; j<3;j++){
            while(arr[j] >0 && count <A.length) {
                result[count] = j;
                count++;
                arr[j]--;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        SortByColor sbc = new SortByColor();
        sbc.sortColors(new int[]{0});
    }
}
