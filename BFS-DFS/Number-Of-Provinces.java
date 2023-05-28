/*
547. Number of Provinces

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.

Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 
Constraints:
1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
*/

class Solution {
    public int findCircleNum(int[][] isConnected) {
        
        int V = isConnected.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < isConnected.length; i++){
            for(int j = 0; j < isConnected[0].length; j++){
                if(i != j && isConnected[i][j] == 1){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        boolean vis[] = new boolean[V];
        int count = 0;
        for(int i = 0; i < V; i++){
            if(!vis[i]){
                count++;
                
                //Method1 : DFS
                dfs(adj, i, vis);
                
                //Method2 : BFS
                /* 
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                vis[i] = true;
                while(!q.isEmpty()){
                    int node = q.remove();

                    for(int it : adj.get(node)){
                        if(!vis[it]){
                            vis[it] = true;
                            q.add(it);
                        }
                    }
                }
                */
            }
        }
        return count;
    }

    public void dfs(ArrayList<ArrayList<Integer>> adj, int node, boolean vis[]){

        vis[node] = true;
        for(int it : adj.get(node)){
            if(!vis[it]){
                dfs(adj, it, vis);
            }
        }
    }
}

/*
TC: O(N) + O(N+2E) [N for outer loop, N+2E for dfs]
SC: O(2N) ~ O(N) [For Stack space and Visited array]
*/