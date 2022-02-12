package com.ramamosr.Trees;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class LeftViewOfBinaryTree {
/*    Problem Description
    Given a binary tree of integers. Return an array of integers representing the left view of the Binary tree.

    Left view of a Binary Tree is a set of nodes visible when the tree is visited from Left side

    NOTE: The value comes first in the array which have lower level.
            Problem Constraints
1 <= Number of nodes in binary tree <= 100000

            0 <= node values <= 109
    Input Format
   First and only argument is a root node of the binary tree, A.
    Output Format
   Return an integer array denoting the left view of the Binary tree.
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
            [1, 2, 4, 8]
    Output 2:
            [1, 2, 4, 5]
    Example Explanation
    Explanation 1:
    The Left view of the binary tree is returned.
        */

    class Pair {

        public TreeNode first;
        public int second;

        public Pair(TreeNode x, int y) {
            first = x;
            second = y;
        }
    }

    public ArrayList < Integer > solveScaler(TreeNode A) {

        ArrayList < Integer > ans = new ArrayList < Integer > ();
        Queue < Pair > q = new LinkedList < > ();
        int l = 0;

        q.add(new Pair(A, 0));
        while (q.size() > 0) {
            TreeNode temp = q.peek().first;
            int temp_l = q.peek().second;

            if (temp.left != null) {
                q.add(new Pair(temp.left, temp_l + 1));
            }
            if (temp.right != null) {
                q.add(new Pair(temp.right, temp_l + 1));
            }
            if (temp_l == l) {
                ans.add(temp.val);
                l++;
            }
            q.remove();
        }
        return ans;
    }

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
                //Add the first item in the list, as that will the left most element in the tree.
                result.add(li.get(0));
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
        result.add(li.get(0));
        return result;

    }

    public static void main(String[] args) {
        LeftViewOfBinaryTree tree = new LeftViewOfBinaryTree();
        tree.root = new TreeNode(3);
        tree.root.left = new TreeNode(9);
        tree.root.right = new TreeNode(20);
        tree.root.right.left = new TreeNode(15);
        tree.root.right.right = new TreeNode(7);
        tree.solve(tree.root);
    }
}
