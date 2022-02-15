package com.ramamosr.Trees.BinarySearchTree;

import com.ramamosr.Trees.TreeNode;

public class ValidBST {
    /*
    Problem Description

Given a binary tree represented by root A.

Assume a BST is defined as follows:

1) The left subtree of a node contains only nodes with keys less than the node's key.

2) The right subtree of a node contains only nodes with keys greater than the node's key.

3) Both the left and right subtrees must also be binary search trees.



Problem Constraints

1 <= Number of nodes in binary tree <= 100000

0 <= node values <= 10^9



Input Format

First and only argument is head of the binary tree A.



Output Format

Return 0 if false and 1 if true.



Example Input

Input 1:


   1
  /  \
 2    3
Input 2:


  2
 / \
1   3


Example Output

Output 1:

 0
Output 2:

 1


Example Explanation

Explanation 1:

 2 is not less than 1 but is in left subtree of 1.
Explanation 2:

Satisfies all conditions.
     */
    /*
    As mentioned in the hints, we can check two conditions for a valid Binary Search Tree.

At each node, left subtree is also a Binary Search Tree and max value in left subtree is smaller than the node.
At each node, right subtree is also Binary Search Tree and min value in right subtree greater than the node.
The trick is when we trverse down the tree , maintain max and min allowed values for the subtree and check that the value
of the node should lie between the allowed max and min. The initial values for min and max should be INT_MIN and INT_MAX.

If at current node, allowed min is minn and allowed max is maxx.

If we moves to the left, then max value in the left subtree should be smaller than the node. So, update maxx = min(maxx, value of node).
Similarily, If we moves tot he right, the min value in right subtree should be greater than the node.So, update minn = max(minn, value of node).

In this we are traversing each node only once. So, the time complexity is O(n).
     */
    TreeNode root;
    public int isValidBST(TreeNode A) {
        if(checkBST(A,Integer.MIN_VALUE,Integer.MAX_VALUE))
            return 1;
        else
            return 0;
    }

    public boolean checkBST(TreeNode root, int l, int r){

        if(root==null) return true;
        if(root.val >= l && root.val <=r){
            boolean left = checkBST(root.left,l,root.val-1);
            boolean right = checkBST(root.right,root.val+1,r);
            return left && right;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidBST tree = new ValidBST();
        tree.root = new TreeNode(2147483647 );
        tree.root.left = new TreeNode(3);
        tree.isValidBST(tree.root);
    }
}
