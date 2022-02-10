package com.ramamosr.Trees;

import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
        left=null;
        right=null;
    }
}
public class IterativePostOrderTraversal {

    /*
    Problem Description

Given a binary tree, return the Postorder traversal of its nodes values.

NOTE: Using recursion is not allowed.



Problem Constraints

1 <= number of nodes <= 105



Input Format

First and only argument is root node of the binary tree, A.



Output Format

Return an integer array denoting the Postorder traversal of the given binary tree.



Example Input

Input 1:

   1
    \
     2
    /
   3
Input 2:

   1
  / \
 6   2
    /
   3


Example Output

Output 1:

 [3, 2, 1]
Output 2:

 [6, 3, 2, 1]


Example Explanation

Explanation 1:

 The Preoder Traversal of the given tree is [3, 2, 1].
Explanation 2:

 The Preoder Traversal of the given tree is [6, 3, 2, 1].
     */


    TreeNode root;
    public int[] postorderTraversal(TreeNode A) {

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();

        if(A==null) return new int[0];

        //push the root element to first stack;
        st1.push(A);
        while(!st1.isEmpty()){
            TreeNode temp = st1.pop();
            st2.push(temp);
            if(temp.left!=null)
                st1.push(temp.left);
            if(temp.right!=null)
                st1.push(temp.right);

        }
        int[] result = new int[st2.size()];
        int count = 0;
        while(!st2.isEmpty()){
            result[count] = st2.pop().val;
            count++;
        }

        return result;

    }
    public static void main(String[] args) {

        IterativePostOrderTraversal tree = new IterativePostOrderTraversal();

        // Let us create trees shown in above diagram
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);
        tree.postorderTraversal(tree.root);
    }
}
