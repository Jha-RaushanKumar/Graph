/*
Number of Distinct Islands
https://practice.geeksforgeeks.org/problems/number-of-distinct-islands/1

Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).

Example 1:
Input:
grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}}
Output:
1
Explanation:
grid[][] = {{1, 1, 0, 0, 0}, 
            {1, 1, 0, 0, 0}, 
            {0, 0, 0, 1, 1}, 
            {0, 0, 0, 1, 1}}
Same colored islands are equal. We have 2 equal islands, so we have only 1 distinct island.

Example 2:
Input:
grid[][] = {{1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1}}
Output:
3
Explanation:
grid[][] = {{1, 1, 0, 1, 1}, 
            {1, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 1}, 
            {1, 1, 0, 1, 1}}
Same colored islands are equal. We have 4 islands, but 2 of them are equal, So we have 3 distinct islands.

Your Task:
You don't need to read or print anything. Your task is to complete the function countDistinctIslands() which takes the grid as an input parameter and returns the total number of distinct islands.

Expected Time Complexity: O(n * m)
Expected Space Complexity: O(n * m)

Constraints:
1 ≤ n, m ≤ 500
grid[i][j] == 0 or grid[i][j] == 1
*/

class Solution {

    int countDistinctIslands(int[][] grid) {
        // Your Code here
        
        int m = grid.length;
        int n = grid[0].length;
        
        boolean vis[][] = new boolean[m][n];
        HashSet<ArrayList<String>> hs = new HashSet<>();
        
        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                
                ArrayList<String> ls = new ArrayList<>();
                if(grid[i][j] == 1 && !vis[i][j]){
                    
                    //Method1 : DFS
                    dfs(i, j, grid, vis, ls, i, j);
                    
                    //Method2 : BFS
                    /*
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    vis[i][j] = true;
                    while(!q.isEmpty()){
                        int r = q.peek().row;
                        int c = q.peek().col;
                        ls.add((r - i) + " " + (c - j));
                        q.remove();
                        int dr[] = {-1,0,1,0};
                        int dc[] = {0,1,0,-1};
                
                        for(int k = 0; k < 4; k++){
                            int nrow = r + dr[k];
                            int ncol = c + dc[k];
                
                            if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && !vis[nrow][ncol] && grid[nrow][ncol] == 1){
                                vis[nrow][ncol] = true;
                                q.add(new Pair(nrow, ncol));
                            }
                        }
                    }
                    */
                    
                    hs.add(ls);
                }
            }
        }
        return hs.size();
    }
    
    public void dfs(int r, int c, int[][] grid, boolean[][] vis, ArrayList<String> ls, int r0, int c0){
        
        int m = grid.length;
        int n = grid[0].length;

        vis[r][c] = true;
        ls.add((r - r0) + " " + (c - c0));
        
        int dr[] = {-1,0,1,0};
        int dc[] = {0,1,0,-1};

        for(int i = 0; i < 4; i++){
            int nrow = r + dr[i];
            int ncol = c + dc[i];

            if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && !vis[nrow][ncol] && grid[nrow][ncol] == 1){
                dfs(nrow, ncol, grid, vis, ls, r0, c0);
            }
        }
    }

    class Pair{
        int row;
        int col;

        public Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}

/*
TC: O(N*M*4) ~ O(N*M) [N*M for dfs done 4 directionally]
SC: O(N*M) [For Stack space, Visited array and HashSet]
*/