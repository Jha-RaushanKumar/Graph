/*
https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
Shortest path in Undirected Graph having unit distance

You are given an Undirected Graph having unit weight, Find the shortest path from src to all the vertex and if it is unreachable to reach any vertex, then return -1 for that vertex.

Example:
Input:
n = 9, m= 10
edges=[[0,1],[0,3],[3,4],[4 ,5],[5, 6],[1,2],[2,6],[6,7],[7,8],[6,8]] 
src=0
Output:0 1 2 1 2 3 3 4 4

Your Task:
You don't need to print or input anything. Complete the function shortest path() which takes a 2d vector or array edges representing the edges of undirected graph with unit weight, an integer N as number nodes, an integer M as number of edges and an integer src as the input parameters and returns an integer array or vector, denoting the vector of distance from src to all nodes.

Constraint:
1<=n,m<=100
1<=adj[i][j]<=100
Expected Time Complexity: O(N + E), where N is the number of nodes and E is edges
Expected Space Complexity: O(N)
*/

class Solution {
    
    public int[] shortestPath(int[][] edges,int n,int m ,int src) {
        // Code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        
        int dist[] = new int[n];
        Arrays.fill(dist, (int)1e9);
        dist[src] = 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        
        while(!q.isEmpty()){
            int node = q.peek();
            q.remove();
            
            for(int adjNode : adj.get(node)){
                if(dist[node] + 1 < dist[adjNode]){
                    dist[adjNode] = dist[node] + 1;
                    q.add(adjNode);
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            if(dist[i] == (int)1e9){
                dist[i]= -1;
            }
        }
        return dist;
        
    }
    
}

/*
TC: O(N + V) 
SC: O(N) [For queue]
*/