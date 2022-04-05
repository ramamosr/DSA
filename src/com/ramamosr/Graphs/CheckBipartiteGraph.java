package com.ramamosr.Graphs;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;


public class CheckBipartiteGraph {
    /*
    Problem Description
Given a undirected graph having A nodes. A matrix B of size M x 2 is given which represents the edges such that there is an edge between B[i][0] and B[i][1].

Find whether the given graph is bipartite or not.

A graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B

Note:

There are no self-loops in the graph.

No multiple edges between two pair of vertices.

The graph may or may not be connected.

Nodes are Numbered from 0 to A-1.

Your solution will run on multiple testcases. If you are using global variables make sure to clear them.



Problem Constraints
1 <= A <= 100000

1 <= M <= min(A*(A-1)/2,200000)

0 <= B[i][0],B[i][1] < A



Input Format
The first argument given is an integer A.

The second argument given is the matrix B.



Output Format
Return 1 if the given graph is bipartide else return 0.



Example Input
Input 1:

A = 2
B = [ [0, 1] ]
Input 2:

A = 3
B = [ [0, 1], [0, 2], [1, 2] ]


Example Output
Output 1:

1
Output 2:

0


Example Explanation
Explanation 1:

Put 0 and 1 into 2 different subsets.
Explanation 2:


It is impossible to break the graph down to make two different subsets for bipartite matching
     */
    /*
    You can use and approach either BFS or DFS to check whether the graph can be colored using two colors or not.

Start from any node that hase not been colored yet:
a. Assign color1 to this nodes
b. check its adjacent nodes
a. if this is colored in color1 then the graph can’t be bipartite ,return 0.
b. else if this is colored in color1 do nothing.
c. else color it with color 2 and push it into queue.
Repet step1 until no nodes is left for coloring.
     */

    public int solve(int A, int[][] B) {
        int nodes = A;
        int[] color = new int[nodes];

        Arrays.fill(color,-1);
        ArrayList<ArrayList<Integer>> graph = buildGraph(B, nodes);

        // since the question says, there could be multiple connected components in the graph,
        // do a for loop for all the nodes.
        for(int i=0; i<nodes;i++){
            // check the color of the current node
            if(color[i]==-1){
                color[i]=1;
                if(!checkBipartite(i,graph,color))
                    return 0;
            }
        }
        return 1;
    }

    public boolean checkBipartite(int source, ArrayList<ArrayList<Integer>> graph, int[] color){

        ArrayList<Integer> neighbors = graph.get(source);
        for (int i = 0; i < neighbors.size(); i++) {
            int currNode = neighbors.get(i);
            if (color[currNode] == -1) {
                // if the color has not been given, give opposite color of the source to the current Node
                color[currNode] = color[source] ^ 1;
                if (!checkBipartite(currNode, graph, color)) {
                    return false;
                }
            }
            if (color[currNode] == color[source]) {
                // if the source color and the neighbor color are the same, its not a valid bipartite graph
                return false;
            }
        }
        return true;

    }

    public ArrayList<ArrayList<Integer>> buildGraph(int[][] edges, int nodes){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0; i<nodes;i++)
            list.add(new ArrayList<Integer>());

        // Its undirected graph. so add the edges to both nodes.
        for(int i=0;i<edges.length;i++){
            int u = edges[i][0];
            int v = edges[i][1];

            list.get(u).add(v);
            list.get(v).add(u);
        }
        return list;
    }

    static int maxn = 100009;
    static ArrayList < ArrayList < Integer > > graph;
    public static void graphC() {
        graph = new ArrayList < ArrayList < Integer > > (maxn);
        for (int i = 0; i < maxn; i++) {
            graph.add(new ArrayList < Integer > ());
        }
    }
    public int solveScaler(int A, int[][] B) {
        graphC();
        for (int[] edge: B) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        if (isBipar(A) == true)
            return 1;
        return 0;
    }
    public static boolean isBipar(int n) {
        if (n == 0)
            return true;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        Queue< Integer > q = new ArrayDeque< >();
        for (int i = 0; i < n; ++i) {
            if (color[i] != -1)
                continue;
            color[i] = 1;
            q.offer(i);
            while (q.size() > 0) {
                int x = q.poll();
                for (int it: graph.get(x)) {
                    if (color[it] == -1) {
                        color[it] = color[x] ^ 1;
                        q.offer(it);
                    } else if (color[it] == color[x])
                        return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int A = 3;
        int[][] B = { {0, 1}, {0, 2}, {1, 2}};
        CheckBipartiteGraph cpg = new CheckBipartiteGraph();
        System.out.println(cpg.solve(A,B));
    }
}


