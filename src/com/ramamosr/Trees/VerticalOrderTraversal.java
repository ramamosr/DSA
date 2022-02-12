package com.ramamosr.Trees;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class VerticalOrderTraversal {

    /*
    Problem Description

Given a binary tree, return a 2-D array with vertical order traversal of it. Go through the example and image for more details.


NOTE: If 2 Tree Nodes shares the same vertical level then the one with lesser depth will come first.



Problem Constraints

0 <= number of nodes <= 105



Input Format

First and only arument is a pointer to the root node of binary tree, A.



Output Format

Return a 2D array denoting the vertical order traversal of tree as shown.



Example Input

Input 1:

      6
    /   \
   3     7
  / \     \
 2   5     9
Input 2:

      1
    /   \
   3     7
  /       \
 2         9


Example Output

Output 1:

 [
    [2],
    [3],
    [6, 5],
    [7],
    [9]
 ]
Output 2:

 [
    [2],
    [3],
    [1],
    [7],
    [9]
 ]


Example Explanation

Explanation 1:

 First row represent the verical line 1 and so on.
     */

    TreeNode root;
    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        HashMap<Integer,ArrayList<TreeNode>> hm = new HashMap<>();
        Queue<TreePair> que = new LinkedList<>();
        int maxLevel = 0,minLevel = 0;
        que.add(new TreePair(A,0));

        while(!que.isEmpty()){
            TreePair temp = que.poll();
            TreeNode curr = temp.first;
            int level = temp.second;
            maxLevel = Math.max(maxLevel,level);
            minLevel = Math.min(minLevel,level);

            if(hm.containsKey(level)){
                hm.get(level).add(curr);
            }
            else{
                ArrayList<TreeNode> tl = new ArrayList<>();
                tl.add(curr);
                hm.put(level,tl);
            }
            if(curr.left!=null)
                que.add(new TreePair(curr.left,level-1));
            if(curr.right!=null)
                que.add(new TreePair(curr.right,level+1));
        }
        for(int i=minLevel, j=0;i<=maxLevel;i++,j++){
            ArrayList<TreeNode> li;
            ArrayList<Integer> valList = new ArrayList<>();
            li = hm.get(i);
            for(int k=0;k<li.size();k++){
                valList.add(li.get(k).val);
            }
            result.add(j,valList);
        }
        return result;
    }

    public static void main(String[] args) {
        VerticalOrderTraversal tree = new VerticalOrderTraversal();
        tree.root = new TreeNode(6);
        tree.root.left = new TreeNode(3);
        tree.root.right = new TreeNode(7);
        tree.root.left.left = new TreeNode(2);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.right = new TreeNode(9);
        tree.verticalOrderTraversal(tree.root);
    }
}
