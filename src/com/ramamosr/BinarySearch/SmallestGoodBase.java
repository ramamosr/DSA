package com.ramamosr.BinarySearch;

public class SmallestGoodBase {

    /*
    Given an integer A, we call k >= 2 a good base of A, if all digits of A base k are 1. Now given a string representing A, you should return the smallest good base of A in string format.


Input Format

The only argument given is the string representing A.
Output Format

Return the smallest good base of A in string format.
Constraints

3 <= A <= 10^18
For Example

Input 1:
    A = "13"
Output 1:
    "3"     (13 in base 3 is 111)

Input 2:
    A = "4681"
Output 2:
    "8"     (4681 in base 8 is 11111).
     */

    public String solve(String A) {
        long num = Long.parseLong(A);
        for(int i=64;i>0;i--){
            long start = 2;
            long end = num - 1;
            while (start<=end){
                long mid = start + ((end-start)/2);
                long sum = 0;
                for(int j=0;j<i;j++){
                    if(sum > num)
                        break;
                    sum += findpow(mid,j,num);
                }
                if(sum > num){
                    end = mid -1;
                }
                else if (sum<num){
                    start = mid + 1;
                }
                else if(sum==num){
                    return String.valueOf(mid);
                }
            }
        }
        return "-1";
    }
    public long findpow(long a, long b, long max) {
        long ans = 1;
        while(b > 0) {
            if(b%2 == 1) {
                ans = ans * a;
                if(ans > max) return max;
            }
            a = a * a;
            b = b/2;
        }
        return ans;
    }

    public static void main(String[] args) {
        SmallestGoodBase smg = new SmallestGoodBase();
        System.out.println(smg.solve("1000000000000000000"));
    }
}
