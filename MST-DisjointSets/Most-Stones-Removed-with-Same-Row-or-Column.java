/*
947. Most Stones Removed with Same Row or Column
https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

Example 1:
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.

Example 2:
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.

Example 3:
Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.

Constraints:
1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
*/

class Solution {
    public int removeStones(int[][] stones) {
        
        int maxRow = 0;
        int maxCol = 0;
        for(int i = 0; i < stones.length; i++){
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }

        HashMap<Integer, Integer> hm = new HashMap<>();
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);
        for(int i = 0; i < stones.length; i++){
            int nrow = stones[i][0];
            int ncol = stones[i][1] + maxRow + 1;

            ds.unionByRank(nrow, ncol);
            hm.put(nrow, 1);
            hm.put(ncol, 1);
        }

        int cnt = 0;
        for(Map.Entry<Integer, Integer> entry : hm.entrySet()){
            if(ds.findUParent(entry.getKey()) == entry.getKey()){
                cnt++;
            }
        }
        return stones.length - cnt;
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