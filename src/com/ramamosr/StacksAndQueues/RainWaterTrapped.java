package com.ramamosr.StacksAndQueues;

public class RainWaterTrapped {
    /*
Rain Water Trapped
Problem Description

Given a vector A of non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.



Problem Constraints
1 <= |A| <= 100000



Input Format
First and only argument is the vector A



Output Format
Return one integer, the answer to the question



Example Input
Input 1:

A = [0, 1, 0, 2]
Input 2:

A = [1, 2]


Example Output
Output 1:

1
Output 2:

0


Example Explanation
Explanation 1:

1 unit is trapped on top of the 3rd element.
Explanation 2:

No water is trapped.
     */
    public int trap(final int[] A) {
        int waterCount = 0;
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        int maxLeft = 0, maxRight = 0;
        for(int i=0; i< A.length;i++){
            if(maxLeft < A[i])
                maxLeft = A[i];
            left[i] = maxLeft;
        }
        for(int j=A.length-1; j>=0;j--){
            if(maxRight < A[j])
                maxRight = A[j];
            right[j] = maxRight;
        }
        for(int k=0; k<A.length;k++){
            waterCount+= Math.min(left[k],right[k]) - A[k];
        }
        return waterCount;
    }
    /*
    instead of calculating area by height*width, we can think it in a cumulative way.
In other words, sum water amount of each bin(width=1). Search from left to right and maintain a max height of
left and right separately, which is like a one-side wall of partial container. Fix the higher one and flow water
from the lower part. For example, if current height of left is lower, we fill water in the left bin. Until left meets right,
we filled the whole container.
     */

    public int trapScaler(final int[] A) {

        int n=A.length;
        int left = 0; int right = n - 1;
        int res = 0;
        int maxleft = 0, maxright = 0;

        while(left <= right){

            // When height of left side is lower, calculate height of water trapped in left bin else calculate for right bin
            if(A[left] <= A[right]){
                if(A[left] >= maxleft) maxleft = A[left];
                else res += maxleft-A[left];
                left++;
            }
            else{
                if(A[right] >= maxright) maxright = A[right];
                else res += maxright - A[right];
                right--;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        RainWaterTrapped rwt = new RainWaterTrapped();
        System.out.println(rwt.trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
