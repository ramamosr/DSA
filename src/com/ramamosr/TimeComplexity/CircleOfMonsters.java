package com.ramamosr.TimeComplexity;

public class CircleOfMonsters {
    /*
    Circle of Monsters
Problem Description

You are playing another computer game, and now you have to slay n monsters. These monsters are standing in a circle, numbered clockwise from 1 to n. Initially, the i-th monster has ai health.

You may shoot the monsters to kill them. Each shot requires exactly one bullet and decreases the health of the targeted monster by 1 (deals 1 damage to it). Furthermore, when the health of some monster i becomes 0 or less than 0, it dies and explodes, dealing bi damage to the next monster (monster i+1, if i < n, or monster 1, if i=n). If the next monster is already dead, then nothing happens. If the explosion kills the next monster, it explodes too, damaging the monster after it and possibly triggering another explosion, and so on.

You have to calculate the minimum number of bullets mod 10 9 + 7 you have to fire to kill all n monsters in the circle.

NOTE: If the minimum no. of bullets are x then you have to return x % 1e9 + 7 .



Problem Constraints
2<= n <=300000
1<= A[i], B[i]<= 1e9


Input Format
First Argument is array of integers of N size denoting array A Second Argument is array of integers of N size denoting array B


Output Format
Return the miniumum number of bullets mod 10 9 + 7 you have to fire to kill all of the monsters.


Example Input
Input- 1
A=[7,2,5]
B=[15,14,3]
Input- 2

A=[1 2]
B=[2 1]


Example Output
Output- 1
6
Output- 2

1


Example Explanation
Explanation-1
Firstly we shoot 2nd monster whose health is 2 with 2 bullets and then this monster will create damage of 14 to another monster.
Now 3rd monster health will decrease by 14 units . hence 3rd monster also died . hence it will create damage of 3 units to 1st monster
Now 1st monster health is 7-3=4 . and now 4 more bullets are required to kill 1st monster.
Total bullets required to kill all the monsters = 2+4= 6
Explanation-2

Kill 1st monster with 1 bullet so it will cause 2 units of damage to 2nd monster.
Hence 2nd monster automatically died.
Total bullets required=1
     */
    public int solve(int[] A, int[] B) {
        long totalBullets = 0;
        int mod = 1000*1000*1000+7;
        // for the first monster in A or the starting monster, there are no prior damage element from B.
        // So the number of bullets taken will be A[0]-A[0]-B[n-1]. Add that to the total bullets.
        totalBullets = Math.max(0,A[0]-B[B.length-1]);
        for(int i=1;i<A.length;i++){
            // if the damage is more than the required bullets,
            // then bullet count goes as negative.So it has to be zero
            totalBullets += Math.max(0,A[i]-(B[i-1]));
        }
        long minBullets = Long.MAX_VALUE;
        // After calculating the total bullets, now choose each of the monsters as the starting element
        // and calculate the bullets required for shooting all the monsters.
        // to do that, add the current bullets required and deduct the value added to the sum
        // earlier. which is the total bullets-damage caused by the prior monster.

        // for 0th  monster there is no prior. But the current bullet value was added to the sum.
        // this follows this. A[0] + totalBullets - (A[0]-B[0-1];
        // so A[0] + totalBullets - A[0] -B[B.length-1]/
        minBullets = Math.min(minBullets,(A[0] + (totalBullets -Math.max(0,A[0]-B[B.length-1]))));
        //minBullets = Math.min(minBullets,totalBullets);

        for(int i=1; i<A.length;i++){
            minBullets = Math.min(minBullets,(A[i] + totalBullets - Math.max(0,(A[i]-B[i-1]))));
        }
        return (int)(minBullets%mod);
    }

    public static void main(String[] args) {
        CircleOfMonsters cm = new CircleOfMonsters();
        int[] A = new int[]{7,2,5};
        int[] B = new int[]{15,14,3};
        System.out.println(cm.solve(A,B));
    }
}
