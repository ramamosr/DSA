package com.ramamosr.Trees;

import java.util.ArrayList;
import java.util.HashMap;

public class BTFromInOrderPreOrder {

    /*
    Problem Description

Given preorder and inorder traversal of a tree, construct the binary tree.

NOTE: You may assume that duplicates do not exist in the tree.



Problem Constraints

1 <= number of nodes <= 105



Input Format

First argument is an integer array A denoting the preorder traversal of the tree.

Second argument is an integer array B denoting the inorder traversal of the tree.



Output Format

Return the root node of the binary tree.



Example Input

Input 1:

 A = [1, 2, 3]
 B = [2, 1, 3]
Input 2:

 A = [1, 6, 2, 3]
 B = [6, 1, 3, 2]


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

        for(int i=0; i<B.size();i++){
            hm.put(B.get(i),i);
        }
        TreeNode result = constructTree(A,0,A.size()-1,B,0,B.size()-1);
        return result;
    }

    public TreeNode constructTree(ArrayList<Integer> pre, int preStart, int preEnd,ArrayList<Integer> in, int inStart, int inEnd){

        if(preStart > preEnd)
            return null;
        TreeNode temp = new TreeNode(pre.get(preStart));

        if(preStart == preEnd)
            return temp;
        int rootIndex = -1;
        if(hm.containsKey(pre.get(preStart))){
            rootIndex = hm.get(pre.get(preStart));
        }
        else
            return null;
        int count = rootIndex - inStart;

        temp.left = constructTree(pre,preStart+1,preStart+count,in,inStart,rootIndex-1);
        temp.right = constructTree(pre, preStart+count+1,preEnd,in,rootIndex+1,inEnd);

        return temp;
    }

    public static void main(String[] args) {
        ArrayList<Integer> pre = new ArrayList<>();
        pre.add(1);
        pre.add(2);
        pre.add(3);

        ArrayList<Integer> in = new ArrayList<>();
        in.add(2);;
        in.add(1);
        in.add(3);

        BTFromInOrderPreOrder btInPre = new BTFromInOrderPreOrder();
        btInPre.buildTree(pre,in);
    }
    ArrayList < Integer > preorder, inorder;

    public TreeNode buildTreeScaler(ArrayList < Integer > preorder, ArrayList < Integer > inorder) {

        if (preorder == null || inorder == null || preorder.size() == 0 || inorder.size() == 0)
            return null;

        if (preorder.size() != inorder.size())
            return null;

        this.preorder = preorder;
        this.inorder = inorder;

        return rec(0, preorder.size() - 1, 0);

    }

    private TreeNode rec(int start, int end, int index) {

        if (start > end)
            return null;

        TreeNode root = new TreeNode(preorder.get(index));

        int i = start;

        for (; i <= end; i++) {
            if (inorder.get(i).intValue() == root.val)
                break;
        }

        root.left = rec(start, i - 1, index + 1);
        root.right = rec(i + 1, end, index + i - start + 1);

        return root;
    }
}
