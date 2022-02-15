package com.ramamosr.Trees.BinarySearchTree;

import java.util.ArrayList;
import com.ramamosr.Trees.TreeNode;

public class FloorAndCeilOfBST {
    /*
    Problem Description

Given a Binary Search Tree rooted at A.

Given an integer array B of size N. Find the floor and ceil of every element of the array B.

Floor(X) is the highest element in the tree <= X, while the ceil(X) is the lowest element in the tree >= X.

NOTE: If floor or ceil of any element of B doesn't exists, output -1 for the value which doesn't exists.



Problem Constraints

0 <= Number of nodes in the tree <= 1000000
0 <= node values <= 109
0 <= N <= 100000
0 <= B[i] <= 109



Input Format

First argument represents the root of binary tree A.
Second argument is an integer array B.



Output Format

Return an integer array C of size N*2. C[i][0] denotes the floor value of B[i] and C[i][1] represents the ceil value of B[i] in the given tree.



Example Input

Input 1:

Given Tree A:
           10
         /    \
        4      15
       / \
      1   8
B = [4, 19]
Input 2:

Given Tree A:
            8
          /   \
         5     19
        / \     \
       4   7     100
B = [1, 11]


Example Output

Output 1:

[
    [4, 4]
    [15, -1]
]
Output 2:

[
    [-1, 4]
    [8, 19]
]


Example Explanation

Explanation 1:

Take all elements of given tree in sorted form: [1, 4, 8, 10, 15].
4 is present in the tree. So, for B[0] = 4, output is [4, 4] as both floor and ceil value is 4.

For B[1] = 19,
Highest element <= 19 is 15. So the floor value of 19 is 15.
19 is greater than all elements in the tree. So, the ceil value of 19 doesn't exists.
So, output is [15, -1].
Explanation 2:

All elements of tree in sorted form: [4, 5, 7, 8, 19, 100].

For B[0] = 1,
There is no element in the tree <= 1. So, the floor value doesn't exists.
Lowest element >= 1 is 4. So, the ceil value is 4.
So, output is [-1, 4]

Similarily for B[1] = 11, output is [8, 19].
     */
    TreeNode root;
    int floor =-1,ceil = -1;
    public ArrayList<ArrayList<Integer>> solve(TreeNode A, ArrayList<Integer> B) {

        if(A==null) return null;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i: B){
            floor = -1;
            ceil = -1;
/*            ArrayList<Integer> temp = new ArrayList<>();
            findCeilFloor(A,i);
            temp.add(floor);
            temp.add(ceil);
            result.add(temp);*/

            ArrayList<Integer> temp = new ArrayList<>();
            int fl = findFloor(A,i);
            if(fl==Integer.MAX_VALUE)
                temp.add(-1);
            else
                temp.add(fl);
            temp.add(findCeil(A,i));
            result.add(temp);
        }
        return result;
    }

    public void findCeilFloor(TreeNode root,int value)
    {
        while (root != null)
        {
            if (root.val == value)
            {
                ceil = root.val;
                floor = root.val;
                return;
            }

            if (root.val < value)
            {
                floor = root.val;
                root = root.right;
            }
            else
            {
                ceil = root.val;
                root = root.left;
            }
        }
        return;
    }

    public int findCeil(TreeNode node, int value){
        if(node==null)
            return -1;
        if(node.val==value)
            return node.val;
        // if the node data is lesser than the value, search in the right side of the tree.
        // else search in the left.
        if(node.val<value){
            return findCeil(node.right,value);
        }
        int tempCeil = findCeil(node.left,value);
        return tempCeil>=value?tempCeil:node.val;
    }

    public int findFloor(TreeNode node, int value){
        if(node==null)
            return Integer.MAX_VALUE;
        if(node.val==value)
            return node.val;
        // if the node data is greater than the value, search in the left side of the tree.
        // else search in the right.
        if(node.val>value){
            return findFloor(node.left,value);
        }
        int tempFloor = findFloor(node.right,value);
        return (tempFloor<=value)?tempFloor:node.val;
    }

    public static void main(String[] args) {
        FloorAndCeilOfBST tree = new FloorAndCeilOfBST();
        tree.root = new TreeNode(10);
        tree.root.left = new TreeNode(4);
        tree.root.right = new TreeNode(15);
        tree.root.left.left = new TreeNode(1);
        tree.root.left.right = new TreeNode(8);
        ArrayList<Integer> input = new ArrayList<>();
        input.add(19);;
        tree.solve(tree.root,input);
    }
}
