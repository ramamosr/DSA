package com.ramamosr.Graphs;

import java.util.*;

public class Dijsktra {
    /*
    Problem Description

Given a weighted undirected graph having A nodes and M weighted edges, and a source node C.

You have to find an integer array D of size A such that:

=> D[i] : Shortest distance form the C node to node i.

=> If node i is not reachable from C then -1.

Note:

There are no self-loops in the graph.

No multiple edges between two pair of vertices.

The graph may or may not be connected.

Nodes are numbered from 0 to A-1.

Your solution will run on multiple testcases. If you are using global variables make sure to clear them.



Problem Constraints

1 <= A <= 1e5

0 <= B[i][0],B[i][1] < A

0 <= B[i][2] <= 1e3

0 <= C < A



Input Format

The first argument given is an integer A, representing the number of nodes.

The second argument given is the matrix B of size M x 3, where nodes B[i][0] and B[i][1] are connected with an edge of weight B[i][2].

The third argument given is an integer C.



Output Format

Return the integer array D.



Example Input

Input 1:

A = 6
B = [   [0, 4, 9]
        [3, 4, 6]
        [1, 2, 1]
        [2, 5, 1]
        [2, 4, 5]
        [0, 3, 7]
        [0, 1, 1]
        [4, 5, 7]
        [0, 5, 1] ]
C = 4
Input 2:

A = 5
B = [   [0, 3, 4]
        [2, 3, 3]
        [0, 1, 9]
        [3, 4, 10]
        [1, 3, 8]  ]
C = 4


Example Output

Output 1:

D = [7, 6, 5, 6, 0, 6]
Output 2:

D = [14, 18, 13, 10, 0]


Example Explanation

Explanation 1:

 All Paths can be considered from the node C to get shortest path
Explanation 2:

 All Paths can be considered from the node C to get shortest path
     */

    /*
    Initialize a distance array of integers(denoting distance of source to node i) with infinity.
Initialize d[source]=0 (distance from source to source is 0).
Insert {d[source],source} into a min heap based on distance.
extract first element from the heap:
if this element is mark visited then again start extract top element fro heap
else mark this as vis and expore adjacent nodes of the top node of the heap:
if distance[adjacentnode]>distance[curr]+weight of the edge between curr and adjacent node
update distacne[adjacentnode] = distance[curr]+weight of the edge between curr and adjacent node
insert this node alongwith weight into minheap.
     */

    public int[] solve(int A, int[][] B, int C) {
        HashMap<Integer, List<PairW>> map = new HashMap<>();

        for (int i = 0; i < A; i++)
            map.put(i, new ArrayList<PairW>());
        // Put mapping for un-directed graph in the HashMap
        for (int i = 0; i < B.length; i++) {
            int n = B[i][0];
            int e = B[i][1];
            int w = B[i][2];
            map.get(n).add(new PairW(n, e, w));
            map.get(e).add(new PairW(e, n, w));
        }
        // output array
        int[] d = new int[A];
        //Fills all -1 in d
        Arrays.fill(d, -1);
        // Add 0 to our source node
        d[C] = 0;
        //Min Heap with Pair Comparator
        PriorityQueue<PairW> pq = new PriorityQueue<>((p1, p2) -> {
            return p1.weight - p2.weight;
        });
        // Adds All the nodes source as C to min Heap
        for (PairW p1 : map.get(C))
            pq.add(p1);
        // Run while loop till out queue is not empty
        while (!pq.isEmpty()) {
            PairW pair = pq.poll();
            // Checks that edge is not already traversed with minimum distance
            if (d[pair.edge] != -1)
                continue;
            // Add the shortest distance possible for the edge.
            d[pair.edge] = pair.weight;
            // Start traversing through the edges coming out of current edge
            for (PairW p1 : map.get(pair.edge)) {
                //if the edge is not traversed then adds it to min heap with weight+previous node weight
                if (d[p1.edge] == -1)
                    pq.add(new PairW(p1.node, p1.edge, p1.weight + d[pair.edge]));
            }
        }
        return d;
    }
    static int maxn = 100009;
    static int[] vis = new int[maxn];
    static ArrayList < ArrayList < Pair > > adj;
    public static void graph() {
        adj = new ArrayList < ArrayList < Pair > > (maxn);
        for (int i = 0; i < maxn; i++) {
            vis[i] = 0;
            adj.add(new ArrayList < Pair > ());
        }
    }
    public int[] solveScaler(int A, int[][] B, int C) {
        graph();
        for (int[] edge: B) {
            adj.get(edge[0]).add(new Pair(edge[2], edge[1]));
            adj.get(edge[1]).add(new Pair(edge[2], edge[0]));
        }
        return dijkstra(A, C);
    }

    public static int[] dijkstra(int n, int source) {
        PriorityQueue < Pair > pq = new PriorityQueue < Pair > (new CustomComp());
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;
        pq.offer(new Pair(0, source));

        while (pq.size() != 0) {
            Pair temp = pq.poll();
            int u = temp.b;
            if (vis[u] == 1)
                continue;
            vis[u] = 1;
            for (int i = 0; i < adj.get(u).size(); i++) {
                int v = adj.get(u).get(i).b, w = adj.get(u).get(i).a;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new Pair(dist[v], v));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        }
        return dist;
    }
}
class Pair {
    int a, b;
    public Pair(int u, int v) {
        this.a = u;
        this.b = v;
    }
}
class CustomComp implements Comparator < Pair > {
    @Override
    public int compare(Pair aa, Pair bb) {
        return aa.a - bb.a;
    }
}

class PairW {
    int node, edge, weight;
    PairW(int node, int edge, int weight) {
        this.node =node;
        this.edge= edge;
        this.weight= weight;
    }
}
