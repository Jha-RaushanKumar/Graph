/*
802. Find Eventual Safe States
https://leetcode.com/problems/find-eventual-safe-states/

There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

Example 1:
Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

Example 2:
Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
 
Constraints:
n == graph.length
1 <= n <= 104
0 <= graph[i].length <= n
0 <= graph[i][j] <= n - 1
graph[i] is sorted in a strictly increasing order.
The graph may contain self-loops.
The number of edges in the graph will be in the range [1, 4 * 104].
*/

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        int m = graph.length;
        int n = graph[0].length;
        List<Integer> safeNodes = new ArrayList<>();

        //Method1: DFS
        /*
        boolean vis[] = new boolean[m];
        boolean pathVis[] = new boolean[m];
        boolean check[] = new boolean[m];

        for(int i = 0; i < m; i++){
            if(!vis[i]){
                dfs(i, graph, vis, pathVis, check);
            }
        }
        for(int i = 0; i < m; i++){
            if(check[i]){
                safeNodes.add(i);
            }
        }
        */

        //Method2: BFS(Topological)
        ArrayList<ArrayList<Integer>> adjR = new ArrayList<>();
        for(int i = 0; i < m; i++){
            adjR.add(new ArrayList<>());
        }
        int indegree[] = new int[m];

        for(int i = 0; i < m; i++){
            for(int it : graph[i]){
                adjR.get(it).add(i);
                indegree[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < m; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int node = q.remove();
            safeNodes.add(node);

            for(int it : adjR.get(node)){
                indegree[it]--;
                if(indegree[it] == 0){
                    q.add(it);
                }
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
    }

    public boolean dfs(int node, int[][] graph, boolean[] vis, boolean[] pathVis, boolean check[]){

        vis[node] = true;
        pathVis[node] = true;

        for(int it : graph[node]){
            if(!vis[it]){
                if(dfs(it, graph, vis, pathVis, check)){
                    return true;
                }
            }
            else if(pathVis[it]){
                return true;
            }
        }

        pathVis[node] = false;
        check[node] = true;
        return false;
    }
}

/*
TC: O(N) + O(2E) ~ O(V+E) [N is total nodes(V), 2E is for total degrees as we traverse all adjacent nodes]
SC: O(N) [For Queue and indegree array]
*/