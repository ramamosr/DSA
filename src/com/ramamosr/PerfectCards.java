package com.ramamosr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PerfectCards {
    /*
    Perfect Cards
Problem Description

Tom and Harry are given N numbers, with which they play a game as a team.

Initially, Tom chooses a particular number P from the N numbers, and he takes away all the numbers that are equal to P.

Next, Harry chooses a different number Q, different from what Tom chose, and takes away all the numbers equal to Q from the remaining N numbers.

They win the game if they can take all the numbers by doing the above operation once and if each of them has the same amount of numbers towards the end.

If they win, return the string "WIN", else return "LOSE".



Problem Constraints
2 <= N <= 100

1 <= A[i] <= 100



Input Format
The first and the only argument of input contains an integer array, A.



Output Format
Return a string, denoting the answer.



Example Input
Input 1:

 A = [1, 2]
Input 2:

 A = [1, 1, 2, 2, 3]


Example Output
Output 1:

 "WIN"
Output 2:

 "LOSE"


Example Explanation
Explanation 1:

 In the his turn, Tom can take 1 away, and then Harry take take 2 away. The array is empty and both of them have equal number of cards, so we can say that they have won the game.
Explanation 2:

 No matter how they take away the elements, >= 1 card will always remain, hence, they lose.
     */
    public String solve(int[] A) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0; i<A.length;i++){
            if(hm.containsKey(A[i])){
                hm.put(A[i],hm.get(A[i]) +1);
            }
            else {
                hm.put(A[i], 1);
            }
        }
        if(hm.size()!=2)
            return "LOSE";
        else{
            int oldValue = 0;
            for (Map.Entry me : hm.entrySet()){
                Integer key = (Integer)me.getKey();
                int value = (int) me.getValue();
                if(oldValue==0) oldValue = value;
                else if (oldValue > 0 && oldValue != value)
                    return "LOSE";
                else if(oldValue > 0 && oldValue==value)
                    return "WIN";
            }
        }
            return "WIN";
    }

    public static void main(String[] args) {
        PerfectCards pc = new PerfectCards();
        System.out.println(pc.solve(new int[] {1,1,2,2,}));
    }
}
