package com.ramamosr.LinkedList;

public class ReOrderList {

    /*
    Problem Description
Given a singly linked list A
 A: A0 → A1 → … → An-1 → An
reorder it to:
 A0 → An → A1 → An-1 → A2 → An-2 → …
You must do this in-place without altering the nodes' values.
Problem Constraints
1 <= |A| <= 106
Input Format
The first and the only argument of input contains a pointer to the head of the linked list A.
Output Format
Return a pointer to the head of the modified linked list.
Example Input
Input 1:
 A = [1, 2, 3, 4, 5]
Input 2:
 A = [1, 2, 3, 4]
Example Output
Output 1:
 [1, 5, 2, 4, 3]
Output 2:
[1, 4, 2, 3]
Example Explanation
Explanation 1:
The array will be arranged to [A0, An, A1, An-1, A2].
Explanation 2:
The array will be arranged to [A0, An, A1, An-1, A2].
*/
    /*
    Solution Approach.
    Find the middle of the list. If the list is even length, stop at the first mid.
    Then mark the pointer of the mid node to be null.
    Reverse the second half of the list after split.
    Merge the two lists
     */
    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reorderList(ListNode A) {

        if(A==null || A.next==null) return A;
        ListNode mid = findMiddle(A);
        ListNode secondList = mid.next;
        mid.next=null;
        secondList = reverseList(secondList);
        ListNode curr = A;
        while(secondList!=null){
            ListNode temp = secondList;
            secondList = secondList.next;
            temp.next = curr.next;
            curr.next = temp;
            curr = curr.next.next;
        }
        return A;
    }

    public ListNode findMiddle(ListNode A) {

        ListNode fast = A;
        ListNode slow = A;

        while(fast!=null && fast.next!=null){
            if(fast.next.next==null)
                break;
            else{
                fast = fast.next.next;
                slow = slow.next;
            }
        }
        return slow;
    }

    public ListNode reverseList(ListNode A) {
        ListNode prev = null, nxtNode = null;
        ListNode curr = A;

        while(curr!=null){
            nxtNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxtNode;
        }
        return prev;
    }
}
