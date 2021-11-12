package com.ramamosr;

import java.util.Arrays;
import java.lang.Math.*;

public class ArrayAssignment {
    /* FizzBuzz
Problem Description
Given a positive integer A, return an array of strings with all the integers from 1 to N. But for multiples of 3 the array should have “Fizz” instead of the number. For the multiples of 5, the array should have “Buzz” instead of the number. For numbers which are multiple of 3 and 5 both, the array should have "FizzBuzz" instead of the number.

Look at the example for more details.
Problem Constraints
1 <= A <= 500000
Input Format
The first argument has the integer A.
Output Format
Return an array of string.
Example Input
Input 1:
 A = 5

Example Output
Output 1:
 [1 2 Fizz 4 Buzz]
Example Explanation
Explanation 1:
 3 is divisible by 3 so it is replaced by "Fizz".
 Similarly, 5 is divisible by 5 so it is replaced by "Buzz".*/

    public String[] fizzBuzz(int A) {
        String[] result = new String[A];
        for(int i=1;i<=A;i++){
            //check if A is divisible by 15. mod (3*5)
            if(i%15==0)
                result[i-1] = "FizzBuzz";
            else if(i%5==0)
                result[i-1] = "Buzz";
            else if(i%3==0)
                result[i-1]= "Fizz";
            else
                result[i-1] = Integer.toString(i);
            }
        return result;
        }

/*Time to equality
Problem Description
Given an integer array A of size N. In one second you can increase the value of one element by 1.

Find the minimum time in seconds to make all elements of the array equal.
Problem Constraints
1 <= N <= 1000000
1 <= A[i] <= 1000

Input Format
First argument is an integer array A.
Output Format
Return an integer denoting the minimum time to make all elements equal.

Example Input
A = [2, 4, 1, 3, 2]
Example Output
8
Example Explanation
We can change the array A = [4, 4, 4, 4, 4]. The time required will be 8 seconds.
*/

    public int timeToEquality (int[] A){
        Arrays.sort(A);
        int timeTaken = 0;
        for(int i=0; i<A.length;i++){
            timeTaken += A[A.length-1] - A[i];
        }
        return timeTaken;
    }

    /*
    Good Pair
Problem Description
Given an array A and a integer B. A pair(i,j) in the array is a good pair if i!=j and (A[i]+A[j]==B). Check if any good pair exist or not.
Problem Constraints
1 <= A.size() <= 104
1 <= A[i] <= 109
1 <= B <= 109

Input Format
First argument is an integer array A.
Second argument is an integer B.

Output Format
Return 1 if good pair exist otherwise return 0.

Example Input
Input 1:
A = [1,2,3,4]
B = 7

Input 2:
A = [1,2,4]
B = 4

Input 3:
A = [1,2,2]
B = 4

Example Output
Output 1:
1
Output 2:
0
Output 3:
1


Example Explanation
Explanation 1:

(i,j) = (3,4)
Explanation 2:
No pair has sum equal to 4.
Explanation 3:
 (i,j) = (2,3)
     */

    public int goodPair(int[] A, int B){
        Arrays.sort(A);;
        for(int i=0,j=A.length-1; i<j;){
            if(A[i]+A[j]==B)
                return 1;
            else if(A[i]+A[j] <B)
                i++;
            else if(A[i]+A[j] >B)
                j--;
        }
        return 0;
    }


    /*
    Odd Even Subsequences
Given an array of integers A of size N. Find the longest subsequence of A which is odd-even.
A subsequence is said to odd-even in the following cases:
The first element of the subsequence is odd, second element is even, third element is odd and so on. For example: [5, 10, 5, 2, 1, 4], [1, 2, 3, 4, 5]
The first element of the subsequence is even, second element is odd, third element is even and so on. For example: [10, 5, 2, 1, 4, 7], [10, 1, 2, 3, 4]
Return the maximum possible length of odd-even subsequence.

Note: An array B is a subsequence of an array A if B can be obtained from A by deletion of several (possibly, zero or all) elements.

Input Format
The only argument given is the integer array A.
Output Format

Return the maximum possible length of odd-even subsequence.
Constraints
1 <= N <= 100000
1 <= A[i] <= 10^9
For Example

Input 1:
    A = [1, 2, 2, 5, 6]
Output 1:
    4
    Explanation 1:
        Maximum length odd even subsequence is [1, 2, 5, 6]

Input 2:
    A = [2, 2, 2, 2, 2, 2]
Output 2:
    1
    Explanation 2:
        Maximum length odd even subsequence is [2]
     */

    public int findLongestSubSequence(int[] A){

        int[] subSequenceLength = new int[A.length];
        int longest = 0;
        int[][] subsequence = new int[A.length][A.length];

        // the minimum length of all subsequences will be 1.
        // initiallize all elements of subsequenceLength to 1
        for(int i=0;i <A.length;i++){
            subSequenceLength[i] = 1;
        }

        //loop through all columns of a row.
        for(int col=1;col<A.length;col++){
            //start second loop with 0 to col for the same row for comparison
            // A[col] should be greater than the previous element.
            // they both cannot be odd or even. Addition of odd + even mod 2 gives remainder of 1.
            // if mod is 0, then they both cannot be next to each other. Loop until the value of col

            for(int prev = 0;prev<col;prev++){
                if(((A[col] + A[prev])%2!=0)
                && subSequenceLength[col] < (subSequenceLength[prev] + 1)){
                        subSequenceLength[col] = subSequenceLength[prev] + 1;
                }
            }
        }
        for(int i =0;i<A.length; i++){
            longest = longest < subSequenceLength[i]? subSequenceLength[i]:longest;
        }
        return longest;
    }

    // Solution Approach By Scaler with Bit Manipulation
    public int findLongestSubSequenceByScaler(int[] A){
        int firstSequence = 0, secondSequence = 0;
        int odd =1, even = 0;
        for(int i : A){
            // Take each element in A and do an bitwise AND with 1. if the result is 1
            //  the number is odd. the next number should be even to be considered for the subsequence
            // so flip the odd to even by doing a bitwise XOR with 1.
            if((i&1) == odd){
                firstSequence +=1;
                odd = odd ^ 1;
            }
            if((i&1) == even){
                secondSequence +=1;
                even = even ^ 1;
            }
        }
        return Math.max(firstSequence,secondSequence);

    }

    public static void main(String[] args) {
        ArrayAssignment arr = new ArrayAssignment();
        //System.out.println(arr.fizzBuzz(30));
        int[] a = {2,4,1,3,2,4,3,1,10};
        //System.out.println(arr.timeToEquality(a));
        int[] b = {1,2,3,5,4,4};
        int check = 8;
       // System.out.println(arr.goodPair(b,check));
        int[] subsequence = {16, 50, 2,3,4,9,};
        System.out.println(arr.findLongestSubSequence(subsequence));
        System.out.println(arr.findLongestSubSequenceByScaler(subsequence));

    }
}
