package com.ramamosr.Graphs;

import java.util.ArrayList;

public class CycleInUnDirectedGraph {

    /*
    Problem Description

Given an undirected graph having A nodes labelled from 1 to A with M edges given in a form of matrix B of size M x 2 where (B[i][0], B[i][1]) represents two nodes B[i][0] and B[i][1] connected by an edge.

Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.

NOTE:

The cycle must contain atleast three nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.


Problem Constraints

1 <= A, M <= 3*105

1 <= B[i][0], B[i][1] <= A



Input Format

The first argument given is an integer A representing the number of nodes in the graph.

The second argument given is an matrix B of size M x 2 which represents the M edges such that there is a edge between node B[i][0] and node B[i][1].



Output Format

Return 1 if cycle is present else return 0.



Example Input

Input 1:

 A = 5
 B = [  [1. 2]
        [1, 3]
        [2, 3]
        [1, 4]
        [4, 5]
     ]
Input 2:

 A = 3
 B = [  [1. 2]
        [1, 3]
     ]
     */
    /*
    Method 1: DFS
Like directed graphs, we can use DFS to detect cycle in an undirected graph in O(A+M) time.
We do a DFS traversal of the given graph. For every visited vertex ‘v’, if there is an adjacent ‘u’ such that u is already visited and u is not parent of v, then there is a cycle in graph.
If we don’t find such an adjacent for any vertex, we say that there is no cycle.
The assumption of this approach is that there are no parallel edges between any two vertices


Method 2: Union-Find
We can keep track of the subsets in a 1D array, let’s call it parent[].
For each edge, make subsets using both the vertices of the edge. If both the vertices are in the same subset, a cycle is found.
Initially, all slots of parent array are initialized to -1 (means there is only one item in every subset).
Time Complexity: O(MlogA)
     */
    /*
    Approach: Run a DFS from every unvisited node. Depth First Traversal can be used to detect a cycle in a Graph. DFS for a connected graph produces a tree. There is a cycle in a graph only if there is a back edge present in the graph. A back edge is an edge that is joining a node to itself (self-loop) or one of its ancestor in the tree produced by DFS. 
To find the back edge to any of its ancestors keep a visited array and if there is a back edge to any visited node then there is a loop and return true.
Algorithm: 

Create the graph using the given number of edges and vertices.
Create a recursive function that have current index or vertex, visited array and parent node.
Mark the current node as visited .
Find all the vertices which are not visited and are adjacent to the current node. Recursively call the function for those vertices, If the recursive function returns true return true.
If the adjacent node is not parent and already visited then return true.
Create a wrapper class, that calls the recursive function for all the vertices and if any function returns true, return true.
Else if for all vertices the function returns false return false.
     */
    public int solve(int A, int[][] B) {
        ArrayList<ArrayList<Integer>> graph = buildGraph(B,A);
        boolean[] visited = new boolean[A+1];
        for(int i =1; i <=A;i++){
            if(!visited[i]){
                if(checkCycle(graph,visited,i,-1))
                    return 1;
            }
        }
        return 0;
    }

    public ArrayList<ArrayList<Integer>> buildGraph(int[][] edges, int nodes){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0; i<=nodes;i++)
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

    public boolean checkCycle(ArrayList<ArrayList<Integer>> graph,boolean[] visited, int node, int parent){

        visited[node] = true;
        for(int vert: graph.get(node)){
            if(!visited[vert]){
                if(checkCycle(graph,visited,vert,node))
                    return true;
            }
            else if(vert!=parent)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int A = 5;
        int[][] B = {{1,2},{1, 3},{2, 3},{1, 4},{4, 5}};

        CycleInUnDirectedGraph cig = new CycleInUnDirectedGraph();
        System.out.println(cig.solve(A,B));
    }
}
