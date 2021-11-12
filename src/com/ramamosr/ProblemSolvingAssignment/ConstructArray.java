package com.ramamosr.ProblemSolvingAssignment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class ConstructArray {
    /*
    Construct Array
Problem Description

Simba has an integer array of length A. Despite having insisted alot, He is not ready to reveal the array to his friend Expert. But now, he is ready to reveal some hints about the array and challenges Expert to find the values of his hidden array. The hints revealed are as follows:

The array is sorted by values in ascending order.
All the elements in the array are distinct and positive (greater than 0).
The array contains two elements B and C such that B < C.
Difference between all adjacent (consecutive) elements are equal.
Among all the arrays satisfying the above conditions, his array has the minimum possible maximum element value.
If there are multiple possible arrays, his array will have minimum possible minimum element value.
Can you help Expert to construct such an array and surprise Simba?



Problem Constraints
2 <= A <= 50

1 <= B < C <= 50



Input Format
First argument is an integer A.

Second argument is an integer B.

Third argument is an integer C.



Output Format
Return a sorted integer array having length N, denoting Simba's Secret Array.



Example Input
Input 1:

 A = 5
 B = 20
 C = 50
Input 2:

 A = 2
 B = 2
 C = 3


Example Output
Output 1:

 [10, 20, 30, 40, 50]
Output 2:

 [2, 3]


Example Explanation
Explanation 1:

 Sorted array = [10, 20, 30, 40, 50] satisfies all the conditions having maximum value = 50 minimum possible.
 Other arrays such as [20, 30, 40, 50, 60] having max value = 60 or [20, 50, 80, 120] having max value = 120, also satisfy conditions but their maximum value is not minimum possible(As minimum possible max value is 50).
Explanation 2:

 As the array has only 2 elements, [2, 3] is the only possible candidate.
     */

    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int[] solve(final int A, final int B, final int C) {
        // Condition is B is always less than C
        // The minimum element in the array should be greater than zero.
        // The difference between elements should be the same.
        // The array should be sorted in ascending order.
        // the maximum value of the array should be as small as possible.
        if(A==2){
            return new int[]{B,C};
        }
        // find the factor of the difference between B and C.
        // the right factor element when divided will give a value.
        // sum of the value + 2 should be equal to A, the length of the array.
        int[] result = new int[A];
        int factor = 1;
        int startElement = 1;
        if(C-B>1) {
            ArrayList<Integer> factList = new ArrayList<>();
            if ((C - B) <= 3) {
                factList.add(1);
                factList.add(C - B);
            } else {
                for (int i = 1; i <= Math.sqrt(C - B); i++) {
                    if ((C - B) % i == 0) {
                        if (i * i == C - B) {
                            factList.add(i);
                        } else {
                            factList.add(i);
                            factList.add((C - B) / i);
                        }
                    }
                }
            }
            factList.sort(Comparator.naturalOrder());
            //Now that we know the factor, we will have to find the starting element of the array.
            // we can assume the starting element to be A-2 times the factor.
            int prevStart =1, prevEnd = Integer.MAX_VALUE;
            for(Integer val: factList){
                int start = 1,end = Integer.MAX_VALUE;
                int innerElements = ((C-B)/val)-1;
                if(B-val<=0)
                    start = B;
                else{
                    for (int i = A-2-innerElements; i >0; i--) {
                        if (B - (i * val) > 0) {
                            start = B - (i * val);
                            break;
                        }
                    }
                }
                end = start + (A-1) * val;
                if(end >=C && start <=B && start >0){
                    if(prevEnd==end){
                        startElement = Math.min(prevStart,start);

                    }
                    else if(prevEnd>end) {
                        startElement = start;
                    }
                    else
                        continue;
                    prevEnd = end;
                    prevStart = start;
                    factor = val;
                }
            }
       }
        else{
            factor = 1;
            if(B-1<=0)
                startElement = B;
            else{
                for (int i = A-2; i >0; i--) {
                    if (B - (i * 1) > 0) {
                        startElement = B - (i * 1);
                        break;
                    }
                }
            }
        }
        result[0] = startElement;
        for(int j = 1; j<A;j++){
            result[j] = result[j-1] + factor;
        }
         return result;
    }
    public static void main(String[] args) {
        ConstructArray ca = new ConstructArray();
/*        ca.solve(3,1,47);
        ca.solve(5,17,32);
        ca.solve(7,39,41);
        ca.solve(3,10,39);*/
        ca.solve(5,20,50);
    }
}
