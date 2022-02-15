package com.ramamosr.Trees.BinarySearchTree;

import com.ramamosr.Trees.TreeNode;

public class SortedArrayToBBST {
    /*
    Problem Description

Given an array where elements are sorted in ascending order, convert it to a height Balanced Binary Search Tree (BBST).

Balanced tree : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.



Problem Constraints

1 <= length of array <= 100000



Input Format

First argument is an integer array A.



Output Format

Return a root node of the Binary Search Tree.



Example Input

Input 1:

 A : [1, 2, 3]
Input 2:

 A : [1, 2, 3, 5, 10]


Example Output

Output 1:

      2
    /   \
   1     3
Output 2:

      3
    /   \
   2     5
  /       \
 1         10


Example Explanation

Explanation 1:

 You need to return the root node of the Binary Tree.
     */

    /*
    For a BST, all values lower than the root go in the left part of root,
    and all values higher go in the right part of the root.
    For the tree to be balanced, we will need to make sure we distribute the elements
    almost equally in left and right part.
    So we choose the mid part of the array as root and divide the elements around it .

Things to take care of :
1) Are you passing a copy of the array around or are you only passing around a reference ?
2) Are you dividing the array before passing onto the next function call or are you just specifying the start and end index ?
     */
    public TreeNode sortedArrayToBST(final int[] A) {

        if(A.length==0) return null;
        return buildBBST(A,0,A.length-1);
    }

    public TreeNode buildBBST(int[] A, int start, int end){

        if(start>end)
            return null;

        int mid = start + (end-start)/2;
        TreeNode root = new TreeNode(A[mid]);

        root.left = buildBBST(A,start,mid-1);
        root.right = buildBBST(A,mid+1,end);

        return root;
    }

    public static void main(String[] args) {
        SortedArrayToBBST bbst = new SortedArrayToBBST();
        bbst.sortedArrayToBST(new int[]{1, 2, 3, 5, 10});
    }
}
