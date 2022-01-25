package com.ramamosr.LinkedList;

public class LinkedList {
    /*
        Problem Description

    Design and implement a Linked List data structure. A node in a linked list should have the following attributes - an integer value and a pointer to the next node. It should support the following operations:

    insert_node(position, value) - To insert the input value at the given position in the linked list.
    delete_node(position) - Delete the value at the given position from the linked list.
    print_ll() - Print the entire linked list, such that each element is followed by a single space.
    Note:

    If an input position does not satisfy the constraint, no action is required.
    Each print query has to be executed in new line.


    Problem Constraints

    1 <= position <= n where, n is the size of the linked-list.



    Input Format

    First line contains an integer denoting number of cases, let's say t.
    Next t line denotes the cases.


    Output Format

    When there is a case of print_ll(),  Print the entire linked list, such that each element is followed by a single space.
    NOTE: You don't need to return anything.


    Example Input

    5
    i 1 23
    i 2 24
    p
    d 1
    p


    Example Output

    23 24
    24


    Example Explanation

    After first two cases linked list contains two elements 23 and 24.
    At third case print: 23 24.
    At fourth case delete value at first position, only one element left 24.
    At fifth case print: 24.
         */
    static Node head;
    static int length;
    public static class Node{
        int data;
        Node next;

        public Node(int value){
            data = value;
            next = null;
        }
    }

    public static void insert_node(int position, int value) {
        // @params position, integer
        // @params value, integer
        Node newNode = new Node(value);
        // insert node at the head. position 1.
        if(position > 0 && position <=length+1) {
            if (position == 1) {
                newNode.next = head;
                head = newNode;
            } else {
                Node temp = head;
                int count = 1;
                while (position > count + 1) {
                    temp = temp.next;
                    count++;
                }
                newNode.next = temp.next;
                temp.next = newNode;
            }
            length++;
        }
    }

    public static void delete_node(int position) {
        // @params position, integer

        Node temp = head;
        Node prev = null;
        int count = 1;

        if(position > 0 && position <=length){
            while(count<position){
                prev = temp;
                temp = temp.next;
                count++;
            }
            if(prev==null){
                prev = head.next;
                head = prev;
            }
            else {
                prev.next = temp.next;
                temp = null;
            }
            length--;
        }
    }

    public static void print_ll() {
        // Output each element followed by a space
        Node temp = head;
        while(temp!=null){
            if(temp.next!=null)
                System.out.print(temp.data + " ");
            else
                System.out.print(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        Node node = new Node(5);
        insert_node(0,5);
    }
}
