package com.ramamosr.ArrayProblems;

import java.util.Arrays;
import java.util.TreeMap;

public class MinimizeDiffBetMaxAndMin {
    /*
    Minimize Difference
Problem Description

Given an array of integers A of size, N. Minimize the absolute difference between the maximum and minimum element of the array.

You can perform two types of operations at most B times in total to change the values in the array.
Multiple operations can be performed on the same element.

Increment : A[i] -> A[i] + 1.

Decrement : A[i] -> A[i] - 1.

Return the minimum difference possible.



Problem Constraints
1 <= N <= 105

1 <= A[i] <= 106

1 <= B <= 109



Input Format
First argument is an integer array A.
Second argument is an integer B which represents the maximum number of operations that can be performed.



Output Format
Return an integer denoting the minimum difference.



Example Input
Input 1:

 A = [2, 6, 3, 9, 8]
 B = 3
Input 2:

 A = [4, 6, 3, 1, 4]
 B = 5


Example Output
Output 1:

 5
Output 2:

 1


Example Explanation
Explanation 1:

 We can apply the atmost 3 operations in the following sequence.
 Initial array => [2, 6, 3, 9, 8].
   Decrement 9 => [2, 6, 3, 8, 8].
   Increment 2 => [3, 6, 3, 8, 8].
   Increment 3 => [3, 6, 4, 8, 8].
 Max = 8. Min = 3.
 Therefore, abs|Max - Min| = |8 - 3| = 5.
Explanation 2:

 We can apply the atmost 5 operations in the following sequence.
 Initial array => [4, 6, 3, 1, 4].
   Increment 1 => [4, 6, 3, 2, 4].
   Decrement 6 => [4, 5, 3, 2, 4].
   Increment 2 => [4, 5, 3, 3, 4].
   Decrement 5 => [4, 4, 3, 3, 4].
   Increment 3 => [4, 4, 4, 3, 4].
 Max = 4. Min = 3.
 Therefore, abs|Max - Min| = |4 - 3| = 1.
     */

    /*
    Maintain a freq array to maintain the frequency of the elements present in the array.

x = Number of elements equals to the Minimum of the array, y = Number of elements equals to the Maximum of the array.

if freq[x] > freq[y] perform decrement operation else perform increment operation.

Keep on doing operations until the difference becomes 0.

Total operations should be less then k.
     */
    public int solve(int[] A, int B) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //find the min and max value;
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        for(int i=0; i<A.length;i++){
            min = Math.min(min,A[i]);
            max = Math.max(max,A[i]);
            tm.put(A[i],tm.getOrDefault(A[i],0)+1);
        }
            // While the operation count is greater than zero and min is less than max
            // first pick which side you want to do the operations. Min side or max side.
            // compare the duplicate occurrence of min and max elements and pick a side.
            // check if the number of duplicate occurrence of min value is more than the available operations count
            // if not, check the number of duplicate occurrences of the max value is more than the available operation counts.
            // if both are more than the available ops count, break. nothing can be changed.
            // for example if we have 2 values of min as 3 and 2 values of max as 9, but have only 1 operation,
            // the difference will still be the same, as max and min values do not change.
            while(B >0 && min<max){
                if(tm.getOrDefault(min,0) < tm.getOrDefault(max,0)){
                    if(tm.getOrDefault(min,0) > B)
                        break;
                    // INcrement the min key by 1 and add the count of min values to the new min which is old min +1;
                    //reduce the operations count by the number of old min with duplicates.
                    tm.put(min+1,tm.getOrDefault(min+1,0) + tm.get(min));
                    B = B-tm.get(min);
                    min = min +1;
                }
                else{
                    // Decrement the max key by 1 and add the count of max values to the new max which is old max -1;
                    //reduce the operations count by the number of old max with duplicates.
                    if(tm.getOrDefault(max,0) > B)
                        break;
                    tm.put(max-1,tm.getOrDefault(max-1,0) + tm.get(max));
                    B = B-tm.get(max);
                    max = max-1;
                }

            }
            return max-min;
    }

    public int solveScaler(int[] A, int B) {
        int maxx = 0;
        int k = B;
        for (int i = 0; i < A.length; i++) {
            maxx = Math.max(maxx, A[i]);
        }

        int[] freq = new int[maxx + 1];
        Arrays.fill(freq, 0);

        for (int i = 0; i < A.length; i++) {
            freq[A[i]]++;
        }

        int i = 0, j = maxx;
        while (i < j) {

            if (freq[i] > freq[j]) {

                if (freq[j] <= k) { // perfrom decrement operation
                    freq[j - 1] = freq[j - 1] + freq[j];
                    k = k - freq[j];
                    j--;
                } else {
                    break;
                }
            } else {
                if (freq[i] <= k) { // perfrom increment operation
                    freq[i + 1] = freq[i + 1] + freq[i];
                    k = k - freq[i];
                    i++;
                } else {
                    break;
                }
            }
        }

        return j - i;
    }
    public static void main(String[] args) {
        MinimizeDiffBetMaxAndMin md = new MinimizeDiffBetMaxAndMin();
        System.out.println(md.solve(new int[] {2, 6, 3, 9, 8},4 ));
    }
}
