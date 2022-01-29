package com.ramamosr.LinkedList;

import java.util.HashMap;

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
    /*
    Approach 1 : Using hashmap.
Use a hashmap to store the mapping from oldListNode to the newListNode. Whenever you encounter a new node in the oldListNode (either via random pointer or through the next pointer ), create the newListNode, store the mapping. and update the next and random pointers of the newListNode using the mapping from the hashmap.

Approach 2 : Using 2 traversals of the list.
Step 1: create a new node for each existing node and join them together eg: A->B->C will be A->A’->B->B’->C->C’

Step2: copy the random links: for each new node n’, n’.random = n.random.next

Step3: detach the list: basically n.next = n.next.next; n’.next = n’.next.next
     */

     class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
     };

    private HashMap < RandomListNode, RandomListNode > hashMap;

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
    
    public RandomListNode copyRandomListScaler(RandomListNode head) {
        RandomListNode node, newHead, newNode;
        hashMap = new HashMap< >();
        node = head;
        newHead = null;
        while (node != null) {
            newNode = new RandomListNode(node.label);
            if (newHead == null)
                newHead = newNode;
            hashMap.put(node, newNode);
            node = node.next;
        }
        for (Map.Entry < RandomListNode, RandomListNode > entry: hashMap.entrySet()) {
            node = entry.getKey();
            newNode = entry.getValue();
            if (node.next != null) {
                newNode.next = hashMap.get(node.next);
            }
            if (node.random != null) {
                newNode.random = hashMap.get(node.random);
            }
        }
        return newHead;
    }
    public static void main(String[] args) {
        System.out.println("hi");
    }
}
