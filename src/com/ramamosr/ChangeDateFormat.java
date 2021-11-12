package com.ramamosr;

import java.util.HashMap;

public class ChangeDateFormat {

    /*
    Change Date Format
Given a date string in the format Day Month Year, where:

Day is in the set {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", ..., "29th", "30th", "31st"}.
Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
Year is in the inclusive range [1900, 3000].

Convert the date string to the format YYYY-MM-DD, where:

YYYY denotes the 4 digit year.
MM denotes the 2 digit month.
DD denotes the 2 digit day.
For example:

1st Mar 1984 → 1984-03-01
2nd Feb 2013 → 2013-02-02 4th Apr 1900 → 1900-04-04



Input Format

The only argument given is a String, a date in the above-mentioned format.
Output Format

Return a String, i.e date in YYYY-MM-DD format.
Constraints

The values of Day, Month, and Year are restricted to the value ranges specified above.
The given dates are guaranteed to be valid, so no error handling is necessary.
Sample Test 1

Input:
    A = "16th Mar 2068"
Output:
    2068-03-16
Sample Test 2

Input:
    A = "6th Jun 1933"
Output:
    1933-06-06
     */
    public String solve(String A) {
        HashMap<String,String> hm = new HashMap<>();
        hm.put("Jan","01");
        hm.put("Feb","02");
        hm.put("Mar","03");
        hm.put("Apr","04");
        hm.put("May","05");
        hm.put("Jun","06");
        hm.put("Jul","07");
        hm.put("Aug","08");
        hm.put("Sep","09");
        hm.put("Oct","10");
        hm.put("Nov","11");
        hm.put("Dec","12");

        StringBuilder sb = new StringBuilder();
        String[] str = new String[3];
        str = A.split(" ");
        String strDate = str[0].length()==4?str[0].substring(0,2): "0" + str[0].substring(0,1);
        String strMonth = hm.get(str[1]);
        String strYear = str[2];

        return strYear + "-" + strMonth + "-" + strDate;


    }

    public static void main(String[] args) {
        ChangeDateFormat cdf = new ChangeDateFormat();
        System.out.println(cdf.solve("6th Jun 1933"));
    }
}
