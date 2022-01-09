pacakge com.ramamosr.StacksAndQueues;

import java.util.Stack;

public class MaxAndMin {

    /*
    Problem Description
Given an array of integers A .value of a array = max(array) - min(array).
Calculate and return the sum of values of all possible subarrays of A % 109+7.
Problem Constraints
1 <= |A| <= 100000
1 <= A[i] <= 1000000
Input Format
The first and only argument given is the integer array A.
Output Format
Return the sum of values of all possible subarrays of A % 10^9+7.
Example Input
Input 1:
A = [1]
Input 2:
A = [4, 7, 3, 8]
Example Output
Output 1:
0
Output 2:
26
Example Explanation
Explanation 1:
Only 1 subarray exists. Its value is 0.
Explanation 2:
value ( [4] ) = 4 - 4 = 0
value ( [7] ) = 7 - 7 = 0
value ( [3] ) = 3 - 3 = 0
value ( [8] ) = 8 - 8 = 0
value ( [4, 7] ) = 7 - 4 = 3
value ( [7, 3] ) = 7 - 3 = 4
value ( [3, 8] ) = 8 - 3 = 5
value ( [4, 7, 3] ) = 7 - 3 = 4
value ( [7, 3, 8] ) = 8 - 3 = 5
value ( [4, 7, 3, 8] ) = 8 - 3 = 5
sum of values % 10^9+7 = 26
     */

 /*   public int solve(int[] A) {

        int len = A.length;
        int mod = 1000 * 1000 * 1000 + 7;
        int[] ngeArr = findNextGreaterElement(A,len);
        int[] lgeArr = findLastGreaterElement(A,len);
        int[] nseArr = findNextSmallerElement(A,len);
        int[] lseArr = findLastSmallerElement(A,len);

        long ans = 0;
        for (int i = 0; i < len; i++) {
            long right = ngeArr[i] - i;
            long left = i - lgeArr[i];
            ans += (((left * right)%mod) * A[i])%mod;
            if(ans<0)
                ans+=mod;
            ans%=mod;
            left = i - lseArr[i];
            right = nseArr[i] - i;
            ans -= (((left * right)%mod) * A[i])%mod;
            if(ans<0)
                ans+=mod;
            ans%=mod;
        }
        return (int) ans;
    }

    public int[] findNextGreaterElement(int[] A, int length){

        Stack<Integer> ngeStack = new Stack<Integer>();
        int[] result = new int[length];
        for(int i=0; i<length;i++){

            while(!ngeStack.isEmpty() && A[ngeStack.peek()] < A[i]){
                  result[ngeStack.pop()] = i;
            }
            ngeStack.push(i);
        }
        return result;
    }

    public int[] findLastGreaterElement(int[] A, int length){

        Stack<Integer> lgeStack = new Stack<Integer>();
        int[] result = new int[length];
        for(int i=length-1; i>=0;i--){

            while(!lgeStack.isEmpty() && A[lgeStack.peek()] <= A[i]){
                result[lgeStack.pop()] = i;
            }
            lgeStack.push(i);
        }
        return result;
    }

    public int[] findLastSmallerElement(int[] A, int length){

        Stack<Integer> lseStack = new Stack<Integer>();
        int[] result = new int[length];
        for(int i=length-1; i>=0;i--){

            while(!lseStack.isEmpty() && A[lseStack.peek()] >= A[i]){
                result[lseStack.pop()] = i;
            }
            lseStack.push(i);
        }
        return result;
    }

    public int[] findNextSmallerElement(int[] A, int length){

        Stack<Integer> nseStack = new Stack<Integer>();
        int[] result = new int[length];
        for(int i=0; i<length;i++){
            while(!nseStack.isEmpty() && A[nseStack.peek()] > A[i]){
                result[nseStack.pop()] = i;
            }
            nseStack.push(i);
        }
        return result;
    }*/

    public int solveScaler(int[] A) {
        int n = A.length, mod = 1000 * 1000 * 1000 + 7;
        int a[] = new int[n + 1];
        int Next_greater_element[] = new int[n + 1];
        int Previous_greater_element[] = new int[n + 1];
        int Previous_smaller_element[] = new int[n + 1];
        int Next_smaller_element[] = new int[n + 1];
        for (int i = 0; i < n; i++) {
            Next_greater_element[i + 1] = n + 1;
            Next_smaller_element[i + 1] = n + 1;
            a[i + 1] = A[i];
        }
        Stack < Integer > s = new Stack < Integer > ();
        for (int i = 1; i <= n; i++) { // this loop calculates next_greater element index
            if (s.empty()) {
                s.push(i);
            } else {
                while (!s.empty() && a[s.peek()] <= a[i]) {
                    Next_greater_element[s.peek()] = i;
                    s.pop();
                }
                s.push(i);
            }
        }
        while (!s.empty()) {
            s.pop();
        }
        for (int i = n; i > 0; i--) { // this loop calculates Previous_greater element index
            if (s.empty()) {
                s.push(i);
            } else {
                while (!s.empty() && a[i] > a[s.peek()]) {
                    Previous_greater_element[s.peek()] = i;
                    s.pop();
                }
                s.push(i);
            }
        }
        while (!s.empty()) {
            s.pop();
        }
        for (int i = n; i > 0; i--) { // this loop calculates Previous smaller element index
            if (s.empty()) {
                s.push(i);
            } else {
                while (!s.empty() && a[i] <= a[s.peek()]) {
                    Previous_smaller_element[s.peek()] = i;
                    s.pop();
                }
                s.push(i);
            }
        }
        while (!s.empty()) {
            s.pop();
        }
        for (int i = 1; i <= n; i++) { // this loop calculates Next smaller element index
            if (s.empty()) {
                s.push(i);
            } else {
                while (!s.empty() && a[i] < a[s.peek()]) {
                    Next_smaller_element[s.peek()] = i;
                    s.pop();
                }
                s.push(i);
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            long right = Next_greater_element[i] - i;
            long left = i - Previous_greater_element[i];
            ans += (((left * right) % mod) * a[i]) % mod;
            ans %= mod;
            left = i - Previous_smaller_element[i];
            right = Next_smaller_element[i] - i;
            ans -= (((left * right) % mod) * a[i]) % mod;
            ans += mod;
            ans %= mod;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        MaxAndMin mm = new MaxAndMin();
        System.out.println(mm.solveScaler(new int[] {4,7,3,8}));
    }
}
