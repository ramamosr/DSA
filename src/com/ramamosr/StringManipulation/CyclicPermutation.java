package com.ramamosr.StringManipulation;

public class CyclicPermutation {
    /*
    Given two binary strings A and B, count how many cyclic permutations of B when taken XOR with A give 0.
NOTE: If there is a string, S0, S1, ... Sn-1 , then it's cyclic permutation is of the form Sk, Sk+1, ... Sn-1, S0, S1, ... Sk-1 where k can be any integer from 0 to N-1.

Problem Constraints
1 ≤ length(A) = length(B) ≤ 105
Input Format
First argument is a string A.
Second argument is a string B.

Output Format
Return an integer denoting the required answer.

Example Input
Input 1:
 A = "1001"
 B = "0011"
Input 2:

 A = "111"
 B = "111"
Example Output
Output 1:
 1
Output 2:
 3

Example Explanation
Explanation 1:
 4 cyclic permutations of B exists: "0011", "0110", "1100", "1001".
 There is only one cyclic permutation of B i.e. "1001" which has 0 xor with A.
Explanation 2:
 All cyclic permutations of B are same as A and give 0 when taken xor with A. So, the ans is 3.
     */

    public int solve(String A, String B){
        // Only A XOR A will result in 0.
        // So the idea is to find the number of cyclic permutations of B that
        // will be equal to A.

        // Idea is to pattern matching.
        // To find the cyclic permutations of B, append B to itself and remove the last element.
        B = B + B;
        B = B.substring(0,B.length()-1);
        int count = 0;
        String str = A + "$" + B;
        int[] zArr = PopulateZArray(str);
        for(int i=1;i < str.length();i++){
            if(zArr[i]==A.length())
                count++;
        }
        return count;
    }

    public int[] PopulateZArray(String A){
        int[] z = new int[A.length()];
        int left = 0, right = 0;
        int n = A.length();
        z[0] = n;

        for(int i=1; i<n;i++){

            if(i>right){
                left = i;
                right = i;
                while(right<n && A.charAt(right)==A.charAt(right-left)){
                    right++;
                }
                z[i] = right - left;
                // since right pointer would have move to the next item, reduce r by 1.
                right--;
            }
            else{
                int j = i - left;
                if(z[j] < right-i+1){
                    z[i] = z[j];
                }
                else{
                    left = i;
                    while(right<n && A.charAt(right)==A.charAt(right-left)){
                        right++;
                    }
                    z[i] = right - left;
                    // since right pointer would have move to the next item, reduce r by 1.
                    right--;

                }
            }
        }
        return z;
    }

    public static void main(String[] args) {
        String A = "1001";
        String B = "0011";
        CyclicPermutation cp = new CyclicPermutation();
        System.out.println(cp.solve(A,B));

    }
}
