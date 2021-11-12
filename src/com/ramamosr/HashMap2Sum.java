package com.ramamosr;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashMap;

public class HashMap2Sum {

    /*
    2 Sum
Problem Description

Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 < index2. Please note that your returned answers (both index1 and index2 ) are not zero-based. Put both these numbers in order in an array and return the array from your function ( Looking at the function signature will make things clearer ). Note that, if no pair exists, return empty list.

If multiple solutions exist, output the one where index2 is minimum. If there are multiple solutions with the minimum index2, choose the one with minimum index1 out of them.

Input: [2, 7, 11, 15], target=9
Output: index1 = 1, index2 = 2
     */
    public int[] twoSum(final int[] A, int B) {
        int[] result = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

        for (int i = 0; i < A.length; i++) {
            if (hm.containsKey(B - A[i])) {
                result[0] = hm.get(B - A[i]);
                result[1] = i + 1;
                if (result[0] > result[1]) {
                    int temp = result[1];
                    result[1] = result[0];
                    result[0] = temp;
                }
                break;
            } else {
                if (!hm.containsKey(A[i]))
                    hm.put(A[i], i + 1);
            }
        }
        if(result[0] == 0 && result[1]==0)
            return new int[0];
        return result;
    }
            //if key is not available in hashmap add it to hashmap
            /*if (!hm.containsKey(B-A[i]))
                hm.put(A[i], i  + 1);
            else if(A[i] * 2 == B){
                result[0] = hm.get(A[i]);
                result[1] = i + 1;
                return result;
            }
            else if (hm.containsKey(B - A[i]) && hm.containsKey(A[i])) {
                result[0] = hm.get(A[i]);
                result[1] = hm.get(B-A[i]);
                if (result[0] > result[1]) {
                    int temp = result[1];
                    result[1] = result[0];
                    result[0] = temp;
                }
                break;
            }
        }*/

/*
            if(hm.containsKey(B - A[i]) && hm.containsKey(A[i])) {
                    result[0] = hm.get(A[i]);
                    result[1] = hm.get(B-A[i]);
                    if (result[0] > result[1]) {
                        int temp = result[1];
                        result[1] = result[0];
                        result[0] = temp;
                    }
                    break;
            }

if(hm.containsKey(B-A[i])) {

                if(A[i] != B-A[i]){
                    if(result[0] > 0 && result[1] > 0){
                        int temp = result[1];
                        result[1] = Math.min(result[1],hm.get(B-A[i]));
                        result[0] = result[1]==temp?result[0]:hm.get(A[i]);
                    }
                    else{
                        result[0] = i+1;
                        result[1] = hm.get(B-A[i]);
                    }
                }
            }
        }
        Arrays.sort(A);
        int left = 0, right = A.length-1;
        while(left < right){
            if(A[left] + A[right] > B){
                right--;
            }
            if(A[left] + A[right] < B){
                left++;
            }
            else{
                result[0] = hm.get(A[left]);
                result[1] = hm.get(A[right]);
                if (result[0] > result[1]) {
                    int temp = result[1];
                    result[1] = result[0];
                    result[0] = temp;
                }
            }
        }*/

    public static void main(String[] args) {
   //     int[] input = {7,5,4,3,11,15};
   //     int B = 9;
//        int[] input = {4, 7, -4, 2, 2, 2, 3, -5, -3, 9, -4, 9, -7, 7, -1, 9, 9, 4, 1, -4, -2, 3, -3, -5, 4, -7, 7, 9, -4, 4, -8};
//        int B = -3;
        int[] input = {1,1,1};
        int B = 2;
        HashMap2Sum hm2Sum = new HashMap2Sum();
        int[] res = hm2Sum.twoSum(input,B);
        System.out.println(Arrays.toString(res));
    }
}
