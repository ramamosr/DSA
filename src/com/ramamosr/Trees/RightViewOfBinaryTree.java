package com.ramamosr.Trees;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

public class RightViewOfBinaryTree {
    /*
    Problem Description
Given a binary tree of integers denoted by root A. Return an array of integers representing the right view of the Binary tree.

Right view of a Binary Tree is a set of nodes visible when the tree is visited from Right side.



Problem Constraints
1 <= Number of nodes in binary tree <= 100000

0 <= node values <= 10^9



Input Format
First and only argument is head of the binary tree A.



Output Format
Return an array, representing the right view of the binary tree.



Example Input
Input 1:


            1
          /   \
         2    3
        / \  / \
       4   5 6  7
      /
     8
Input 2:


            1
           /  \
          2    3
           \
            4
             \
              5


Example Output
Output 1:

 [1, 3, 7, 8]
Output 2:

 [1, 3, 4, 5]


Example Explanation
Explanation 1:

Right view is described.
Explanation 2:

Right view is described.
     */

    TreeNode root;
    public ArrayList<Integer> solve(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        if(A==null) return null;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(A);
        //Add null element as marker to move to next level.
        que.add(null);
        ArrayList<Integer> li = new ArrayList<>();
        while(que.size()>1){
            TreeNode temp = que.poll();
            // when null marker is encountered, do the addition to the list.
            if(temp==null){
                que.add(null);
                //Add the last item in the list, as that will the right most element in the tree.
                result.add(li.get(li.size()-1));
                li = new ArrayList<>();
                continue;
            }
            li.add(temp.val);
            if(temp.left!=null)
                que.add(temp.left);
            if(temp.right!=null)
                que.add(temp.right);
        }
        // add the final list when the queue size is equal to one, which will be a null marker value;
        result.add(li.get(li.size()-1));
        return result;

    }

    public static void main(String[] args) {
        RightViewOfBinaryTree tree = new RightViewOfBinaryTree();
        tree.root = new TreeNode(3);
        tree.root.left = new TreeNode(9);
        tree.root.right = new TreeNode(20);
        tree.root.right.left = new TreeNode(15);
        tree.root.right.right = new TreeNode(7);
        tree.solve(tree.root);
    }
}
