public class AthMagicalNumber {
    /*
Problem Description

Given three positive integers A, B and C.

Any positive integer is magical if it is divisible by either B or C.

Return the Ath magical number. Since the answer may be very large, return it modulo 109 + 7.
Problem Constraints

1 <= A <= 109
2 <= B, C <= 40000
Input Format
The first argument given is an integer A.
The second argument given is an integer B.
The third argument given is an integer C.

Output Format
Return the Ath magical number. Since the answer may be very large, return it modulo 109 + 7.
Example Input
Input 1:
 A = 1
 B = 2
 C = 3
Input 2:
 A = 4
 B = 2
 C = 3
Example Output
Output 1:
 2
Output 2:
6
Example Explanation

Explanation 1:

 1st magical number is 2.
Explanation 2:

 First four magical numbers are 2, 3, 4, 6 so the 4th magical number is 6.     */

    /*
    Solution Approach
    The Ath term for this problem can be solved by the equation
    Ath term = TermNumber/B + TermNumber/C - TermNumber/lcm(B,C)
    lcm = B*C / gcd(B,C)
    To find the Ath Term, we will use recursion.
    If the term count is less than A, we move right, increase the left by 1.
    If its greater than or equal to N, we move left, setting the right to the mid position.
     */

    /*
    Say L = lcm(B,C), the least common multiple of B and C and let f(x) be the number of magical numbers less than or equal to x.
A well known result says that L = (B*C)/gcd(B,C), and that we can calculate the function gcd.

Then f(x) = x/B + x/C - x/L (floor of all the divisions)

Why? There are x/B numbers B,2B,3B… thaare divisible by B, there are x/C numbers divisible by C and we need to subtract the x/L numbers divisible by B and C that we double counted.

Finally,the answer must be between 0 and A * min(B,C).

If x increase f(x) increases so we can use binary search on x to find the Ath number.

Algorithm:
1) Low=1 and High = A * min(B,C)
2) Find mid = (low + high)/2
3) Find f(mid) let it be count
4) If count>=A then mark it as a answer and try to find smaller number which implies high = mid-1
5) Else low = mid+1
6) Repeat steps 2 to 5 until low<=high

Time Complexity: O(log (A * min(B, C)))
Space Complexity: O(1).
     */

    public int getGCD(int B, int C){
        if(B==0)
            return C;
        return getGCD(C%B, B);
    }

    public int calculateTerm(int A, int B, int C){

        long max = Long.MAX_VALUE;
        long start = 1, ans = 0;
        int mod = 1000000007;
        while(start<max){

            long mid = start + ((max-start)/2);
            long lcm = (long)(B * C) / getGCD(B,C);
            long temp = (mid / B) + (mid / C) - (mid/lcm);
            if(temp<A){
                start = mid + 1;
            }else
                max = mid;
        }
        return (int)(start%mod);
    }

    public int solve(int A, int B, int C) {
        return calculateTerm(A,B,C);
    }

    public int solveScaler(int A, int B, int C) {
        long lcm = (long) B * C / getGCD(B, C);
        long low = 2, high = (long) 1e15, ans = 2;
        while (low <= high) {
            long mid = (high - low) / 2 + low;
            // count numbers <= mid divisible by B, C and both B, C.
            long cntB = mid / B, cntC = mid / C, cntBC = mid / lcm;
            if (cntB + cntC - cntBC >= A) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
    }

    public static void main(String[] args) {
        AthMagicalNumber amn = new AthMagicalNumber();
        System.out.println(amn.solve(807414236,3788,38141));
    }
}
