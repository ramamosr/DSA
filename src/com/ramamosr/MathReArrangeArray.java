package com.ramamosr;
import java.lang.*;
import java.util.*;
import java.util.Collections;

public class MathReArrangeArray {
    /*
    Rearrange Array
Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.

Example:

Input : [1, 0]
Return : [0, 1]
 Lets say N = size of the array. Then, following holds true :
All elements in the array are in the range [0, N-1]
N * N does not overflow for a signed integer
     */
    public void arrange(ArrayList<Integer> A) {
        int length = A.size();
    /*
    The array elements of the given array lies from 0 to n-1.
    Now an array element is needed that can store two different values at the same time.
    To achieve this increment every element at ith index is incremented by (targetelement %mod)* length of array/.
    After the increment operation of first step, every element holds both old values and new values.
    Old value can be obtained by arr[i]%length and a new value can be obtained by arr[i]/length of array.
     */
        for(int i=0; i <length;i++){
            int temp = A.get(i) + (A.get(A.get(i))%length)*length;
            A.set(i,temp);
        }
        for(int i=0; i <length;i++){
           A.set(i,A.get(i)/length);
        }
    }
    public static void main(String[] args) {
        MathReArrangeArray mra = new MathReArrangeArray();
        ArrayList<Integer> A = new ArrayList<>();
        A.add(3);
        A.add(2);
        A.add(0);
        A.add(1);
        mra.arrange(A);
    }
}
