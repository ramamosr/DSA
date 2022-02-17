package com.ramamosr.Trees.BinarySearchTree;

import com.ramamosr.Trees.TreeNode;
  
public class LeastCommonAncestor {
    /*
    Problem Description

Find the lowest common ancestor in an unordered binary tree A given two values B and C in the tree.

Lowest common ancestor : the lowest common ancestor (LCA) of two nodes and w in a tree or directed acyclic graph (DAG) is the lowest (i.e. deepest) node that has both v and w as descendants.



Problem Constraints

1 <= size of tree <= 100000

1 <= B, C <= 109



Input Format

First argument is head of tree A.

Second argument is integer B.

Third argument is integer C.



Output Format

Return the LCA.



Example Input

Input 1:


      1
     /  \
    2    3
B = 2
C = 3
Input 2:

      1
     /  \
    2    3
   / \
  4   5
B = 4
C = 5


Example Output

Output 1:

 1
Output 2:

 2


Example Explanation

Explanation 1:

 LCA is 1.
Explanation 2:

 LCA is 2.
     */
    
    /*
    The idea is to traverse the tree starting from the root. 
    If any of the given keys (n1 and n2) matches with the root, then the root is LCA 
    (assuming that both keys are present). If the root doesn’t match with any of the keys, 
    we recur for the left and right subtree. The node which has one key present in its left 
    subtree and the other key present in the right subtree is the LCA. If both keys lie in the 
    left subtree, then the left subtree has LCA also, otherwise, LCA lies in the right subtree.
    
    If one of the elements is not present in the tree, we check the boolean values and return -1.
      
     */
    TreeNode root;
    boolean value1 = false, value2 = false;
    public int lca(TreeNode A, int B, int C) {
        TreeNode node = findLCA(A,B,C);
        if(value1 && value2)
            return node.val;
        else
            return -1;

    }

    public TreeNode findLCA (TreeNode node, int B, int C){
        if(node==null) return null ;
        TreeNode temp = null;
        if(node.val==B){
            value1 = true;
            temp = node;
        }
        if(node.val==C){
            value2 = true;
            temp = node;
        }

        if(temp!=null)
            return temp;

        TreeNode left = findLCA(node.left,B,C);
        TreeNode right = findLCA(node.right,B,C);
        if(left!=null && right !=null)
            return node;
        else return (left!=null)?left:right;
    }

    public static TreeNode LCA(TreeNode root, int val1, int val2) {
        if (root == null)
            return null;
        if (root.val == val1 || root.val == val2)
            return root;
        TreeNode l = LCA(root.left, val1, val2);
        TreeNode r = LCA(root.right, val1, val2);
        if (l != null && r != null)
            return root;
        if (l != null)
            return l;
        return r;
    }
    public static boolean find(TreeNode root, int val1) {
        if (root == null)
            return false;
        if (root.val == val1)
            return true;
        return (find(root.left, val1) || find(root.right, val1));
    }
    public int lcaScaler(TreeNode A, int B, int C) {
        int val1 = B;
        int val2 = C;
        TreeNode root = A;
        if (find(root, val1) == false || find(root, val2) == false)
            return -1;
        TreeNode ans = LCA(root, val1, val2);
        if (ans == null)
            return -1;
        return ans.val;
    }

    public static void main(String[] args) {
        LeastCommonAncestor tree = new LeastCommonAncestor();
        tree.root = new TreeNode(1 );
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.lca(tree.root,4,6);
    }
}
