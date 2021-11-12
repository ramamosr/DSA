package com.ramamosr;

import java.util.Arrays;


public class Solution {

    //You are given an Array A of size N.
    //
    //Find for how many elements, there is a strictly smaller element and a strictly greater element.
    public int solve(int[] A) {
        int result = 0;

        Arrays.sort(A);
        int minValue = A[0];
        int maxValue = A[A.length-1];
        for(int i = 0;i <A.length;i++){
            if(A[i] < maxValue && A[i] > minValue)
                result = result+1;
        }
        return result;
    }


    //You are given an array of distinct integers A, you have to find and return all elements in array which have at-least two greater elements than themselves.
    //NOTE: The result should have the order in which they are present in the original array.
    public int[] solveTwoGreater(int[] A) {
        int[] result = new int[A.length-2];
        int firstMax = A[0];
        int secondMax = 0;
        for(int i=1;i<A.length;i++){

            if(A[i]>firstMax){
                secondMax = firstMax;
                firstMax = A[i];
            } else if(A[i]>secondMax){
                secondMax = A[i];
            }
        }
        int count = 0;
        for(int j=0;j<A.length;j++){
            if(A[j] < secondMax){
                result[count] = A[j];
                count++;
            }
        }
        return  result;
    }
    //Print a Pattern According to The Given Value of A.
    //Example: For A = 3 pattern looks like:
    /*
        1 0 0
        1 2 0
        1 2 3
   */
    public int[][] solveStarPattern(int A){
        int[][] result = new int[A][A];
        for(int i=0;i<A;i++){
            for(int j=0;j<=i;j++){
                if(i==0 || j==0)
                    result[i][j] = 1;
                else
                    result[i][j] = result[i][j-1] + 1;
            }
        }
        return result;
    }
    //You are given an array of integers A of size N.
    //Return the difference between the maximum among all even numbers of A and the minimum among all odd numbers in A.
    public int solveEvenOdd(int[] A){
        int maxEven = 0;
        int minOdd = 0;
        for(int i=0; i<A.length;i++){
            if(A[i]%2==0){
                if(A[i] < 0 && maxEven ==0)
                    maxEven = A[i];
                if(A[i]>maxEven)
                    maxEven = A[i];
            } else{
                if(minOdd == 0)
                    minOdd = A[i];
                else if(A[i]<minOdd)
                    minOdd = A[i];
            }
        }
        return maxEven-minOdd;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        //int[] numArray = {1,1,4,4,5,6,7,7,3};
        // int result = s.solve(numArray);
        //System.out.println(result);

/*        int[] numArray1 = {10,20,13,0,49,23,100};
        System.out.println(s.solveTwoGreater(numArray1));*/

/*        int[] numArray2 = {-15, -45, 43, 23, -63, 69, 35, 19, 37, -52};
        System.out.println(s.solveEvenOdd(numArray2));*/
        int matrix = 4;
        System.out.println(s.solveStarPattern(matrix));

    }
}
