/*
1319. Number of Operations to Make Network Connected
https://leetcode.com/problems/number-of-operations-to-make-network-connected/

There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.
Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

Example 1:
Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

Example 2:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2

Example 3:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
 
Constraints:
1 <= n <= 105
1 <= connections.length <= min(n * (n - 1) / 2, 105)
connections[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated connections. No two computers are connected by more than one cable.
*/

class Solution {
    public int makeConnected(int n, int[][] connections) {
        
        int cntExtreEdge = 0;
        DisjointSet ds = new DisjointSet(n);
        for(int i = 0; i < connections.length; i++){
            int u = connections[i][0];
            int v = connections[i][1];
            
            if(ds.findUParent(u) != ds.findUParent(v)){
                ds.unionByRank(u, v);
            }
            else{
                cntExtreEdge++;
            }
        }

        int components = 0;
        for(int i = 0; i < n; i++){
            if(ds.findUParent(i) == i){
                components++;
            }
        }
        
        if(cntExtreEdge >= components - 1){
            return components - 1;
        }
        else{
            return -1;
        }
    }
}

class DisjointSet{
    ArrayList<Integer> parent;
    ArrayList<Integer> rank;
    ArrayList<Integer> size;
    
    public DisjointSet(int n){
        parent = new ArrayList<>();
        rank = new ArrayList<>();
        size = new ArrayList<>();
        
        for(int i = 0; i <= n; i++){
            parent.add(i);
            rank.add(0);
            size.add(1);
        }
    }
    
    public int findUParent(int node){
        if(node == parent.get(node)){
            return node;
        }
        int uParent = findUParent(parent.get(node));
        parent.set(node, uParent);
        return parent.get(node);
    }
    
    public void unionByRank(int u, int v){
        int uParent_u = findUParent(u);
        int uParent_v = findUParent(v);
        if(uParent_u == uParent_v){
            return;
        }
        if(rank.get(uParent_u) > rank.get(uParent_v)){
            parent.set(uParent_v, uParent_u);
        }
        else if(rank.get(uParent_u) < rank.get(uParent_v)){
            parent.set(uParent_u, uParent_v);
        }
        else{
            parent.set(uParent_v, uParent_u);
            int rankU = rank.get(uParent_u);
            rank.set(uParent_u, rankU + 1);
        }
    }
    
    public void unionBySize(int u, int v){
        int uParent_u = findUParent(u);
        int uParent_v = findUParent(v);
        if(uParent_u == uParent_v){
            return;
        }
        if(size.get(uParent_u) >= size.get(uParent_v)){
            parent.set(uParent_v, uParent_u);
            int sizeU = size.get(uParent_u);
            int sizeV = size.get(uParent_v);
            size.set(uParent_u, sizeU + sizeV);
        }
        else{
            parent.set(uParent_u, uParent_v);
            int sizeU = size.get(uParent_u);
            int sizeV = size.get(uParent_v);
            size.set(uParent_v, sizeU + sizeV);
        }
    }
}

/*
TC: O(N+E) + O(E logE) 
SC: O(N)
*/

