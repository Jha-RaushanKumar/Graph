/*
Detect cycle in an undirected graph
https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1

Given an undirected graph with V vertices and E edges, check whether it contains any cycle or not. Graph is in the form of adjacency list where adj[i] contains all the nodes ith node is having edge with.

Example 1:
Input: check diagram here: https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
V = 5, E = 5
adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}} 
Output: 1
Explanation: 
1->2->3->4->1 is a cycle.

Example 2:
Input: check diagram here: https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
V = 4, E = 2
adj = {{}, {2}, {1, 3}, {2}}
Output: 0
Explanation: 
No cycle in the graph.
 
Your Task:
You don't need to read or print anything. Your task is to complete the function isCycle() which takes V denoting the number of vertices and adjacency list as input parameters and returns a boolean value denoting if the undirected graph contains any cycle or not, return 1 if a cycle is present else return 0.
NOTE: The adjacency list denotes the edges of the graph where edges[i] stores all other vertices to which ith vertex is connected.

Expected Time Complexity: O(V + E)
Expected Space Complexity: O(V)

Constraints:
1 ≤ V, E ≤ 105
*/

class Solution {
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        boolean vis[] = new boolean[V];
        for(int i = 0; i < V; i++){
            if(!vis[i]){
                
                //Method1 : DFS
                if(dfs(i, -1, adj, vis)){
                    return true;
                }
                
                //Method2 : BFS
                /*
                Queue<Pair> q = new LinkedList<>();
                q.add(new Pair(i, -1));
                vis[i] = true;
                
                while(!q.isEmpty()){
                    int node = q.peek().node;
                    int parent = q.peek().parent;
                    q.remove();
                    
                    for(int it : adj.get(node)){
                        if(!vis[it]){
                            q.add(new Pair(it, node));
                            vis[it] = true;
                        }
                        else if(it != parent){
                            return true;
                        }
                    }
                }
                */
            }
        }
        return false;
    }
    
    public boolean dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean vis[]) {
        
        vis[node] = true;
        
        for(int it : adj.get(node)){
            if(!vis[it]){
                if(dfs(it, node, adj, vis)){
                    return true;
                }
            }
            else if(it != parent){
                return true;
            }
        }
        return false;
    }
    
    class Pair{
        int node;
        int parent;

        public Pair(int node, int parent){
            this.node = node;
            this.parent = parent;
        }
    }
}

/*
TC: O(N) + O(N+2E) [N for outer loop, N+2E for dfs]
SC: O(2N) ~ O(N) [For Stack space and Visited array]
*/