package com.ramamosr.Trees;

import java.util.ArrayList;

public class NodeDistanceInBT {
    /*
    Given a binary tree of integers. All nodes in the binary tree have distinct values. You are given an integer B.

You have to find all the nodes that are at a distance of exactly C from the node containing value B.

Return an array of integers consisting all the nodes that are C distance from node containing value B.

Note:

You may return the nodes in any order.

Your solution will run on multiple test cases, if you are using global variables make sure to clear every time .

Constraints

1 <= Number of nodes in binary tree <= 100000
0 <= Node values <= 10^9
0 <= B <= 10^9
0 <= C <= 100
For Example

Input 1:
            1
          /   \
         2    3
        / \  / \
       4   5 6  7
      /
     8

     B = 3
     C = 3
Output 1:
    [4, 5]

Input 2:
            1
           /  \
          2    3
           \
            4
             \
              5
        B = 4
        C = 1
Output 2:
    [2, 5]
     */

    TreeNode root;
    public ArrayList<Integer> solve(TreeNode A, int B, int C) {
        if(A==null) return null;

        // First find the path to the node with value B. Store them in array list.
        ArrayList<TreeNode> path = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        int dist = C;
        if (findPath(A,B,path)){
            findNodes(path.get(0),dist,result);
            dist = dist-1;
            for(int i=1;i<path.size();i++){
                if(dist==0){
                    result.add(path.get(i).val);
                    break;
                }
                if(path.get(i).left == path.get(i-1)){
                    findNodes(path.get(i).right,dist-1,result);
                }
                else{
                    findNodes(path.get(i).left,dist-1,result);
                }
                dist = dist-1;
            }
        }
        return result;
    }

    public void findNodes(TreeNode A, int distance,ArrayList<Integer> result ) {

        if(A==null) return;
        if(distance==0) {
            result.add(A.val);
            return;
        }
        findNodes(A.left,distance-1,result);
        findNodes(A.right,distance-1,result);
        return;
    }

    public boolean findPath(TreeNode node, int elem, ArrayList<TreeNode> path){

        if(node == null) return false;

        if(node.val == elem){
            path.add(node);
            return true;
        }

        if(findPath(node.left,elem,path) || findPath(node.right,elem,path)){
            path.add(node);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        NodeDistanceInBT tree = new NodeDistanceInBT();

        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.right = new TreeNode(4);
        tree.root.left.right.right = new TreeNode(5);

        tree.solve(tree.root,4,1);
    }
}
