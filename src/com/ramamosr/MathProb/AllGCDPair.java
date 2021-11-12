package com.ramamosr.MathProb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class AllGCDPair {

    /*
    ALL GCD PAIR
Problem Description

Given an array of integers A of size N containing GCD of every possible pair of elements of another array.

Find and return the original numbers which are used to calculate the GCD array in any order. For example, if original numbers are {2, 8, 10} then the given array will be {2, 2, 2, 2, 8, 2, 2, 2, 10}.



Problem Constraints
1 <= N <= 10000

1 <= A[i] <= 109



Input Format
The first and only argument given is the integer array A.



Output Format
Find and return the original numbers which are used to calculate the GCD array in any order.



Example Input
Input 1:

 A = [2, 2, 2, 2, 8, 2, 2, 2, 10]
Input 2:

 A = [5, 5, 5, 15]


Example Output
Output 1:

 [2, 8, 10]
Output 2:

 [5, 15]


Example Explanation
Explanation 1:

 Initially, array A = [2, 2, 2, 2, 8, 2, 2, 2, 10].
 2 is the gcd between 2 and 8, 2 and 10.
 8 and 10 are the gcds pair with itself.
 Therefore, [2, 8, 10] is the original array.
Explanation 2:

 Initially, array A = [5, 5, 5, 15].
 5 is the gcd between 5 and 15.
 15 is the gcds pair with itself.
 Therefore, [5, 15] is the original array.
     */

    public int[] solve(int[] A) {

        Arrays.sort(A);
        reverseArray(A);
        // create a frequency array for all numbers in the array,
        // since there could be duplicates in the original array
        // the length of the frequency array should be more than the largest element in the original array.
        // the largest element will be the first one after sorting.

        int[] freq = new int[A[0] +1];
        // we need the result array. The result array size should be the sqrt of length of original array.
        int size = (int) Math.sqrt(A.length);
        int[] result = new int[size];

        //add elements to the frequency array.
        for(int i=0;i<A.length;i++){
            //increment the count of the frequency of an element in original array by 1.
            freq[A[i]]++;
        }
        // Count of elements in the result array
        int countInResult = 0;
        int gc = 0;
        for(int i =0; i<A.length;i++){

            // check if the frequency of an element is more than zero and count of items in the result array
            // is less than the allotted size of the result array
            if(freq[A[i]] > 0 && countInResult < size){
                // The largest element in the original array will be part of the source array as the gcd of element
                // is itself. for ex gcd of 10,10 is 10.
                result[countInResult] = A[i];
                //reduce the frequency after adding to the result.
                freq[A[i]]--;
                //increase the count in the result array.
                countInResult++;

                // Now we will have to find the gcd of the current element in the original array A[i] with
                // elements in the result array. reduce the frequency of gc by 2
                // for 40,5 there could be 2 combinations 40, 5 and 5,40. but the gcd will be 5.
                // so 5 has to be reduced by 2 counts.
                for(int j = 0; j<countInResult;j++){
                    gc = gcd(A[i],result[j]);
                    freq[gc]-=2;
                }
            }
        }
        Arrays.sort(result);
        return result;
    }
    public int gcd(int A, int B) {
        if (B != 0)
            return gcd(B, A%B);
        else
            return A;
    }
    public int[] reverseArray(int[] A){

        for(int i =0;i <=A.length/2;i++){
            int temp = A[i];
            A[i] = A[A.length-1-i];
            A[A.length-1-i] = temp;
        }
        return A;
    }

     public ArrayList< Integer > solveScaler(ArrayList < Integer > A) {
        ArrayList < Integer > ans = new ArrayList < Integer > ();
        int size = (int) Math.pow(A.size(), 0.5);
        Collections.sort(A, Collections.reverseOrder());
        HashMap< Integer, Integer > mp = new HashMap < Integer, Integer > ();
        for (int i = 0; i < A.size(); i++) {
            int x = A.get(i);
            if (mp.containsKey(x) && mp.get(x) > 0)
                mp.put(x, mp.get(x) - 1);
            else {
                for (int j = 0; j < ans.size(); j++) {
                    int g = gcd(ans.get(j), x);

                    if (mp.containsKey(g))
                        mp.put(g, mp.get(g) + 2);
                    else
                        mp.put(g, 2);
                }
                ans.add(x);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {46, 1, 2, 1, 1, 1, 5, 45, 1, 1, 2, 5, 1, 40, 1, 1, 1, 1, 1, 1, 1, 1, 1, 31, 1 };
        AllGCDPair agp = new AllGCDPair();
        agp.solve(A);
    }
}
