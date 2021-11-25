pacakage com.ramamosr.BinarySearch;

public class CountOccurrences {

    /*
    Problem Description
    Given a sorted array arr[] and a number x,
    write a function that counts the occurrences of x in arr[]. Expected time complexity is O(Logn)

    Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 2
  Output: 4 // x (or 2) occurs 4 times in arr[]

    Solution Approach
    To find the count of indexes, we will have to find the start index and end index of the element
    Use binary search to first find the start index.
    the use the start index to restrict the search space to find the last index.
    count will be the last index - start index +1
     */
    public int solve(int[] A, int B){
        int count = -1;
        int firstIndex, endIndex;

        firstIndex = findFirstIndex(A,0,A.length-1,B);

        if(firstIndex==-1)
            return firstIndex;

        endIndex = findLastIndex(A,firstIndex,A.length-1,B);
        count = endIndex - firstIndex + 1;
        return count;
    }

    public int findFirstIndex(int[] A, int start, int end, int B){

        if(start<=end){
            int mid = (start+end) / 2;

            if((mid == 0 || B > A[mid-1]) && A[mid]==B)
                return mid;

            else if(B > A[mid])
                return findFirstIndex(A,mid+1,end,B);
            else
                return findFirstIndex(A,start,mid-1,B);
        }
        return -1;
    }

    public int findLastIndex(int[] A, int start, int end, int B){

        if(start<=end){
            int mid = (start+end) / 2;

            if((mid == A.length-1 || B < A[mid+1]) && A[mid]==B)
                return mid;

            else if(B < A[mid])
                return findLastIndex(A,start,mid-1,B);
            else
                return findLastIndex(A,mid+1,end,B);
        }
        return -1;
    }

    public static void main(String[] args) {
        CountOccurrences co = new CountOccurrences();
        int[] A = {1,1,2,2,2,2,3,3,4};
        int B = 3;
        System.out.println(co.solve(A,B));
    }
}
