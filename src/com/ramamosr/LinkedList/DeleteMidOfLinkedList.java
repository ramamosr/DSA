package com.ramamosr.LinkedList;

public class DeleteMidOfLinkedList {

    class ListNode {
     public int val;
     public ListNode next;
     ListNode(int x) { val = x; next = null; }
 }
    /*
    Given a singly linked list, delete middle of the linked list.

For example, if given linked list is 1->2->3->4->5 then linked list should be modified to 1->2->4->5

If there are even nodes, then there would be two middle nodes, we need to delete the second middle element.

For example, if given linked list is 1->2->3->4->5->6 then it should be modified to 1->2->3->5->6.

Return the head of the linked list after removing the middle node.

If the input linked list has 1 node, then this node should be deleted and a null node should be returned.


Input Format

The only argument given is the node pointing to the head node of the linked list
     */

    public ListNode solve(ListNode A) {
        if(A==null || A.next==null) return null;
        ListNode head = A;
        int count = 0;
        ListNode temp = head;

        while(temp!=null){
            count++;
            temp = temp.next;
        }
        if(count==2){
            A.next = null;
            return head;
        }
        int mid = count / 2;
        temp = head;
        while(mid>1){
            temp = temp.next;
            mid--;
        }
        temp.next = temp.next.next;
        return head;
    }

    static ListNode deleteMid(ListNode head)
    {
        // Base cases
        if (head == null)
            return null;
        if (head.next == null) {
            return null;
        }

        // Initialize slow and fast pointers
        // to reach middle of linked list
        ListNode slow_ptr = head;
        ListNode fast_ptr = head;

        // Find the middle and previous of middle.
        ListNode prev = null;

        // To store previous of slow_ptr
        while (fast_ptr != null
                && fast_ptr.next != null) {
            fast_ptr = fast_ptr.next.next;
            prev = slow_ptr;
            slow_ptr = slow_ptr.next;
        }

        // Delete the middle node
        prev.next = slow_ptr.next;

        return head;
    }
}
