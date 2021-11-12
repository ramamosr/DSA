package com.ramamosr.MathProb;
/* Approach.
Lets start by looking at the first character.

If the first character is X, all permutations which had the first character less than X would come before this permutation when sorted lexicographically.

Number of permutation with a character C as the first character = number of permutation possible with remaining N-1 character = (N-1)!

Then the problem reduces to finding the rank of the permutation after removing the first character from the set.

Hence,

rank = number of characters less than first character * (N-1)! + rank of permutation of string with the first character removed
Example
Lets say out string is “VIEW”.

Character 1 : 'V'
All permutations which start with 'I', 'E' would come before 'VIEW'.
Number of such permutations = 3! * 2 = 12

Lets now remove ‘V’ and look at the rank of the permutation ‘IEW’.

Character 2 : ‘I’
All permutations which start with ‘E’ will come before ‘IEW’
Number of such permutation = 2! = 2.

Now, we will limit ourself to the rank of ‘EW’.

Character 3:
‘EW’ is the first permutation when the 2 permutations are arranged.

So, we see that there are 12 + 2 = 14 permutations that would come before "VIEW".
Hence, rank of permutation = 15.

Still unable to solve the problem after this h
 */
public class SortedPermRankScaler {
    private int mod = 1000003;
    public int fact(int n) {
        if(n == 0 || n == 1)
            return 1;
        else
            return (n * fact(n - 1)) % mod;
    }
    public int findRank(String A) {
        int ans = 0;
        int n = A.length();
        for(int i = 0; i < n - 1; i++) {
            int count = 0;
            for(int j = i+1; j < n; j++)
                if(A.charAt(j) < A.charAt(i))
                    count++;

            ans += (count * fact(n - i - 1)) % mod;
        }
        return (ans + 1) % mod;
    }
}
