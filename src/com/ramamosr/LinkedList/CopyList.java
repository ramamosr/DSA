package com.ramamosr.LinkedList;

public class CopyList {

    /*
    Problem Description

A linked list A is given such that each node contains an additional random pointer which could point to any node in the list or NULL.

Return a deep copy of the list.



Problem Constraints

0 <= |A| <= 106



Input Format

The first argument of input contains a pointer to the head of linked list A.



Output Format

Return a pointer to the head of the required linked list.



Example Input

Given list
   1 -> 2 -> 3
with random pointers going from
  1 -> 3
  2 -> 1
  3 -> 1



Example Output

   1 -> 2 -> 3
with random pointers going from
  1 -> 3
  2 -> 1
  3 -> 1



Example Explanation

You should return a deep copy of the list.
The returned answer should not contain the same node as the original list, but a copy of them.
The pointers in the returned list should not link to any node in the original input list.
     */

     class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
     };

    public RandomListNode copyRandomList(RandomListNode head) {

        if(head==null) return null;

        // Step 1 is to combine the nodes to create a single list.
        // Create the new node and add it to the current node.
        // Modify the next pointers to insert in between the current and next node.
        RandomListNode curr = head;
        while(curr!=null){
            RandomListNode temp = new RandomListNode(curr.label);
            temp.next = curr.next;
            curr.next = temp;
            // move the current node to the original next node
            curr = curr.next.next;
        }

        // Step 2 is to update the random pointers for each node.
        // Check whether the random pointer is null or not.
        // Assign the random of the newly inserted nodes.
        // the new random will be after the current random.
        // then move the curr pointer to the next original node.

        curr = head;
        while(curr!=null){
            if(curr.random!=null)
                curr.next.random = curr.random.next;
            curr = curr.next.next;
        }

        // Step 3. Now split the single list to two.
        // Original list and new list

        curr = head;
        RandomListNode head2 = curr.next;
        RandomListNode node2 = curr.next;
        while(curr!=null){
            curr.next = curr.next.next;
            curr = curr.next;
            // After updating current, check whether it is null;
            if(curr!=null){
                node2.next = node2.next.next;
                node2 = node2.next;
            }
        }
        return head2;
    }
