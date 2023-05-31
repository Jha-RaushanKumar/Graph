/*
Detect cycle in a directed graph
https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1

Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.

Example 1:
Input: check diagram here: https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
Output: 1
Explanation: 3 -> 3 is a cycle

Example 2:
Input: check diagram here: https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
Output: 0
Explanation: no cycle in the graph

Your task:
You dont need to read input or print anything. Your task is to complete the function isCyclic() which takes the integer V denoting the number of vertices and adjacency list as input parameters and returns a boolean value denoting if the given directed graph contains a cycle or not.

Expected Time Complexity: O(V + E)
Expected Auxiliary Space: O(V)

Constraints:
1 ≤ V, E ≤ 105
*/

class Solution {
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        
        //Method1: DFS
        
        boolean vis[] = new boolean[V];
        boolean pathVis[] = new boolean[V];
        
        for(int i = 0; i < V; i++){
            if(!vis[i]){
                if(dfs(i, vis, pathVis, adj)){
                    return true;
                }
            }
        }
        return false;
        
        //Method2: BFS (Topo Sort)
        /*
        int indegree[] = new int[V];
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < V; i++){
            for(int it : adj.get(i)){
                indegree[it]++;
            }
        }
        for(int i = 0; i < V; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        
        int count = 0;
        while(!q.isEmpty()){
            int node = q.remove();
            count++;
            
            for(int it : adj.get(node)){
                indegree[it]--;
                if(indegree[it] == 0){
                    q.add(it);
                }
            }
        }
        if(count == V){
            return false;
        }
        return true;
        */
    }
    
    public boolean dfs(int node, boolean vis[], boolean pathVis[], ArrayList<ArrayList<Integer>> adj){
        
        vis[node] = true;
        pathVis[node] = true;
        
        for(int it : adj.get(node)){
            if(!vis[it]){
                if(dfs(it, vis, pathVis, adj)){
                    return true;
                }
            }
            else if(pathVis[it]){
                return true;
            }
        }
        pathVis[node] = false;
        return false;
    }
}

/*
TC: O(N) + O(2E) ~ O(V+E) [N is total nodes(V), 2E is for total degrees as we traverse all adjacent nodes]
SC: O(N) [For Stack space, path visited and visited array/ indegree array]
*/