package com.ramamosr.StacksAndQueues;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class NIntegers {

    /*
        ArrayList<Integer> ans = new ArrayList<Integer>();

    for (int i = 1; i <= 3; i++)
      ans.add(i);

    // calculate further results
    for (int i = 0; i <= A / 3; i++)
      for (int j = 0; j <3; j++)
        if ((ans.get(i) * 10) != 0)
          ans.add(ans.get(i) * 10 + ans.get(j));
    return ans;
     */

    public ArrayList<Integer> solve(int A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();

        for (int i = 1; i <= 3; i++)
            ans.add(i);

        // calculate further results
        for (int i = 0; i < A / 3; i++)
            for (int j = 0; j <3; j++)
                if(ans.size()<A){
                    if ((ans.get(i) * 10) != 0)
                        ans.add(ans.get(i) * 10 + ans.get(j));
                }
        return ans;
    }

    public ArrayList < Integer > solveScaler(int A) {
        Queue< Integer > q = new LinkedList< Integer >();
        ArrayList < Integer > ans = new ArrayList < > ();
        q.add(1);
        q.add(2);
        q.add(3);
        int cnt=3;
        while (ans.size() < A) {
            int x = q.peek();
            ans.add(x);
            q.remove();
            if(cnt>=A)continue;
            q.add(10 * x + 1);
            q.add(10 * x + 2);
            q.add(10 * x + 3);
            cnt+=3;
        }
        return ans;
    }

    public static void main(String[] args) {
        NIntegers nint = new NIntegers();
        nint.solve(586);
    }
}
