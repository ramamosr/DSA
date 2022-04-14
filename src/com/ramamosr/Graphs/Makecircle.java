package com.ramamosr.Graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class Makecircle {
    /*
    Problem Description

Given an array of strings A of size N, find if the given strings can be chained to form a circle.

A string X can be put before another string Y in circle if the last character of X is same as first character of Y.

NOTE: All strings consist of lower case characters.



Problem Constraints

1 <= N <= 105

Sum of length of all strings <= 106



Input Format

First and only argument is a string array A of size N.



Output Format

Return an integer 1 if it is possible to chain the strings to form a circle else return 0.



Example Input

Input 1:

 A = ["aab", "bac", "aaa", "cda"]
Input 2:

 A = ["abc", "cbc"]


Example Output

Output 1:

 1
Output 2:

 0


Example Explanation

Explanation 1:

 We can chain the strings aab -> bac -> cda -> aaa -> aab. So this forms a circle. So, output will be 1.
Explanation 2:

 There is no way to chain the given strings such that they forms a circle.
     */

    /*
    It can be clearly seen after graph representation that if a loop among graph vertices is possible then we can reorder the strings otherwise not.

Now to check whether this graph can have a loop which goes through all the vertices, we’ll check two conditions,
1) Indegree and Outdegree of each vertex should be same.
2) Graph should be strongly connected.

First condition can be checked easily by keeping two arrays, in and out for each character.
For checking whether graph is having a loop which goes through all vertices is same as checking complete directed graph is strongly connected or not because if it has a loop which goes through all vertices then we can reach to any vertex from any other vertex that is, graph will be strongly connected and same argument can be given for reverse statement also.

Now for checking second condition we will just run a DFS from any character and visit all reachable vertices from this, now if graph has a loop then after this one DFS all vertices should be visited, if all vertices are visited then we will return true otherwise false so visiting all vertices in a single DFS flags a possible ordering among strings.
     */
    public int solve(String[] A) {
        int m = 26;
        boolean[] mark = new boolean[m];
        int[] in = new int[m];
        int[] out = new int[m];

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<m; i++)
        {
            adj.add(new ArrayList<Integer>());
        }

        for(int i=0; i<A.length; i++)
        {
            int f = (int)(A[i].charAt(0) - 'a');
            int l = (int)(A[i].charAt(A[i].length() - 1) -'a');

            mark[l] = mark[f] = true;

            in[l]++;
            out[f]++;

            adj.get(f).add(l);
        }

        for(int i=0; i<m; i++)
        {
            if(in[i] != out[i])
                return 0;
        }
        return isConnected(adj, mark, A[0].charAt(0)-'a');
    }

    public int isConnected(ArrayList<ArrayList<Integer>> adj, boolean[] mark, int src)
    {
        int[] vis = new int[26];
        dfs(adj, vis, src);

        for(int i=0; i<26; i++)
        {
            if(mark[i] && vis[i] == 0)
                return 0;
        }
        return 1;
    }

    public void dfs(ArrayList<ArrayList<Integer>> adj, int[] vis, int src)
    {
        vis[src] = 1;

        for(int it : adj.get(src))
        {
            if(vis[it] == 0)
                dfs(adj, vis, it);
        }
    }

    public static void main(String[] args) {
        Makecircle mc = new Makecircle();
        String[] A = {"aab", "bac", "aaa", "cda"};
        System.out.println(mc.solve(A));
    }
}

public class Solution {
    static int maxn = 26;
    static ArrayList < ArrayList < Integer > > g;
    public static void graph() {
        g = new ArrayList < ArrayList < Integer > > (maxn);
        for (int i = 0; i < maxn; i++) {
            g.add(new ArrayList < Integer > ());
        }
    }
    public int solve(String[] A) {
        graph();
        int n = A.length;
        int[] mark = new int[26];
        int[] in = new int[26];
        int[] out = new int[26];
        for (int i = 0; i < n; i++) {
            int f = A[i].charAt(0) - 'a';
            int l = A[i].charAt(A[i].length() - 1) - 'a';
            mark[l] = 1;
            mark[f] = 1; in [l]++;
            out[f]++;
            g.get(f).add(l);
        }
        for (int i = 0; i < 26; i++) {
            if ( in [i] != out[i])
                return 0;
        }
        if (isConnected(mark, A[0].charAt(0) - 'a') == true)
            return 1;
        return 0;
    }
    public static boolean isConnected(int[] mark, int s) {
        int[] visit = new int[26];
        Arrays.fill(visit, 0);
        dfs(s, visit);
        for (int i = 0; i < 26; i++) {
            if (mark[i] == 1 && visit[i] == 0)
                return false;
        }
        return true;
    }
    public static void dfs(int u, int[] visit) {
        visit[u] = 1;
        for (int i = 0; i < g.get(u).size(); i++) {
            if (visit[g.get(u).get(i)] == 0)
                dfs(g.get(u).get(i), visit);
        }
    }
}
