package com.ramamosr.Sorting;

public class QuickSort {

    public void quickSort(int[] A, int start, int end){

        if(start < end){
            int pivot = partition(A,start,end);
            quickSort(A,start,pivot-1);
            quickSort(A,pivot+1, end);
        }
    }

    public int partition(int[] A, int start, int end){

        // Pick the random pivot element.
        int elements = end - start;
        double rand = Math.random();
        int pivot = (int) ((rand) * (elements));
        swap(A,start+pivot,end);
        // loop through the array. move all the smaller elements to the left of the pivot
        // and greater elements to the right of the pivot
        int pivotElement = A[end];
        int i = start - 1;
        for(int j= start;j<end;j++){
            if(A[j] < pivotElement) {
                i++;
                swap(A,i,j);
            }
        }
        // Since the pivot element is placed at the end initially,
        // move the pivot element to the correct position
        swap(A,i+1,end);
        return i+1;
    }

    public void swap(int[] A, int low, int high){
        int temp = A[low];
        A[low] = A[high];
        A[high] = temp;
    }

    public static void main(String[] args) {

        QuickSort qs = new QuickSort();
        int[] A = new int[]{5,8,2,1,6};
        qs.quickSort(A,0,A.length-1);
        System.out.println(A[0]);
    }
}
