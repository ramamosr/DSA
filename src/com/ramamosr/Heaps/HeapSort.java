package com.ramamosr.Heaps;

public class HeapSort {

    int parent(int i){
        return ((i-1)/ 2);
    }

    int left(int i){
        return (2*i + 1);
    }

    int right(int i){
        return (2*i + 2);
    }

    public int[] sort(int[] A){
        int len = A.length;

        // Build the max heap. Starting from the first non leaf node which is
        //calculated by A.length/2 -1;
        for(int i = A.length/2 - 1; i>=0;i--){
            MaxHeapify(A,A.length,i);
        }
        for(int j = A.length-1;j>0; j--){
            // Swap the elements and heapify from the 0th element
            // to the jth element.
                int temp = A[0];
                A[0]  = A[j];
                A[j] = temp;
                MaxHeapify(A,j,0);
        }
        return A;
    }

    public void MaxHeapify(int[] A,int len, int index) {
        while (left(index) < len && A[left(index)] > A[index]
                || (right(index) < len && A[right(index)] > A[index])) {
            int largest = left(index);
            if (right(index) < len && A[right(index)] > A[largest]) {
                largest = right(index);
            }
            // swap the current Node with the smallest and move the currNode to the smallest node;
            if(largest==index)
                return;
            int temp = A[index];
            A[index] = A[largest];
            A[largest] =  temp;
            index = largest;
        }
    }
    public static void main(String[] args) {
        int[] input = {12, 11, 13, 5, 6, 7};
        HeapSort hs = new HeapSort();
        hs.sort(input);
    }
}
