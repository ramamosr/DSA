package com.ramamosr.LinkedList;

import java.util.HashMap;
public class LRUCache {

    /*
    Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least recently used item before inserting the new item.
The LRUCache will be initialized with an integer corresponding to its capacity. Capacity indicates the maximum number of unique keys it can hold at a time.

Definition of "least recently used" : An access to an item is defined as a get or a set operation of the item. "Least recently used" item is the one with the oldest access time.

NOTE: If you are using any global variables, make sure to clear them in the constructor.

Example :

Input :
         capacity = 2
         set(1, 10)
         set(5, 12)
         get(5)        returns 12
         get(1)        returns 10
         get(10)       returns -1
         set(6, 14)    this pushes out key = 5 as LRU is full.
         get(5)        returns -1
     */

    /*
    Scaler approach
    As discussed in the previous hint, we solve this problem using :

originalMap : A hashmap which stores the orginial key to value mapping
accessList : A doubly linked list which has keys ordered by their access time ( Most recently used key in front of the list to least recently used key at the end of the list ).
addressMap : A hashmap which saves the mapping of key to address of the key in accessList.
Lets see now how the get and set operation would work :

get(key) :
Look for the value corresponding to key in originalMap.
If key is not found, we don’t need to change accessList. So, return -1.
If key is found, then we need to move the node corresponding to the key to front of the list in accessList.
To do that, we find the address of the node for key from addressMap. We make the node->prev->next = node->next;, node->next->prev = node->prev;, node->prev = NULL; node->next = head; head->prev = node;
We update the head of the accessList to be node now.

set(key, value)
If the key is a new entry (it does not exist in the originalMap) AND the cache is full(size = capacity), then we would need to remove the least recently used key lkey.
We know that this key is the key corresponding to the last node in accessList which is accessible in O(1). To evict, we delete the last node from accessList, and the entry corresponding to lkey(from last node) in addressMap and originalMap.

Post this, there are 2 cases.

key is a new entry and is not present in originalMap. In this case, a new node is created for key and inserted at the head of accessList. A new (key,value) entry is created into originalMap and addressMap accordingly.
key is present in the map, in which case the value needs to be updated. We update the value in the originalMap and then we update the accessList for key exactly the way we did in the case of get operation.
A couple of insights for clean code :

Note that the update of accessList for key when key is present is a common operation in get and a subcase of set function. We should create a function for it and call that function in both cases to reduce redundancy.
Also, note that originalMap and addressMap are always of the same size with the same keys ( One with value and the other with address in linkedlist ). If you want to manage less maps, you can just have a masterMap which maps key to a pair of (value, address_in_list)
     */

    class Node{
        int data;
        int value;
        Node next;
        Node prev;

        public Node(int x,int y){
            data = x;
            value = y;
            next = null;
            prev = null;
        }
    }

    HashMap<Integer,Node> hm;
    Node head;
    Node tail;
    int size;
    int maxCapacity;

    public LRUCache(int capacity){
        hm = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        size = 0;
        maxCapacity = capacity;
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

    public int get(int key) {

        // if the key exists, it means the page was visited again by using a get.
        // return the value. But before returning the value, move it to the end of the list
        // as it was accessed by the last get call.
        if(hm.containsKey(key)){
            Node temp = hm.get(key);
            remove(temp);
            addToList(temp);
            return temp.value;
        }
        else
            return -1;
    }

    public void set(int key, int value) {
        // if the data exists in the hashmap, its been accessed again
        // so remove the node from the current position
        // and add it just before the dummy tail.
        // update the value in the hashmap for the key.
        if(hm.containsKey(key)){
            Node temp = hm.get(key);
            temp.value = value;
            hm.put(key,temp);
            //Remove the node from the linked list
            remove(temp);
            addToList(temp);
        }
        else{
            // if the key doesnt exist, it means its a new entry.
            // if there is space available, just insert the node at the last just before the dummy tail.
            // if there is no space, then the head.next element, should be removed first before adding this
            //element before the tail.
            if(size==maxCapacity){
                hm.remove(head.next.data);
                remove(head.next);
                size--;
            }
            Node temp = new Node(key,value);
            temp = addToList(temp);
            hm.put(key,temp);
            size++;
        }
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public Node addToList(Node node){
        node.next = tail;
        node.prev = tail.prev;
        node.prev.next = node;
        tail.prev = node;
        return node;
    }
}
