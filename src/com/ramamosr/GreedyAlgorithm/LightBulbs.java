package com.ramamosr.GreedyAlgorithm;

import java.util.ArrayList;

public class LightBulbs {
    /*
    Bulbs
Problem Description

N light bulbs are connected by a wire.

Each bulb has a switch associated with it, however due to faulty wiring, a switch also changes the state of all the bulbs to the right of current bulb.

Given an initial state of all bulbs, find the minimum number of switches you have to press to turn on all the bulbs.

You can press the same switch multiple times.

Note: 0 represents the bulb is off and 1 represents the bulb is on.



Problem Constraints
1 <= N <= 5*105
0 <= A[i] <= 1



Input Format
The first and the only argument contains an integer array A, of size N.



Output Format
Return an integer representing the minimum number of switches required.



Example Input
Input 1:

 A = [0, 1, 0, 1]
Input 2:

 A = [1, 1, 1, 1]


Example Output
Output 1:

 4
Output 2:

 0


Example Explanation
Explanation 1:

 press switch 0 : [1 0 1 0]
 press switch 1 : [1 1 0 1]
 press switch 2 : [1 1 1 0]
 press switch 3 : [1 1 1 1]
Explanation 2:

 There is no need to turn any switches as all the bulbs are already on.
     */

    /*
        Approach: start from left always. if left most is 0, increment the count by 1 as you
        will have to switch on once. But it switches of the next one.
        when we move to the next one, the value you get is 1, where in it should be 0 after 1st swtich.
        so if the value is still 1, and switch count is 1, (the actual value at that ith location should be 0,
        and we are not modifying the states). then add the switch count to 0. Do this till the end of the array.
        for 0, check for even count and for 1 check for odd count.
    */
    public int bulbs(int[] A) {

        int allOn = 0;
        for(int j= 0; j< A.length;j++){
            if(A[j]== 0){
                if(allOn%2==0)
                    allOn+=1;
            }
            else{
                if(allOn%2==1)
                    allOn+=1;
            }
        }
        return allOn;
    }
    /*
    Scaler Approach
    The order in which you press the switch does not affect the final state.

Example:

Input : [0 1 0 1]

Case 1:
	Press switch 0 : [1 0 1 0]
	Press switch 1 : [1 1 0 1]

Case 2:
	Press switch 1 : [0 0 1 0]
	Press switch 0 : [1 1 0 1]
Therefore we can choose a particular order. To make things easier lets go from left to right.
At the current position if the bulb is on we move to the right, else we switch it on.
This works because changing any switch to the right of it will not affect it anymore.
     */
    public int bulbsScalre(ArrayList< Integer > A) {

        int state = 0, ans = 0;

        // state variable will represent the state which we have to change.
        for (int i = 0; i < A.size(); i++) {

            if (A.get(i) == state) {
                ans++;
                // As we will switch this, all the bulbs on right side will also change. So, change state = 1 - state
                state = 1 - state;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        LightBulbs lb = new LightBulbs();
        System.out.println(lb.bulbs(new int[]{1,1,1,1}));
    }
}
