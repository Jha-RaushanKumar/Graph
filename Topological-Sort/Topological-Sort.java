/*
Topological sort
https://practice.geeksforgeeks.org/problems/topological-sort/1

Given a Directed Acyclic Graph (DAG) with V vertices and E edges, Find any Topological Sorting of that Graph.

Example 1:
Input: check diagram here: https://practice.geeksforgeeks.org/problems/topological-sort/1
Output:
1
Explanation:
The output 1 denotes that the order is valid. So, if you have, implemented your function correctly, then output would be 1 for all test cases.
One possible Topological order for the graph is 3, 2, 1, 0.

Example 2:
Input: check diagram here: https://practice.geeksforgeeks.org/problems/topological-sort/1
Output:
1
Explanation:
The output 1 denotes that the order is valid. So, if you have, implemented your function correctly, then output would be 1 for all test cases.
One possible Topological order for the graph is 5, 4, 2, 1, 3, 0.

Your Task:
You don't need to read input or print anything. Your task is to complete the function topoSort()  which takes the integer V denoting the number of vertices and adjacency list as input parameters and returns an array consisting of the vertices in Topological order. As there are multiple Topological orders possible, you may return any of them. If your returned topo sort is correct then the console output will be 1 else 0.

Expected Time Complexity: O(V + E).
Expected Auxiliary Space: O(V).

Constraints:
2 ≤ V ≤ 104
1 ≤ E ≤ (N*(N-1))/2
*/

class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        // add your code here
        boolean vis[] = new boolean[V];
        
        //Method1 : DFS
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < V; i++){
            if(!vis[i]){
                dfs(i, adj, st, vis);
            }
        }
        
        int[] topo = new int[V];
        int i = 0;
        while(!st.isEmpty()){
            topo[i++] = st.pop();
        }
        
        //Method2 : BFS (Kahn's Algorithm)
        /*
        int indegree[] = new int[V];
        for(int i = 0; i < V; i++){
            for(int it : adj.get(i)){
                indegree[it]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < V; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        
        int[] topo = new int[V];
        int i = 0;
        
        while(!q.isEmpty()){
            int node = q.remove();
            topo[i++] = node;
            for(int it : adj.get(node)){
                indegree[it]--;
                if(indegree[it] == 0){
                    q.add(it);
                }
            }
        }
        */
        return topo;
    }
    
    public static void dfs(int node, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st, boolean vis[]){
        
        vis[node] = true;
        
        for(int it : adj.get(node)){
            if(!vis[it]){
                dfs(it, adj, st, vis);
            }
        }
        st.push(node);
    }
}

/*
TC: O(N) + O(2E) [N is total nodes(V), 2E is for total degrees as we traverse all adjacent nodes]
SC: O(N) [For Stack space and indegree array]
*/