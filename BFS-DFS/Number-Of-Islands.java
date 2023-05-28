/*
200. Number of Islands

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

class Solution {
    public int numIslands(char[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;

        boolean vis[][] = new boolean[m][n];

        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1' && !vis[i][j]){
                    count++;
                    
                    //Method1 : DFS
                    dfs(grid, i, j, vis);

                    //Method2 : BFS
                    /* 
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    vis[i][j] = true;
                    while(!q.isEmpty()){
                        int r = q.peek().row;
                        int c = q.peek().col;
                        q.remove();

                        int dr[] = {-1,0,1,0};
                        int dc[] = {0,1,0,-1};

                        for(int k = 0; k < 4; k++){
                            int nrow = r + dr[k];
                            int ncol = c + dc[k];

                            if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && grid[nrow][ncol] == '1' && !vis[nrow][ncol]){
                                vis[nrow][ncol] = true;
                                q.add(new Pair(nrow,ncol));
                            }
                        }
                    }
                    */
                }
            }
        }
        return count;   
    }

    public void dfs(char[][] grid, int r, int c, boolean vis[][]){

        int m = grid.length;
        int n = grid[0].length;

        vis[r][c] = true;
        int dr[] = {-1,0,1,0};
        int dc[] = {0,1,0,-1};

        for(int k = 0; k < 4; k++){
            int nrow = r + dr[k];
            int ncol = c + dc[k];

            if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && grid[nrow][ncol] == '1' && !vis[nrow][ncol]){
                dfs(grid, nrow, ncol, vis);
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
TC: O(N*M) + O(N+2E) ~ O(N*M) [N*M for outer loop, N+2E for dfs]
SC: O(N*M) [For Stack space and Visited array]
*/