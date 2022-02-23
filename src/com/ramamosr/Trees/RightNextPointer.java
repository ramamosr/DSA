package com.ramamosr.Trees;

import java.util.Queue;
import java.util.LinkedList;


public class RightNextPointer {
    /*
    Problem Description

Given a binary tree,

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Assume perfect binary tree and try to solve this in constant extra space.



Problem Constraints

1 <= Number of nodes in binary tree <= 100000

0 <= node values <= 10^9



Input Format

First and only argument is head of the binary tree A.



Output Format

Return the head of the binary tree after the changes are made.



Example Input

Input 1:


     1
    /  \
   2    3
Input 2:


        1
       /  \
      2    5
     / \  / \
    3  4  6  7


Example Output

Output 1:


        1 -> NULL
       /  \
      2 -> 3 -> NULL
Output 2:


         1 -> NULL
       /  \
      2 -> 5 -> NULL
     / \  / \
    3->4->6->7 -> NULL


Example Explanation

Explanation 1:

Next pointers are set as given in the output.
Explanation 2:

Next pointers are set as given in the output.
     */
    TreeLinkNode root;
    public void connect(TreeLinkNode root) {
        TreeLinkNode curr = root;
        while(curr!=null){
            TreeLinkNode temp = curr;
            while(temp!=null && temp.left!=null && temp.right!=null){
                temp.left.next = temp.right;
                if(temp.next!=null ) {
                    temp.right.next = temp.next.left;
                }
                temp = temp.next;
            }
            curr = curr.left;
        }
    }

    public void connectScaler(TreeLinkNode root) {
        if (root == null)
            return;

        Queue< TreeLinkNode > q = new LinkedList< TreeLinkNode >();
        q.offer(root);

        while (q.size() > 0) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeLinkNode front = q.poll();
                if (i == n - 1) {
                    front.next = null;
                } else {
                    front.next = q.peek();
                }
                if (front.left != null)
                    q.offer(front.left);
                if (front.right != null)
                    q.offer(front.right);
            }
        }
        return;
    }

    public static void main(String[] args) {
        RightNextPointer rnp = new RightNextPointer();
        rnp.root = new TreeLinkNode(1);
/*        rnp.root.left = new TreeLinkNode(2);
        rnp.root.right = new TreeLinkNode(3);*/
        rnp.connect(rnp.root);
    }
}
