package com.ramamosr.Graphs;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeQue;

public class PathInDirectedGraph {

    /*
    Problem Description

Given an directed graph having A nodes labelled from 1 to A containing M edges given by matrix B of size M x 2such that there is a edge directed from node

B[i][0] to node B[i][1].

Find whether a path exists from node 1 to node A.

Return 1 if path exists else return 0.

NOTE:

There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.


Problem Constraints

2 <= A <= 105

1 <= M <= min(200000,A*(A-1))

1 <= B[i][0], B[i][1] <= A



Input Format

The first argument given is an integer A representing the number of nodes in the graph.

The second argument given a matrix B of size M x 2 which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].



Output Format

Return 1 if path exists between node 1 to node A else return 0.



Example Input

Input 1:

 A = 5
 B = [  [1, 2]
        [4, 1]
        [2, 4]
        [3, 4]
        [5, 2]
        [1, 3] ]
Input 2:

 A = 5
 B = [  [1, 2]
        [2, 3]
        [3, 4]
        [4, 5] ]


Example Output

Output 1:

 0
Output 2:

 1


Example Explanation

Explanation 1:

 The given doens't contain any path from node 1 to node 5 so we will return 0.
Explanation 2:

 Path from node1 to node 5 is ( 1 -> 2 -> 3 -> 4 -> 5 ) so we will return 1.
     */

    /*
    Approach:
Either Breadth First Search (BFS) or Depth First Search (DFS) can be used to find path between two vertices.
Take the first vertex as source in BFS (or DFS), follow the standard BFS (or DFS). If the second vertex is found in our traversal, then return 1 else return 0.

Algorithm:
The author implementation iss using BFS.

Create a queue and a visited array initially filled with 0, of size V where V is number of vertices.
Insert the starting node in the queue, i.e. push u in the queue and mark u as visited.
Run a loop until the queue is not empty.
Dequeue the front element of the queue. Iterate all its adjacent elements. If any of the adjacent element is the destination return 1. Push all the adjacent and unvisted vertices in the queue and mark them as visited.
Return 0 as the destination is not reached in BFS.
Complexity Analysis:

Time Complexity: O(A + M) where A is number of vertices in the graph and M is number of edges in the graph.
Space Compelxity: O(A).
There can be atmost A elements in the queue. So the space needed is O(A).

Trade-offs between BFS and DFS: Breadth-First search can be useful to find the shortest path between nodes, and depth-first search may traverse one adjacent node very deeply before ever going into immediate neighbours.
     */

    static int maxn = 100009;
    static int[] visited = new int[maxn];
    static ArrayList <ArrayList< Integer >> adj;
    public int solveScaler(int A, int[][] B) {
        adj = new ArrayList < ArrayList < Integer > > (maxn);
        for (int i = 0; i < maxn; i++) {
            visited[i] = 0;
            adj.add(new ArrayList < Integer > ());
        }
        for (int[] edge: B)
            adj.get(edge[0]).add(edge[1]);
        if (isReachable(1, A) == true)
            return 1;
        return 0;
    }
    public static boolean isReachable(int s, int d) {
        if (s == d)
            return true;
        Queue< Integer > q = new ArrayDeque< >();
        q.offer(s);
        visited[s] = 1;
        while (q.size() > 0) {
            s = q.poll();
            for (int v: adj.get(s)) {
                if (v == d) return true;
                if (visited[v] == 0) {
                    visited[v] = 1;
                    q.offer(v);
                }
            }
        }
        return false;
    }
    public int solve(int A, int[][] B) {
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>(A+1);
        for(int i=0;i<=A;i++) {
            adjList.add(new ArrayList<>());
        }
        for(int i=0;i<B.length;i++) {
            adjList.get(B[i][0]).add(B[i][1]);
        }
        int visited[]=new int[A+1];
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<adjList.get(1).size();i++) {
            st.add(adjList.get(1).get(i));
        }
        visited[1]=1;
        int isFound=0;
        while(!st.isEmpty()) {
            int n=st.pop();
            if(n==A) {
                isFound=1;
                break;
            }
            if(visited[n]!=1) {
                for(int i=0;i<adjList.get(n).size();i++) {
                    st.add(adjList.get(n).get(i));
                }
                visited[n]=1;
            }

        }
        return isFound;
    }

    public static void main(String[] args) {
        PathInDirectedGraph pidg = new PathInDirectedGraph();
        int A = 5;
        int[][] B = {  {1, 2},{2, 3},{3, 4},{4, 5}};
        System.out.println(pidg.solve(A,B));
    }
}
