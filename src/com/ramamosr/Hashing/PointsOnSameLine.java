package com.ramamosr.Hashing;

import java.util.*;

public class PointsOnSameLine {
    /*
    Problem Description

Given two array of integers A and B describing a pair of (A[i], B[i]) coordinates in 2D plane. A[i] describe x coordinates of the ith point in 2D plane whereas B[i] describes the y-coordinate of the ith point in 2D plane.

Find and return the maximum number of points which lie on the same line.



Problem Constraints

1 <= (length of the array A = length of array B) <= 1000

-105 <= A[i], B[i] <= 105



Input Format

First argument is an integer array A.
Second argument is an integer array B.



Output Format

Return the maximum number of points which lie on the same line.



Example Input

Input 1:

 A = [-1, 0, 1, 2, 3, 3]
 B = [1, 0, 1, 2, 3, 4]
Input 2:

 A = [3, 1, 4, 5, 7, -9, -8, 6]
 B = [4, -8, -3, -2, -1, 5, 7, -4]


Example Output

Output 1:

 4
Output 2:

 2


Example Explanation

Explanation 1:

 The maximum number of point which lie on same line are 4, those points are {0, 0}, {1, 1}, {2, 2}, {3, 3}.
Explanation 2:

 Any 2 points lie on a same line.
     */

    /*
    If two point are (x1, y1) and (x2, y2) then their slope will be (y2 – y1) / (x2 – x1) which can be a double value and can cause precision problems.

To get rid of the precision problems, we treat slope as pair ((y2 – y1), (x2 – x1)) instead of ratio and reduce pair by their gcd before inserting into map.

Points which are vertical or repeated are treated separately.

Note: If we use map in c++ or HashMap in Java for storing the slope pair, then total time complexity of solution will be O(n^2 logn)
     */

    public int solve(ArrayList A, ArrayList B) {
        int curmax=0,overlap=0,vertical=0,ans = 0;

        int n = A.size();
        HashMap<Dictionary<Integer, Integer>, Integer> mp = new HashMap<>();

        for(int i=0;i<n; ++i){

            curmax=0;
            overlap=0;
            vertical=0;

            for(int j=i+1; j<n; ++j)

            {
                if(A.get(i)==A.get(j)&&B.get(i)==B.get(j))++overlap;
                else if(A.get(i)==A.get(j))++vertical;
                else
                {
                    int xdiff=(int)A.get(j)-(int)A.get(i);
                    int ydiff=(int)B.get(j)-(int)B.get(i);
                    int z=gcd(xdiff,ydiff);
                    xdiff/=z;
                    ydiff/=z;
                    Dictionary<Integer,Integer> p = new Hashtable<>();
                    p.put(xdiff,ydiff);
                    if(mp.containsKey(p))
                        mp.put(p,mp.get(p)+1);
                    else
                        mp.put(p,1);
                    curmax=Math.max(curmax,mp.get(p));

                }

                curmax = Math.max(curmax,vertical);
            }
            mp.clear();
            ans=Math.max(ans,curmax+overlap+1);

        }

        return ans;
    }


    public int gcd(int A, int B) {
        if (A == 0)
            return B;
        return gcd(B % A, A);
    }
    public static void main(String[] args) {
        PointsOnSameLine pos = new PointsOnSameLine();
        ArrayList<Integer> A = new ArrayList<>();
        A.add(-1);
        A.add(0);
        A.add(1);
        A.add(2);
        A.add(3);
        A.add(3);

        ArrayList<Integer> B = new ArrayList<>();
        B.add(1);
        B.add(0);
        B.add(1);
        B.add(2);
        B.add(3);
        B.add(4);

        System.out.println(pos.solve(A,B));
    }
}
