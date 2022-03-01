package com.ramamosr.Tries;

import java.util.Stack;

public class MaximumXOR {

    /*
    As mentioned in the hints, We will find the maximum XOR of ith element with the previous i-1 elements of the array. Do this for all i 1 to N and update the maximum XOR at eact step.

First build bitwise trie of i-1 elements which means insert the bit representation(from right to left) of all i-1 elements into the trie.

For ex: Given 3 numbers with their bit representation: 6(00110) , 5(00101) and 23(10111) and we need to find the maximum xor of 2(00010) with these numbers.

Insert 6(00110), 5(00101) and (10101).
After inserting, Our trie will look like this. (using only 5 bits for example)

        -1(root)
       /   \
      0     1
     /     /
    0     0
     \     \
      1     1
    /   \    \
   0     1    1
    \   /      \
   (5)1 0(6)      1(23)
We want to find the maximum xor of 2(00010) with the numbers in the trie.
Start traversing in the trie from root, At every node, there can be two possibilites:

1) If the 2(00010) has 1 at that bit, move to the left means towards elements having that bit 0.
2) If the 2(00010) has 0 at that bit, move to the right means towards elements having that bit 1.

Basically move in the direction of opposite bit to set that bit in our answer to one.

Algorithm:

1) Convert numbers into binary form.
2) Add numbers into the trie one by one and compute the maximum XOR of a number to add with all previously inserted. Update maximum XOR at each step.
3) Return the maximum XOR calculated.

At every i we are checking the maximum xor with all elements from 0 to i-1. Time complexity of this step is O(log(max_element in the array)).

We are doing this for every i 1 to N. Overall time complexity is O(Nlog(max_element in the array))
     */

    public int solve(int[] A) {
        TriesNode root = new TriesNode();
        for(int i=0;i<A.length;i++){
            root.insert(A[i]);
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<A.length;i++){
            max = Math.max(max,root.findClosestXor(A[i]));
        }
        return max;
    }

    class TriesNode{
        TriesNode zero;
        TriesNode one;
        int number;
        boolean isTerminal = false;

        public void insert(int input){
            TriesNode node = this;
            int[] binaryArr = convertDecimalToBinary(input);
            for(int i=0;i<binaryArr.length;i++){
                if(binaryArr[i]==1){
                    if(node.one==null){
                        node.one = new TriesNode();
                    }
                    node = node.one;
                }else{
                    if(node.zero==null){
                        node.zero = new TriesNode();
                    }
                    node = node.zero;
                }
            }
            node.isTerminal = true;
            node.number=input;
        }



        public int findClosestXor(int input){
            TriesNode node = this;
            int[] binaryArr = convertDecimalToBinary(input);

            for(int i=0;i<binaryArr.length;i++){
                if(binaryArr[i]==1){
                    if(node.zero!=null){
                        node = node.zero;
                    }else{
                        node = node.one;
                    }
                }else{
                    if(node.one!=null){
                        node = node.one;
                    }else{
                        node = node.zero;
                    }

                }
            }

            if(node.isTerminal){
                return node.number ^ input;
            }
            return input;
        }

        private int[] convertDecimalToBinary(int num){
            Stack<Integer> stack = new Stack<>();
            while(num>0){
                stack.push(num%2);
                num/=2;
            }
            int[] binaryArr = new int[32];
            Stack<Integer> popped = new Stack<>();
            int i = 31;
            while(!stack.isEmpty()){
                popped.push(stack.pop());
            }
            while(!popped.isEmpty()){
                binaryArr[i] = popped.pop();
                i--;
            }

            return binaryArr;
        }
    }

    static private class TrieNode {
        TrieNode left = null; // left points to 0
        TrieNode right = null; // right points to 1
    }
    public int solveScaler(int[] A) {
        return findMaximumXOR(A);
    }
    public static int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        // Constructing the Trie
        for (int num: nums) {
            TrieNode curr = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                if (bit == 0) {
                    if (curr.left == null) {
                        curr.left = new TrieNode();
                    }
                    curr = curr.left;
                } else {
                    if (curr.right == null) {
                        curr.right = new TrieNode();
                    }
                    curr = curr.right;
                }
            }
        }
        int max = Integer.MIN_VALUE;

        // Query on Trie
        for (int num: nums) {
            TrieNode curr = root;
            int currSum = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                if (bit == 0) {
                    if (curr.right != null) {
                        currSum += (int)(Math.pow(2, i));
                        // currSum += (1 >> i);
                        curr = curr.right;
                    } else {
                        curr = curr.left;
                    }
                } else {
                    if (curr.left != null) {
                        currSum += (int)(Math.pow(2, i));
                        // currSum += (1 >> i);
                        curr = curr.left;
                    } else {
                        curr = curr.right;
                    }
                }
            }
            max = Math.max(currSum, max);
        }
        return max;
    }


}
