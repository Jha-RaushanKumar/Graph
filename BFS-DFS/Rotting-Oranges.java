/*
994. Rotting Oranges
https://leetcode.com/problems/rotting-oranges/

You are given an m x n grid where each cell can have one of three values:
0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
*/

class Solution {
    public int orangesRotting(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        boolean vis[][] = new boolean[m][n];
        Queue<Tuple> q = new LinkedList<>();
        int cntFresh = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    q.add(new Tuple(i, j, 0));
                    vis[i][j] = true;
                }
                if(grid[i][j] == 1){
                    cntFresh++;
                }
            }
        }

        //Method : BFS
        int tmax = 0;
        int cnt = 0;
        while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;
            int t = q.peek().time;

            tmax = Math.max(tmax, t);
            q.remove();
            int dr[] = {-1,0,1,0};
            int dc[] = {0,1,0,-1};

            for(int k = 0; k < 4; k++){
                int nrow = r + dr[k];
                int ncol = c + dc[k];

                if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && grid[nrow][ncol] == 1 && !vis[nrow][ncol]){
                    cnt++;
                    vis[nrow][ncol] = true;
                    q.add(new Tuple(nrow, ncol, t + 1));
                }
            }
        }
        if(cnt != cntFresh){
            return -1;
        }
        return tmax;
    }

    class Tuple{
        int row;
        int col;
        int time;

        public Tuple(int row, int col, int time){
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}

/*
TC: O(N*M*4) ~ O(N*M) [N*M for dfs done 4 directionally]
SC: O(N*M) [For Stack space and Visited array]
*/