package com.ramamosr.MathProb;

public class SquareGranites {
    /*
    Square granites
Problem Description

A city is of rectangular shape with the size A * B meters. On the occasion of the city's anniversary, a decision was taken to pave the city with square granite flagstones. Each flagstone is of the size C * C. What is the least number of flagstones needed to pave the city?

NOTE: It's allowed to cover the surface larger than the city, but the city has to be covered. It's not allowed to break the flagstones. The sides of flagstones should be parallel to the sides of the city.



Problem Constraints
1 <= A <= 109

1 <= B <= 109

1 <= C <= 109



Input Format
First argument is an integer A as per the question.

Second argument is an integer B as per the question.

Third argument is an integer C as per the question.



Output Format
Return an integer showing least number of flagstones needed.



Example Input
Input 1:

A=6, B=6, C=4


Example Output
Output 1:

4


Example Explanation
Explanation 1:

If we use 4 flagstones(2 rows and 2 columns) then it will cover the whole city.
     */

    public int solve(int A, int B, int C) {
        double len = Math.ceil((double)A/(double)C);
        double wid = Math.ceil((double)B/(double)C);
        return (int) (len * wid);
//         Scaler Approach
 //       Just you have to caluclate greatestInteger(A/C) and greatestInteger(B/C) and return their product from the function.
//        return ((A+C-1)/C)*((B+C-1)/C);

    }

    public static void main(String[] args) {
        SquareGranites sg = new SquareGranites();
        System.out.println(sg.solve(6,6,4));
    }
}
