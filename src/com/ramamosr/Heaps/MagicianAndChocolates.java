package com.ramamosr.Heaps;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

public class MagicianAndChocolates {
    /*
    Problem Description

Given N bags, each bag contains Bi chocolates. There is a kid and a magician. In one unit of time, kid chooses a random bag i, eats Bi chocolates, then the magician fills the ith bag with floor(Bi/2) chocolates.

Find the maximum number of chocolates that kid can eat in A units of time.

NOTE:

floor() function returns the largest integer less than or equal to a given number.
Return your answer modulo 109+7


Problem Constraints

1 <= N <= 100000
0 <= B[i] <= INT_MAX
0 <= A <= 105



Input Format

First argument is an integer A.
Second argument is an integer array B of size N.



Output Format

Return an integer denoting the maximum number of chocolates that kid can eat in A units of time.



Example Input

Input 1:

 A = 3
 B = [6, 5]
Input 2:

 A = 5
 b = [2, 4, 6, 8, 10]


Example Output

Output 1:

 14
Output 2:

 33


Example Explanation

Explanation 1:

 At t = 1 kid eats 6 chocolates from bag 0, and the bag gets filled by 3 chocolates.
 At t = 2 kid eats 5 chocolates from bag 1, and the bag gets filled by 2 chocolates.
 At t = 3 kid eats 3 chocolates from bag 0, and the bag gets filled by 1 chocolate.
 so, total number of chocolates eaten are 6 + 5 + 3 = 14
Explanation 2:

 Maximum number of chocolates that can be eaten is 33.

     */
    int parent(int i){
        return ((i-1)/ 2);
    }

    int left(int i){
        return (2*i + 1);
    }

    int right(int i){
        return (2*i + 2);
    }

    public void MaxHeapify(ArrayList<Integer> A, int index) {
        while ((left(index) < A.size() && A.get(left(index)) > A.get(index))
                || (right(index) < A.size() && A.get(right(index)) > A.get(index))) {
            int largest = left(index);
            if (right(index) < A.size() && A.get(right(index)) > A.get(largest)) {
                largest = right(index);
            }
            // swap the current Node with the smallest and move the currNode to the smallest node;
            if(largest==index)
                return;
            int temp = A.get(index);
            A.set(index, A.get(largest));
            A.set(largest, temp);
            index = largest;
        }
    }

    public void insertIntoHeap(ArrayList<Integer> A, int value){
        if(A!=null)
            A.add(value);
        int currIndex = A.size()-1;
        // for min heap check if the parent index is less than the current index.
        // if it is smaller swap
        while(A.get(parent(currIndex))<A.get(currIndex)){
            int temp = A.get(parent(currIndex));
            A.set(parent(currIndex), A.get(currIndex));
            A.set(currIndex,temp);
            currIndex = parent(currIndex);
        }

    }
    public int extractMax(ArrayList<Integer>A){
        int max = A.get(0);

        // after getting the max value rebuild heap.
        // swap the max element with the last element in the heap
        // and remove the last element.
        A.set(0,A.get(A.size()-1));
        A.remove(A.size()-1);
        int currNode = 0;
        MaxHeapify(A,currNode);
        return max;
    }

    public int nchoc(int A, ArrayList<Integer> B) {
        int mod = 1000000007;
        for(int i=B.size()-1;i >=0;i--){
            MaxHeapify(B,i);
        }
        long sum = 0;
        while(A-->0){
            int chocs = extractMax(B);
            insertIntoHeap(B,chocs/2);
            sum+=chocs;
        }
        return ((int)sum%mod);
    }

    public static void main(String[] args) {
        int A = 5;
        ArrayList<Integer> input = new ArrayList<>();
        input.add(2);
        input.add(4);
        input.add(6);
        input.add(8);
        input.add(10);
        MagicianAndChocolates mc = new MagicianAndChocolates();
        System.out.println(mc.nchoc(5,input));

    }

    public int nchocScaler(int A, ArrayList<Integer> B) {
        double maximum = 0;
        //maximum heap
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(B.size(), Collections.reverseOrder());
        priorityQueue.addAll(B);

        for(int count=0; count<A; count++) {
            int element = priorityQueue.poll();
            maximum += element;
            priorityQueue.add((int) Math.floor(element/2));
        }

        return (int) (maximum%(Math.pow(10, 9)+7));
    }
}
