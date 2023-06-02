/*
https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1
Shortest path in Directed Acyclic Graph

Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer array(or vector) edges[ ][ ] of length M, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i, 0<=i
Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.

Example:
Input:
n = 6, m= 7
edge=[[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
Output:
0 2 3 6 1 5
 
Your Task:
You don't need to print or input anything. Complete the function shortest path() which takes an integer N as number of vertices, an integer M as number of edges and a 2D Integer array(or vector) edges as the input parameters and returns an integer array(or vector), denoting the list of distance from src to all nodes.

Constraint:
1 <= n,m <= 100
0 <= edgei,0,edgei,1 < n
Expected Time Complexity: O(N+E), where N is the number of nodes and E is edges
Expected Space Complexity: O(N)
*/

class Solution {

	public int[] shortestPath(int N,int M, int[][] edges) {
		//Code here
		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i < N; i++){
            adj.add(new ArrayList<Pair>());
        }
        for(int i = 0; i < M; i++){
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
        }
        
        Stack<Integer> st = new Stack<>();
        boolean vis[] = new boolean[N];
        for(int i = 0; i < N; i++){
            if(!vis[i]){
                topoSort(i, st, vis, adj);
            }
        }
        
        int dist[] = new int[N];
        Arrays.fill(dist, (int)1e9);
        dist[0] = 0;
        while(!st.isEmpty()){
            int node = st.pop();
            
            for(Pair it : adj.get(node)){
                int adjNode = it.first;
                int ew = it.second;
                
                if(dist[node] + ew < dist[adjNode]){
                    dist[adjNode] = dist[node] + ew;
                }
            }
            
        }
        for(int i = 0; i < N; i++){
            if(dist[i] == (int)1e9){
                dist[i]= -1;
            }
        }
        return dist;
		
	}
	
	public void topoSort(int node, Stack<Integer> st, boolean vis[], ArrayList<ArrayList<Pair>> adj){
	    
	    vis[node] = true;
	    
	    for(Pair it : adj.get(node)){
	        if(!vis[it.first]){
	            topoSort(it.first, st, vis, adj);
	        }
	    }
	    st.push(node);
	}
	
	class Pair{
        int first;
        int second;

        public Pair(int _first, int _second){
            this.first = _first;
            this.second = _second;
        }
    }
}

/*
TC: O(N + V) 
SC: O(N) [For Stack]
*/