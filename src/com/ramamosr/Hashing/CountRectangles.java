package com.ramamosr.Hashing;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class CountRectangles {
    /*
Problem Constraints
1 <= N <= 2000
0 <= A[i], B[i] <= 109
Input Format
The first argument given is the integer array A.
The second argument given is the integer array B.
Output Format
Return the number of unordered quadruplet that form a rectangle.
Example Input
Input 1:
 A = [1, 1, 2, 2]
 B = [1, 2, 1, 2]
Input 1:
 A = [1, 1, 2, 2, 3, 3]
 B = [1, 2, 1, 2, 1, 2]
Example Output
Output 1:
 1
Output 2:
 3
Example Explanation
Explanation 1:
 All four given points make a rectangle. So, the answer is 1.
Explanation 2:
 3 quadruplets which make a rectangle are: (1, 1), (2, 1), (2, 2), (1, 2)
                                           (1, 1), (3, 1), (3, 2), (1, 2)
                                           (2, 1), (3, 1), (3, 2), (2, 2)
     */

    /*
    We have fixed the one diagonal of the rectangle and two corner points, from this we can easily find the other two points of the rectangle.
Suppose we have two diagonally opposite points: (x1, y1) and (x2, y2). Then the other two points of the rectangle must be (x1, y2) and (x2, y1).
We just have to check if these two points (x1, y2) and (x2, y1) are given or not. If present increment the answer.
For every rectangle, we have incremented the answer twice because every rectangle has two diagonals. So, our real answer will be half of the answer obtained after running two loops.
     */

    public int solve(int[] A, int[] B) {
        int result= 0;
        int n = A.length;
        HashSet<ArrayList<Integer>> hs = new HashSet<>();
        for(int i=0; i<n;i++){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(A[i]);
            temp.add(B[i]);
            hs.add(temp);
        }

        for(int i =0; i<n;i++){
            for(int j =i+1; j<n;j++){
                int x1 = A[i], x3 = A[j];
                int y1 = B[i], y3 = B[j];
                int x2 = x1, x4 = x3;
                int y2 = y3, y4 = y1;
                ArrayList<Integer> s = new ArrayList<>();
                s.add(x2);
                s.add(y2);

                if(hs.contains(s) && (x1!=x2 || y1!=y2) && (x3!=x2 || y3!=y2)){
                    s.clear();
                    s.add(x4);
                    s.add(y4);

                    if(hs.contains(s) && (x1!=x4 || (y1!=y4)) && (x3!=x4 || y3!=y4)){
                        result++;
                    }
                }
            }
        }
        return result/2;
    }

    public int solveScaler(int[] a, int[] b) {
        int n = a.length;
        HashMap<Integer, HashSet<Integer>> mpx = new HashMap<>();
        HashSet<Integer> h;
        for (int i = 0; i < n; i++) {
            if (mpx.containsKey(a[i]))
                h = mpx.get(a[i]);
            else
                h = new HashSet<>();
            h.add(b[i]);
            mpx.put(a[i], h);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] == a[j] || b[i] == b[j])
                    continue;
                if (mpx.get(a[i]).contains(b[j]) && mpx.get(a[j]).contains(b[i]))
                    ans++;
            }
        }
        return ans >> 1;
    }

    public static void main(String[] args) {

        int[] A = {1, 1, 2, 2, 3, 3};
        int[] B = {1, 2, 1, 2, 1, 2};

        CountRectangles cr = new CountRectangles();
        System.out.println(cr.solve(A,B));
    }
}
