package com.ramamosr.LinkedList;

public class RemoveLoop {
    /*
    Problem Description

Given a linked list which contains some loop.

You need to find the node, which creates a loop, and break it by making the node point to NULL.



Problem Constraints

1 <= number of nodes <= 1000



Input Format

Only argument is the head of the linked list.



Output Format

return the head of the updated linked list.



Example Input

Input 1:


1 -> 2
^    |
| - -
Input 2:

3 -> 2 -> 4 -> 5 -> 6
          ^         |
          |         |
          - - - - - -


Example Output

Output 1:

 1 -> 2 -> NULL
Output 2:

 3 -> 2 -> 4 -> 5 -> 6 -> NULL


Example Explanation

Explanation 1:

 Chain of 1->2 is broken.
Explanation 2:

 Chain of 4->6 is broken.
     */
    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode solve(ListNode A) {

        if(A==null || A.next==null) return null;

        ListNode slow = A;
        ListNode fast = A;

        // Find the meeting point. Once there is a meeting point,
        // there is definitely a loop.
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                break;
            }
        }

        // Once there is a loop, move the slow pointer to the original head.
        // Let slow pointer start from the head, and fast start from the meeting point
        // and move at the same speed.  The point where the slow and fast will meet will be the
        //beginning of the loop. Make the pointer at the beginning of the loop to null;
        slow = A;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        
        //Now slow is the start of the loop. Will have to get to the last node in the loop
        // and remove the pointer.

        ListNode temp = slow;
        while(temp.next!=slow){
            temp = temp.next;
        }
        temp.next= null;
        return A;
    }

    public ListNode solveScaler(ListNode A) {
        detectAndRemoveLoop(A);
        return A;
    }
    int detectAndRemoveLoop(ListNode node) {
        ListNode slow = node, fast = node;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If slow and fast meet at same point then loop is present
            if (slow == fast) {
                removeLoop(slow, node);
                return 1;
            }
        }
        return 0;
    }

    // Function to remove loop
    void removeLoop(ListNode loop, ListNode curr) {
        ListNode ptr1 = null;
        ListNode ptr2 = null;
        /* Set a pointer to the beginning of the Linked List and
         move it one by one to find the first node which is
         part of the Linked List */
        ptr1 = curr;
        while (1 == 1) {
            /* Now start a pointer from loop_node and check if it ever
             reaches ptr2 */
            ptr2 = loop;
            while (ptr2.next != loop && ptr2.next != ptr1) {
                ptr2 = ptr2.next;
            }
            /* If ptr2 reahced ptr1 then there is a loop. So break the
             loop */
            if (ptr2.next == ptr1) {
                break;
            }
            /* If ptr2 did't reach ptr1 then try the next node after ptr1 */
            ptr1 = ptr1.next;
        }
        /* After the end of loop ptr2 is the last node of the loop. So
         make next of ptr2 as NULL */
        ptr2.next = null;
    }
}
