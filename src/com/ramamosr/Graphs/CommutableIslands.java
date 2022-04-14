package com.ramamosr.Graphs;

import java.util.*;

public class CommutableIslands {

    /*
    Problem Description

There are A islands and there are M bridges connecting them. Each bridge has some cost attached to it.

We need to find bridges with minimal cost such that all islands are connected.

It is guaranteed that input data will contain at least one possible scenario in which all islands are connected with each other.



Problem Constraints

1 <= A, M <= 6*104

1 <= B[i][0], B[i][1] <= A

1 <= B[i][2] <= 103



Input Format

The first argument contains an integer, A, representing the number of islands.

The second argument contains an 2-d integer matrix, B, of size M x 3 where Island B[i][0] and B[i][1] are connected using a bridge of cost B[i][2].



Output Format

Return an integer representing the minimal cost required.



Example Input

Input 1:

 A = 4
 B = [  [1, 2, 1]
        [2, 3, 4]
        [1, 4, 3]
        [4, 3, 2]
        [1, 3, 10]  ]
Input 2:

 A = 4
 B = [  [1, 2, 1]
        [2, 3, 2]
        [3, 4, 4]
        [1, 4, 3]   ]


Example Output

Output 1:

 6
Output 2:

 6


Example Explanation

Explanation 1:

 We can choose bridges (1, 2, 1), (1, 4, 3) and (4, 3, 2), where the total cost incurred will be (1 + 3 + 2) = 6.
Explanation 2:

 We can choose bridges (1, 2, 1), (2, 3, 2) and (1, 4, 3), where the total cost incurred will be (1 + 2 + 3) = 6.
     */

    /*
    We can assume islands as the vertex points and bridges as the edges and can construct a graph with the the help of them. After constructing the graph, the problem boils down to finding a subset of edges which helps in connecting vertices in a single connected component such that the sum of their edge weights is as minimum as possible.

Now since the problem is clear to you, can you think of any graph theory algorithms related to this?

Well the answer to this problem is the classic minimum spanning tree algorithm. In this algorithm we aim at finding subset of the edges of a connected, edge-weighted undirected graph that connects all the vertices together, without any cycles and with the minimum possible total edge weight.

There are many algorithms for finding minimum spanning tree of a graph. Some of them are Kruskal’s algorithm, Prim’s algorithm etc.

Kruskal’s algorithm in detail can be found at : https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
Prim’s algorithm in detail can be found at : https://en.wikipedia.org/wiki/Prim%27s_algorithm

Now, can you code this?
     */

    class Pair {
        int weight;
        int u;
        int v;
        public Pair(int _u, int _v, int _w) {
            this.u = _u;
            this.v = _v;
            this.weight = _w;
        }
    }

    class WeightComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.weight - o2.weight;
        }
    }

    public int solve(int A, int[][] B) {

        int totalCost = 0;
        // build adjacency list
        List<List<Pair>> edges = buildAdjList(A, B);
        boolean[] visited = new boolean[A + 1];
        // initialize minHeap
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new WeightComparator());

        // insert node 1 (with cost 0) in queue
        queue.add(new Pair(1, 1, 0));

        while (!queue.isEmpty()) {
            Pair x = queue.poll();
            int x_target = x.v;
            if (!visited[x_target]) {

                // mark current target node as visited and add weight to final cost
                visited[x_target] = true;
                totalCost += x.weight;

                // traverse all neighbors of target node
                for (Pair e : edges.get(x_target)) {
                    int source = e.u;
                    int target = e.v;
                    if (visited[source] && visited[target]) {
                        continue;
                    } else if (!visited[source] || !visited[target]) {
                        // if one th node of current neighbor is unvisited, add edge into queue
                        queue.add(e);
                    }
                }
            }
        }
        return totalCost;
    }

    private List<List<Pair>> buildAdjList(int A, int[][] B) {

        List<List<Pair>> edges = new ArrayList<List<Pair>>();
        for (int i = 0; i <= A; i++) {
            edges.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < B.length; i++) {
            int u = B[i][0];
            int v = B[i][1];
            int w = B[i][2];
            edges.get(u).add(new Pair(u, v, w));
            edges.get(v).add(new Pair(v, u, w));
        }
        return edges;
    }

    public static void main(String[] args) {
        int A = 4;
        int[][] B = {{1, 2, 1},{2, 3, 4},{1, 4, 3},{4, 3, 2},{1, 3, 10}};

        CommutableIslands ci = new CommutableIslands();
        System.out.println(ci.solve(A,B));
    }
}
// Scaler solution
public class Solution {
    static int maxn = 60009;
    static int[] arr = new int[maxn];
    static int[] sz = new int[maxn];
    static ArrayList < pair > edges;
    public int solve(int A, int[][] B) {
        ini();
        for (int[] row: B) {
            edges.add(new pair(row[2], row[0], row[1]));
        }
        Collections.sort(edges);
        return kruskal();
    }
    public static int kruskal() {
        int cost = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (root(edges.get(i).b) == root(edges.get(i).c))
                continue;
            cost += edges.get(i).a;
            un(edges.get(i).b, edges.get(i).c);
        }
        return cost;
    }
    public static void ini() {
        edges = new ArrayList < pair > ();
        for (int i = 0; i < maxn; i++) {
            arr[i] = i;
            sz[i] = 1;
        }
    }
    public static int root(int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]];
            a = arr[a];
        }
        return a;
    }
    public static void un(int a, int b) {
        int ra = root(a);
        int rb = root(b);
        if (sz[ra] <= sz[rb]) {
            arr[ra] = rb;
            sz[rb] += sz[ra];
        } else {
            arr[rb] = ra;
            sz[ra] += sz[rb];
        }
    }
}
class pair implements Comparable < pair > {
    int a, b, c;
    pair(int e, int f, int g) {
        this.a = e;
        this.b = f;
        this.c = g;
    }
    @Override
    public int compareTo(pair aa) {
        return this.a - aa.a;
    }
}
