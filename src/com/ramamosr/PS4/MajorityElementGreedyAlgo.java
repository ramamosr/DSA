package com.ramamosr.PS4;

import java.util.Arrays;
import java.util.List;

public class MajorityElementGreedyAlgo {
    /*
    Majority Element
Problem Description

Given an array of size n, find the majority element. The majority element is the element that appears more than floor(n/2) times.
You may assume that the array is non-empty and the majority element always exist in the array.

Example :

Input : [2, 1, 2]
Return  : 2 which occurs 2 times which is greater than 3/2.
     */

    public int majorityElement(final int[] A) {
        int majCount = 1;
        int majElem = A[0];
        for(int i= 1; i< A.length;i++){
            if(A[i]==majElem) {
                majCount++;
            }
            else if(A[i]!=majElem) {
                majCount--;
            }
            if(majCount==0)
                majElem = A[i+1];
        }
        majCount = 0;
        for(int i=0;i< A.length;i++){
            if(A[i]==majElem)
                majCount++;
        }
        if(majCount > A.length/2) return majElem;
        else return -1;
    }
    public static void main(String[] args) {
        MajorityElementGreedyAlgo mega = new MajorityElementGreedyAlgo();
        mega.majorityElement(new int[] {3,3,4,2,4,4,2,4,4});
    }
}
