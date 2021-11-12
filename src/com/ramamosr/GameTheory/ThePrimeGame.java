package com.ramamosr.GameTheory;


public class ThePrimeGame {

    /*
    The Prime Game
Problem Description

Two players are playing a game. The rules are as follows:

Player 1 always moves first, and both players always play optimally.
Initially there are A piles, where each pile has B number of stones.
The players move in alternating turns. In each turn, a player can choose a pile of size x and reduce the number of stones to y, where 1 <= y < x, and x and y are coprime.
If the current player is unable to make a move, they lose the game.
Determine the winner of the game.


Problem Constraints
1 ≤ A, B ≤ 106



Input Format
The first argument is A, the number of piles. The second argument is B, the number of stones in each pile initially.



Output Format
Return a single integer, 1 or 2, depending upon which player wins.



Example Input
Input 1:

 A = 2
 B = 2
Input 2:

 A = 3
 B = 1


Example Output
Output 1:

 2
Output 2:

 2


Example Explanation
Explanation 1:

 The only possible scenario is player 1 chooses any one pile and reduce it to 1. Then Player 2 reduces the other pile to 1 and wins.
Explanation 2:

 There is no possible move for player 1. Hence, player 2 wins.
     */
    /*
    Approach
    Special Case: When B = 1, there is no possible move, therefore player 2 wins.

When A is even:

Imagine that the piles are separated into two groups having an equal number of A/2 towers in each group.
There is a 1 to 1 relationship between these two groups, whenever player 1 mutates one of the piles from the first group, player 2 can simply copy player 1's last move and apply it to a pile from the second group.
In this way, player 2 will always have move to make (i.e., the mirror of player 1's previous move), so player 2 will always win.
When A is odd:

Player 1 can choose one pile and reduce it to 1 (always coprime).
This results in A-1 piles each having B stones.
Now, Since A-1 is even and it is player 2's turn, Player 1 can follow the same strategy as above and win.
     */
    public int solve(int A, int B) {

        if(B==1 || A%2==0 )
            return 2;
        else
            return 1;
    }

    public static void main(String[] args) {
        ThePrimeGame gt = new ThePrimeGame();
        System.out.println(gt.solve(2,2));
    }
}
