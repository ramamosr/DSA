package com.ramamosr.Trees;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class LevelOrderTraversal {

    /*
    Problem Description

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).



Problem Constraints

1 <= number of nodes <= 105



Input Format

First and only argument is root node of the binary tree, A.



Output Format

Return a 2D integer array denoting the zigzag level order traversal of the given binary tree.



Example Input

Input 1:

    3
   / \
  9  20
    /  \
   15   7
Input 2:

   1
  / \
 6   2
    /
   3


Example Output

Output 1:

 [
   [3],
   [9, 20],
   [15, 7]
 ]
Output 2:

 [
   [1]
   [6, 2]
   [3]
 ]


Example Explanation

Explanation 1:

 Return the 2D array. Each row denotes the traversal of each level.
     */
    TreeNode root;
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(A==null) return null;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(A);
        //Add null element as marker to move to next level.
        que.add(null);
        int level = 0;
        ArrayList<Integer> li = new ArrayList<>();
        while(que.size()>1){
            TreeNode temp = que.poll();
            // when null marker is encountered, do the addition to the list.
            if(temp==null){
                que.add(null);
                result.add(level,li);
                li = new ArrayList<>();
                level++;
                continue;
            }
            li.add(temp.val);
            if(temp.left!=null)
                que.add(temp.left);
            if(temp.right!=null)
                que.add(temp.right);
        }
        // add the final list when the queue size is equal to one, which will be a null marker value;
        result.add(level,li);
        return result;
    }

    public static void main(String[] args) {
        LevelOrderTraversal tree = new LevelOrderTraversal();
        tree.root = new TreeNode(3);
        tree.root.left = new TreeNode(9);
        tree.root.right = new TreeNode(20);
        tree.root.right.left = new TreeNode(15);
        tree.root.right.right = new TreeNode(7);
        tree.levelOrder(tree.root);
    }

}
