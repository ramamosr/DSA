package com.ramamosr;

public class RotateString {
    /*
    Rotate string
Problem Description

Given a string A, rotate the string B times in clockwise direction and return the string.



Problem Constraints
1 <= |A| <= 105

1 <= B <= 109

String A consist only of lowercase characters.



Input Format
First and only argument is a string A.



Output Format
Return a string denoting the rotated string.



Example Input
Input 1:

 A = "scaler"
 B = 2
Input 2:

 A = "academy"
 B = 7


Example Output
Output 1:

 "erscal"
Output 2:

 "academy"


Example Explanation
Explanation 1:

 Rotate the given string twice so the string becomes "erscal".
     */
    public String solve(String A, int B) {
        if(B==A.length() || B==0)
            return A;
        B = B%A.length();
        StringBuilder s = new StringBuilder(A);
        String result = A.substring(A.length()-B,A.length());
        result = result + s.substring(0,A.length()-B);
        return result;

    }
    public static void main(String[] args) {
        RotateString rst = new RotateString();
        System.out.println(rst.solve("jadgbxjesitcdbnbkftdv",29));
    }
}
