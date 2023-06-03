/*
https://practice.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1

Distance from the Source (Bellman-Ford Algorithm)

Given a weighted, directed and connected graph of V vertices and E edges, Find the shortest distance of all the vertex's from the source vertex S.
Note: If the Graph contains a negative cycle then return an array consisting of only -1.

Example 1:
Input:
E = [[0,1,9]]
S = 0
Output:
0 9
Explanation: Shortest distance of all nodes from source is printed.

Example 2:
Input:
E = [[0,1,5],[1,0,3],[1,2,-1],[2,0,1]]
S = 2
Output:
1 6 0
Explanation:nFor nodes 2 to 0, we can follow the path-2-0. This has a distance of 1. For nodes 2 to 1, we cam follow the path-2-0-1, which has a distance of 1+5 = 6,
 
Your Task:
You don't need to read input or print anything. Your task is to complete the function bellman_ford( ) which takes a number of vertices V and an E-sized list of lists of three integers where the three integers are u,v, and w; denoting there's an edge from u to v, which has a weight of w and source node S as input parameters and returns a list of integers where the ith integer denotes the distance of an ith node from the source node.
If some node isn't possible to visit, then its distance should be 100000000(1e8). Also, If the Graph contains a negative cycle then return an array consisting of only -1.

Expected Time Complexity: O(V*E).
Expected Auxiliary Space: O(V).

Constraints:
1 ≤ V ≤ 500
1 ≤ E ≤ V*(V-1)
-1000 ≤ adj[i][j] ≤ 1000
0 ≤ S < V
*/

class Solution {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        // Write your code here
        
        int dist[] = new int[V];
        Arrays.fill(dist, (int)1e8);
        dist[S] = 0;
        
        //Relax all edges V-1 times
        for(int i = 0; i < V - 1; i++){
            for(ArrayList<Integer> edge : edges){
                int u = edge.get(0);
                int v = edge.get(1);
                int w = edge.get(2);
                
                if(dist[u] != (int)1e8 && dist[u] + w < dist[v]){
                    dist[v] = dist[u] + w;
                }
            }
        }
        
        //To check negative cycle (it shouldn't relax again if no neg cycle)
        for(ArrayList<Integer> edge : edges){
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);
            
            if(dist[u] != (int)1e8 && dist[u] + w < dist[v]){
                int arr[] = {-1};
                return arr;
            }
        }
        return dist;
    }
}

/*
TC: O(V*E) 
SC: O(N) [For dist]
*/