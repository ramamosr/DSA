package com.ramamosr;

public class ExcelColumnNumber {

    /*
    Excel Column Number
Problem Description

Given a column title as appears in an Excel sheet, return its corresponding column number.



Problem Constraints
1 <= length of the column title <= 5



Input Format
Input a string which represents the column title in excel sheet.



Output Format
Return a single integer which represents the corresponding column number.



Example Input
Input 1:

 AB
Input 2:

 ABCD


Example Output
Output 1:

 28
Output 2:

 19010


Example Explanation
Explanation 1:

 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
     */

    public int titleToNumber(String A) {
        int result = 0;
        for(char ch : A.toCharArray()){
            result = result * 26;
            result = result + ch - 'A' + 1;
        }
        return  result;
    }

    public static void main(String[] args) {
        ExcelColumnNumber ex = new ExcelColumnNumber();
        System.out.println(ex.titleToNumber("ABCD"));
    }
}
