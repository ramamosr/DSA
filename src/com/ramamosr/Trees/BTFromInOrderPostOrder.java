package com.ramamosr.Trees;

import java.util.HashMap;
import java.util.ArrayList;


public class BTFromInOrderPostOrder {

    /*
    Problem Description

Given inorder and postorder traversal of a tree, construct the binary tree.

NOTE: You may assume that duplicates do not exist in the tree.



Problem Constraints

1 <= number of nodes <= 105



Input Format

First argument is an integer array A denoting the inorder traversal of the tree.

Second argument is an integer array B denoting the postorder traversal of the tree.



Output Format

Return the root node of the binary tree.



Example Input

Input 1:

 A = [2, 1, 3]
 B = [2, 3, 1]
Input 2:

 A = [6, 1, 3, 2]
 B = [6, 3, 2, 1]


Example Output

Output 1:

   1
  / \
 2   3
Output 2:

   1
  / \
 6   2
    /
   3


Example Explanation

Explanation 1:

 Create the binary tree and return the root node of the tree.
     */

    HashMap<Integer,Integer> hm = new HashMap<>();
    public TreeNode buildTree(ArrayList<Integer> A, ArrayList<Integer> B) {

        if (A == null || B == null || A.size() == 0 || B.size() == 0)
            return null;

        if (A.size() != B.size())
            return null;

        for(int i=0; i<A.size();i++){
            hm.put(A.get(i),i);
        }
        TreeNode result = constructTree(B,0,B.size()-1,A,0,A.size()-1);
        return result;
    }

    public TreeNode constructTree(ArrayList<Integer> post, int postStart, int postEnd,ArrayList<Integer> in, int inStart, int inEnd){

        if(inStart > inEnd)
            return null;

        TreeNode temp = new TreeNode(post.get(postEnd));
        if(inStart == inEnd)
            return temp;
        int rootIndex = -1;
        if(hm.containsKey(post.get(postEnd))){
            rootIndex = hm.get(post.get(postEnd));
        }
        else
            return null;
        int count = rootIndex - inStart;

        temp.left = constructTree(post,postStart,postStart+count-1,in,inStart,rootIndex-1);
        temp.right = constructTree(post, postStart+count,postEnd-1,in,rootIndex+1,inEnd);

        return temp;
    }

    public static void main(String[] args) {
        ArrayList<Integer> in = new ArrayList<>();
        in.add(7);
        in.add(5);
        in.add(6);
        in.add(2);
        in.add(3);
        in.add(1);
        in.add(4);


        ArrayList<Integer> post = new ArrayList<>();
        post.add(5);;
        post.add(6);
        post.add(3);
        post.add(1);
        post.add(4);
        post.add(2);
        post.add(7);

        BTFromInOrderPostOrder btInPost = new BTFromInOrderPostOrder();
        btInPost.buildTree(in,post);
    }

    public TreeNode buildTreeScaler(ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        if (inorder == null || postorder == null || inorder.size() == 0 || inorder.size() != postorder.size())
            return null;
        TreeNode root;
        int n = inorder.size();

        root = rec(inorder, postorder, 0, n - 1, n - 1);

        return root;

    }

    public TreeNode rec(ArrayList<Integer> inorder, ArrayList<Integer> postorder, int start, int end, int postIndex) {
        if (start > end)
            return null;
        TreeNode node;
        int nodeVal = postorder.get(postIndex);
        node = new TreeNode(nodeVal);
        int i;
        for (i = start; i <= end; i++) {
            if (inorder.get(i) == nodeVal)
                break;
        }
        node.left = rec(inorder, postorder, start, i - 1, postIndex - (end - i + 1));
        node.right = rec(inorder, postorder, i + 1, end, postIndex - 1);
        return node;
    }
}
