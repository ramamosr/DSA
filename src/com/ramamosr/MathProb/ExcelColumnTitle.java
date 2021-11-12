package com.ramamosr.MathProb;


public class ExcelColumnTitle {

    public String convertToTitle(int A) {
        String title = "";
        while(A>0){
            // Reduce A by -1, since the alphabets are relative to character A.
            // B is 1st character after character 'A'. Z is 25th character from 'A'
            A = A-1;
            char temp = (char)('A'+ (A%26));
            title = temp + title;
            A = A/26;
        }
        return title;
    }

    public static void main(String[] args) {

        ExcelColumnTitle et = new ExcelColumnTitle();
        System.out.println(et.convertToTitle(943566));
    }
}
