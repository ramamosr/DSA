package com.ramamosr.LinkedList;

public class KReverseLinkedList {
    /*
    Problem Description

Given a singly linked list A and an integer B, reverse the nodes of the list B at a time and return modified linked list.



Problem Constraints

1 <= |A| <= 103

B always divides A



Input Format

The first argument of input contains a pointer to the head of the linked list.

The second arugment of input contains the integer, B.



Output Format

Return a pointer to the head of the modified linked list.



Example Input

Input 1:

 A = [1, 2, 3, 4, 5, 6]
 B = 2
Input 2:

 A = [1, 2, 3, 4, 5, 6]
 B = 3


Example Output

Output 1:

 [2, 1, 4, 3, 6, 5]
Output 2:

 [3, 2, 1, 6, 5, 4]


Example Explanation

Explanation 1:

 For the first example, the list can be reversed in groups of 2.
    [[1, 2], [3, 4], [5, 6]]
 After reversing the K-linked list
    [[2, 1], [4, 3], [6, 5]]
Explanation 2:

 For the second example, the list can be reversed in groups of 3.
    [[1, 2, 3], [4, 5, 6]]
 After reversing the K-linked list
    [[3, 2, 1], [6, 5, 4]]
     */
    /*
    The given linked list can be split into bucket of length K. For doing this, you can use two pointers that are K elements apart in the linked
list.

Now, let us try to solve the problem for one bucket i.e. reversing
a single linked list. For this, you can check this.

So now, our problem has been modified to solving the problem for each bucket and then concatenating the lists to get a final
K-reversed linked list which is just an implementation issue.
     */
    class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null; }
    }

    public ListNode reverseList(ListNode A, int B) {

        if(A==null)
            return null;
        ListNode prev = null, nxtNode = null;
        ListNode curr = A;

        int count = 0;
        while(curr!=null && count <B){
            nxtNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxtNode;
            count++;
        }
        if(nxtNode!=null)
            A.next = reverseList(curr,B);
        return prev;
    }
}
