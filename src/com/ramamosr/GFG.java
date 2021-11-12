package com.ramamosr;

// Java code to print all possible
// subsequences for given array using
// recursion
import java.util.*;

class GFG{

    // Recursive function to print all
// possible subsequences for given array
    public static void printSubsequences(int[] arr, int index,
                                         ArrayList<Integer> path)
    {

        // Print the subsequence when reach
        // the leaf of recursion tree
        if (index == arr.length)
        {

            // Condition to avoid printing
            // empty subsequence
            if (path.size() > 0)
                System.out.println(path);
        }

        else
        {
            printSubsequences(arr, index + 1, path);
            path.add(arr[index]);
            printSubsequences(arr, index + 1, path);
            path.remove(path.size() - 1);
        }
        return;
    }

    // Driver code
    public static void main(String[] args)
    {
        int[] arr = { 1, 2, 3 };

        // Auxillary space to store each path
        ArrayList<Integer> path = new ArrayList<>();

        printSubsequences(arr, 0, path);
    }
}