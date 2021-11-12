package com.ramamosr.hw.IntroToSorting;

import java.util.*;

public class LargestNumber {
    /*
    Largest Number
Problem Description

Given a array A of non negative integers, arrange them such that they form the largest number.

Note: The result may be very large, so you need to return a string instead of an integer.



Problem Constraints
1 <= len(A) <= 100000
0 <= A[i] <= 2*109



Input Format
First argument is an array of integers.



Output Format
Return a string representing the largest number.



Example Input
Input 1:

 A = [3, 30, 34, 5, 9]
Input 2:

 A = [2, 3, 9, 0]


Example Output
Output 1:

 "9534330"
Output 2:

 "9320"


Example Explanation
Explanation 1:

 A = [3, 30, 34, 5, 9]
 Reorder the numbers to [9, 5, 34, 3, 30] to form the largest number.
Explanation 2:

 Reorder the numbers to [9, 3, 2, 0] to form the largest number 9320.
     */

    public String largestNumber(final int[] A) {
        boolean allZeros = false;
        for(int j=0; j<A.length;j++){
            if(A[j] > 0){
                allZeros = false;
                break;
            }
            allZeros = true;
        }
        if(allZeros)
            return "0";

        String[] strA = new String[A.length];
        for(int i =0; i< A.length; i++){
            strA[i] = String.valueOf(A[i]);
        }
        Arrays.sort(strA, new Comparator<String>()
        {
            @Override public int compare(String X, String Y)
            {
                String XY = X + Y;
                String YX = Y + X;
                //Remember the switch. The array switches
                // 330 is greater than 303. So the order should be the same. so return 1
                // between 30 and 34 compare between 3034 and 3430 so they should swap so return -1
                // return -1 whenever there should be swap.
                int com = XY.compareTo(YX)>0?-1:1;
                return com;
            }
        });

        Iterator it = Arrays.stream(strA).iterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()){
            sb.append(it.next());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LargestNumber ln = new LargestNumber();
        System.out.println(ln.largestNumber(new int[]{3, 30, 34, 5, 9}));
    }
}
