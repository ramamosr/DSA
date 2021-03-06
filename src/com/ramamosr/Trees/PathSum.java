
package com.ramamosr.Trees;

public class PathSum {
    /*
    Problem Description

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.



Problem Constraints

1 <= number of nodes <= 105

-100000 <= B, value of nodes <= 100000



Input Format

First argument is a root node of the binary tree, A.

Second argument is an integer B denoting the sum.



Output Format

Return 1, if there exist root-to-leaf path such that adding up all the values along the path equals the given sum. Else, return 0.



Example Input

Input 1:

 Tree:    5
         / \
        4   8
       /   / \
      11  13  4
     /  \      \
    7    2      1

 B = 22
Input 2:

 Tree:    5
         / \
        4   8
       /   / \
     -11 -13  4

 B = -1


Example Output

Output 1:

 1
Output 2:

 0


Example Explanation

Explanation 1:

 There exist a root-to-leaf path 5 -> 4 -> 11 -> 2 which has sum 22. So, return 1.
Explanation 2:

 There is no path which has sum -1.
     */
    TreeNode root;
    public int hasPathSum(TreeNode A, int B) {

        if(hasPathUtil(A,B))
            return 1;
        else
            return 0;
    }

    public boolean hasPathUtil(TreeNode A, int sum){
        boolean result = false;
        int currSum = sum - A.val;

        if(currSum==0 && A.left==null && A.right==null){
           return(result = true);
        }
        if(A.left!=null){
            result = result || hasPathUtil(A.left,currSum);
        }
        if(A.right!=null){
            result = result || hasPathUtil(A.right,currSum);
        }
        return result;
    }
    public int hasPathSumScaler(TreeNode A, int B) {
        boolean status = sum(A, 0, B);
        return status ? 1 : 0;
    }

    public boolean sum(TreeNode A, int curSum, int reqSum) {
        if (A == null) {
            return false;
        }
        if (A.left == null && A.right == null) {
            curSum += A.val;
            if (curSum == reqSum)
                return true;
            return false;
        }
        int sum = curSum + A.val;
        return sum(A.left, sum, reqSum) || sum(A.right, sum, reqSum);
    }


    public static void main(String[] args) {
        PathSum tree = new PathSum();
        tree.root = new TreeNode(3);
        tree.root.left = new TreeNode(0);
        tree.root.left.right = new TreeNode(-1);
        tree.root.left.right.left = new TreeNode(-1);


        tree.hasPathSum(tree.root,1);
    }
}
