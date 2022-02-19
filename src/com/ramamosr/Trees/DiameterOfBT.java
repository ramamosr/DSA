package com.ramamosr.Trees;

public class DiameterOfBT {
    /*
    Problem Description

Given a Binary Tree A consisting of N integer nodes, you need to find the diameter of the tree.

The diameter of a tree is the number of edges on the longest path between two nodes in the tree.



Problem Constraints

0 <= N <= 105



Input Format

First and only Argument represents the root of binary tree A.



Output Format

Return an single integer denoting the diameter of the tree.



Example Input

Input 1:

           1
         /   \
        2     3
       / \
      4   5
Input 2:

            1
          /   \
         2     3
        / \     \
       4   5     6


Example Output

Output 1:

 3
Output 2:

 4


Example Explanation

Explanation 1:

 Longest Path in the tree is 4 -> 2 -> 1 -> 3 and the number of edges in this path is 3 so diameter is 3.
Explanation 2:

 Longest Path in the tree is 4 -> 2 -> 1 -> 3 -> 6 and the number of edges in this path is 4 so diameter is 4.
     */

    TreeNode root;
    int diameter = 0;

    public int solve(TreeNode A) {
        int height = findHeight(A);
        return diameter;

    }

    public int findHeight(TreeNode A){
        //the height is calculated as the number of edges. so return -1.
        // because when we add +1, the height becomes 0
        if(A==null) return -1;
        int lHeight = findHeight(A.left);
        int rHeight = findHeight(A.right);
        // Add 2 to the total height, to add the edges from the current root node to left and right.
        diameter = Math.max(diameter,lHeight+rHeight+2);
        int height = Math.max(lHeight,rHeight);
        return (height+1);
    }

    public static void main(String[] args) {
        DiameterOfBT tree = new DiameterOfBT();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.solve(tree.root);
    }
}
