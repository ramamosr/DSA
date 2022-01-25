package com.ramamosr.LinkedList;    

public class ReverseBetweenLinkedList {

        /*
        Problem Description

    Reverse a linked list A from position B to C.

    NOTE: Do it in-place and in one-pass.



    Problem Constraints

    1 <= |A| <= 106

    1 <= B <= C <= |A|



    Input Format

    The first argument contains a pointer to the head of the given linked list, A.

    The second arugment contains an integer, B.

    The third argument contains an integer C.



    Output Format

    Return a pointer to the head of the modified linked list.



    Example Input

    Input 1:

     A = 1 -> 2 -> 3 -> 4 -> 5
     B = 2
     C = 4

    Input 2:

     A = 1 -> 2 -> 3 -> 4 -> 5
     B = 1
     C = 5


    Example Output

    Output 1:

     1 -> 4 -> 3 -> 2 -> 5
    Output 2:

     5 -> 4 -> 3 -> 2 -> 1


    Example Explanation

    Explanation 1:

     In the first example, we want to reverse the highlighted part of the given linked list : 1 -> 2 -> 3 -> 4 -> 5
     Thus, the output is 1 -> 4 -> 3 -> 2 -> 5
    Explanation 2:

     In the second example, we want to reverse the highlighted part of the given linked list : 1 -> 4 -> 3 -> 2 -> 5
     Thus, the output is 5 -> 4 -> 3 -> 2 -> 1
         */

        /*
        If you are still stuck at reversing the full linked list problem,
    then would maintaining curNode, nextNode and a tmpNode help ?

    Maybe at every step, you do something like this :

        tmp = nextNode->next;
                nextNode->next = cur;
                cur = nextNode;
                nextNode = tmp;
    Now, lets say you did solve the problem of reversing the linked list and are stuck at applying it to current problem.
    What if your function reverses the linked list and returns the endNode of the list.
    You can simply do
    prevNodeOfFirstNode->next = everseLinkedList(curNode, n - m + 1);

    Explanation in the video:
    We can also find the two pointers between which the list needs to be reversed and only reverse that portion.
    We will also have to make two new connections, one from the node just before the first node in the original portion to the node at the starting of the reversed portion.
    Another from the first node of the original portion to the node after the last node in the original portion.
         */
        class ListNode {
            public int val;
            public ListNode next;
            ListNode(int x) { val = x; next = null; }
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

        public ListNode reverseBetween(ListNode A, int B, int C) {

            if(B>=C)
                return A;

            // start and end nodes based on the range of inputs B & C.
            ListNode start = null, end = null;
            //prev - node before the start node;
            //nxt is the node after the end node;
            ListNode prev = null, nxt = null;

            int count = 1;

            ListNode head = A;
            ListNode curr = head;

            while(curr!=null && count<=C){

                if(count<B)
                    prev = curr;
                if(count==B)
                    start = curr;
                if(count==C){
                    end = curr;
                    nxt = curr.next;
                }
                curr = curr.next;
                count++;
            }
            end.next = null;
            end = reverseList(start);

            // POint the prev/header element to the end element.
            if(prev!=null)
                prev.next = end;
            else
                head = end;
            // the start element will be the end element. so point to the next element
            // after the end element.
            start.next = nxt;
            return head;
        }

        public ListNode reverseBetweenScaler(ListNode A, int m, int n) {
            int i;
            ListNode node = A;
            ListNode prev = null;
            m--;
            n--;
            for (i = 0; i < m; i++) {
                prev = node;
                node = node.next;
            }
            if (prev != null)
                prev.next = reverseListScaler(node, n - m + 1);
            else
                A = reverseListScaler(node, n - m + 1);
            return A;
        }

        public ListNode reverseListScaler(ListNode A, int count) {
            ListNode node, prev, temp, last;
            node = A;
            last = A;
            if (node == null)
                return null;
            prev = null;
            while (node != null && count > 0) {
                temp = node.next;
                node.next = prev;
                prev = node;
                node = temp;
                count--;
            }
            last.next = node;
            return prev;
        }
    }
