package com.ramamosr.ProblemSolvingAssignment;

public class ConstructArrayBruteForceScaler {
    /*
    The only fact required to solve this problem is just to notice that the answer array is just an arithmetic progression.
After that, we can fix the first element ‘a’, fix the difference ‘d’, construct the array [a, a + d, a + 2 * d, …, a + d * (A − 1)], check if B and C are in this array and, if yes, update the answer with this array.
This is O(n^3) solution.
     */
    public int[] solve(final int A, final int B, final int C) {
        int[] ans = new int[A];

        for(int i = 0; i < A; i++){
            ans[i] = Integer.MAX_VALUE;
        }

        for (int a = 1; a <= 50; a++)
        {
            for (int d = 1; d <= 50; d++){
                int[] temp = new int[A];

                for (int i = 0; i < A;i++){
                    temp[i] = a + i * d;
                }

                boolean fB = false;
                boolean fC = false;
                for (int i = 0; i < A;i++){
                    if(temp[i] == B)
                        fB = true;
                    else if (temp[i] == C)
                        fC = true;
                }

                if(fB == true && fC == true && temp[A - 1] < ans[A - 1]){
                    for (int i = 0; i < A;i++){
                        ans[i] = temp[i];
                    }
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {

    }
}
