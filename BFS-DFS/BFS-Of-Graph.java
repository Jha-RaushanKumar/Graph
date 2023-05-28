/*
https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1

Given a directed graph. The task is to do Breadth First Traversal of this graph starting from 0.
Note: One can move from node u to node v only if there's an edge from u to v and find the BFS traversal of the graph starting from the 0th vertex, from left to right according to the graph. Also, you should only take nodes directly or indirectly connected from Node 0 in consideration.

Example 1:
Input: check diagram here: https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
Output: 0 1 2 3 4
Explanation: 
0 is connected to 1 , 2 , 3.
2 is connected to 4.
so starting from 0, it will go to 1 then 2
then 3.After this 2 to 4, thus bfs will be
0 1 2 3 4.

Example 2:
Input: check diagram here: https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
Output: 0 1 2
Explanation:
0 is connected to 1 , 2.
so starting from 0, it will go to 1 then 2,
thus bfs will be 0 1 2. 

Your task:
You dont need to read input or print anything. Your task is to complete the function bfsOfGraph() which takes the integer V denoting the number of vertices and adjacency list as input parameters and returns  a list containing the BFS traversal of the graph starting from the 0th vertex from left to right.

Expected Time Complexity: O(V + E)
Expected Auxiliary Space: O(V)

Constraints:
1 ≤ V, E ≤ 104
*/

class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here

        ArrayList<Integer> ls = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[V];
        q.add(0);
        vis[0] = true;
        
        while(!q.isEmpty()){
            int node = q.remove();
            vis[node] = true;
            ls.add(node);

            for(int it : adj.get(node)){
                if(!vis[it]){
                    vis[it] = true;
                    q.add(it);
                }
            }
        }
        return ls;
    }
}

/*
TC: O(N) + O(2E) [N is total nodes, 2E is for total degrees as we traverse all adjacent nodes]
SC: O(3N) ~ O(N) [For Queue, ArrayList and Visited array]
*/