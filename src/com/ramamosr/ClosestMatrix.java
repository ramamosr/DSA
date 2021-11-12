package com.ramamosr;
import java.lang.Math;
public class ClosestMatrix {
    /*
    Closest MinMax
Problem Description

Given an array A. Find the size of the smallest subarray such that it contains atleast one occurrence of the maximum value of the array

and atleast one occurrence of the minimum value of the array.



Problem Constraints
1 <= |A| <= 2000



Input Format
First and only argument is vector A



Output Format
Return the length of the smallest subarray which has atleast one occurrence of minimum and maximum element of the array



Example Input
Input 1:

A = [1, 3]
Input 2:

A = [2]


Example Output
Output 1:

 2
Output 2:

 1


Example Explanation
Explanation 1:

 Only choice is to take both elements.
Explanation 2:

 Take the whole array.
     */
    public int solve(int[] A) {
        int result = 0;
        int minValue = A[0];
        int maxValue = A[0];
        int minValueIndex = -1, maxValueIndex = -1;
        if(A.length <=2){
            result = A.length;
            return result;
        }
        else{
            for(int i = 0; i<A.length;i++){
                minValue = Math.min(minValue,A[i]);
                maxValue = Math.max(maxValue,A[i]);
            }
            if(minValue == maxValue)
                return 1;
            for(int i=0; i<A.length; i++){
                if(A[i] == minValue){
                    minValueIndex = i;
                    if(maxValueIndex > -1) {
                        if (result == 0)
                            result = Math.abs(minValueIndex - maxValueIndex) + 1;
                        result = result < Math.abs(minValueIndex - maxValueIndex) ? result : Math.abs(minValueIndex - maxValueIndex) + 1;
                    }
                }
                else if(A[i]==maxValue){
                    maxValueIndex = i;
                    if(minValueIndex > -1) {
                        if (result == 0)
                            result = Math.abs(minValueIndex - maxValueIndex) + 1;
                        result = result < Math.abs(minValueIndex - maxValueIndex) ? result : Math.abs(minValueIndex - maxValueIndex) + 1;
                    }
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        ClosestMatrix cm = new ClosestMatrix();
        int[] arr = {1,2,3,1};
        System.out.println(cm.solve(arr));

    }
}
