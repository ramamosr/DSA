
package com.ramamosr.Graphs;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstructRoads {

    /*
    Problem Description
A country consist of N cities connected by N - 1 roads. King of that country want to construct maximum number of roads such that the new country formed remains bipartite country.

Bipartite country is a country, whose cities can be partitioned into 2 sets in such a way, that for each road (u, v) that belongs to the country, u and v belong to different sets. Also, there should be no multiple roads between two cities and no self loops.

Return the maximum number of roads king can construct. Since the answer could be large return answer % 109 + 7.

NOTE: All cities can be visited from any city.



Problem Constraints
1 <= A <= 105

1 <= B[i][0], B[i][1] <= N



Input Format
First argument is an integer A denoting the number of cities, N.

Second argument is a 2D array B of size (N-1) x 2 denoting the initial roads i.e. there is a road between cities B[i][0] and B[1][1] .



Output Format
Return an integer denoting the maximum number of roads king can construct.



Example Input
Input 1:

 A = 3
 B = [
       [1, 2]
       [1, 3]
     ]
Input 2:

 A = 5
 B = [
       [1, 3]
       [1, 4]
       [3, 2]
       [3, 5]
     ]


Example Output
Output 1:

 0
Output 2:

 2


Example Explanation
Explanation 1:

 We can't construct any new roads such that the country remains bipartite.
Explanation 2:

 We can add two roads between cities (4, 2) and (4, 5).
     */
    /*
    As we know, the tree is itself bipartite.

Run a Depth First search over the given tree and partition it into two sets.

We can’t add an edge between any 2 nodes in the same set and we can add an edge between every 2 nodes in different sets, so let the number of nodes in the left set be x and the number of nodes in the right set be y.

The maximum number of edges that can exist is x * y, but N - 1 edges already exist so the maximum number of edges to be added is x * y - (N - 1).
     */

    public int solve(int A, int[][] B) {

        int[] color = new int[A+1];
        Arrays.fill(color,-1);
        ArrayList<ArrayList<Integer>> graph = buildGraph(B,A);

        color[1] = 1;
        buildRoads(1,graph,color);

        int left = 0, right = 0;

        for(int i = 0; i<color.length;i++){
            if(color[i]==0)
                left++;
            else if (color[i]==1)
                right++;
        }

        long mod = 1000000007;
        long totalRoads = (left%mod * right%mod)%mod;
        totalRoads = totalRoads - B.length;
        return (int)(totalRoads);
    }

    public void buildRoads(int source, ArrayList<ArrayList<Integer>> graph, int[] color){

        ArrayList<Integer> neighbors = graph.get(source);
        for (int i = 0; i < neighbors.size(); i++) {
            int currNode = neighbors.get(i);
            if (color[currNode] == -1) {
                // if the color has not been given, give opposite color of the source to the current Node
                color[currNode] = color[source] ^ 1;
                buildRoads(currNode, graph, color);
            }
        }
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

    public static void main(String[] args) {

      int  A = 5;
      int[][] B = {{1, 3},{1, 4},{3, 2},{3, 5}};

      ConstructRoads cr = new ConstructRoads();
        System.out.println(cr.solve(A,B));

    }
}
