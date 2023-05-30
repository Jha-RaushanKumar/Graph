/*
1020. Number of Enclaves

You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

Example 1:
Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.

Example 2:
Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 0 or 1.
*/

class Solution {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean vis[][] = new boolean[m][n];

        //Method1 : DFS
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || i == m - 1 || j == 0 || j == n - 1){
                    if(grid[i][j] == 1){
                        dfs(i, j, grid, vis);
                    }
                }
            }
        }

        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1 && vis[i][j] == false){
                    count++;
                }
            }
        }
        
        //Method2 : BFS
        /*
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || i == m - 1 || j == 0 || j == n - 1){
                    if(grid[i][j] == 1){
                        q.add(new Pair(i, j));
                        vis[i][j] = true;
                    }
                }
            }
        }

        while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;

            q.remove();

            int dr[] = {-1,0,1,0};
            int dc[] = {0,1,0,-1};

            for(int i = 0; i < 4; i++){
                int nrow = r + dr[i];
                int ncol = c + dc[i];

                if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && !vis[nrow][ncol] && grid[nrow][ncol] == 1){
                    q.add(new Pair(nrow, ncol));
                    vis[nrow][ncol] = true;
                }
            }
        }
        
        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1 && vis[i][j] == false){
                    count++;
                }
            }
        }
        */

        return count;
        
    }

    public void dfs(int r, int c, int[][] grid, boolean[][] vis){
        
        int m = grid.length;
        int n = grid[0].length;

        vis[r][c] = true;
        int dr[] = {-1,0,1,0};
        int dc[] = {0,1,0,-1};

        for(int i = 0; i < 4; i++){
            int nrow = r + dr[i];
            int ncol = c + dc[i];

            if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && !vis[nrow][ncol] && grid[nrow][ncol] == 1){
                dfs(nrow, ncol, grid, vis);
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
SC: O(N*M) [For Stack space and Visited array]
*/