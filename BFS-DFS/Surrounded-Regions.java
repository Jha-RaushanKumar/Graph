/*
130. Surrounded Regions
https://leetcode.com/problems/surrounded-regions/

Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example 1:
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.

Example 2:
Input: board = [["X"]]
Output: [["X"]]
 
Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
*/

class Solution {
    public void solve(char[][] board) {
        
        int m = board.length;
        int n = board[0].length;
        boolean vis[][] = new boolean[m][n];

        //Method1 : DFS
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || i == m - 1 || j == 0 || j == n - 1){
                    if(board[i][j] == 'O'){
                        dfs(i, j, board, vis);
                    }
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O' && vis[i][j] == false){
                    board[i][j] = 'X';
                }
            }
        }
        
        //Method2 : BFS
        /*
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || i == m - 1 || j == 0 || j == n - 1){
                    if(board[i][j] == 'O'){
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

                if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && !vis[nrow][ncol] && board[nrow][ncol] == 'O'){
                    q.add(new Pair(nrow, ncol));
                    vis[nrow][ncol] = true;
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O' && vis[i][j] == false){
                    board[i][j] = 'X';
                }
            }
        }
        */
    }

    public void dfs(int r, int c, char[][] board, boolean[][] vis){
        
        int m = board.length;
        int n = board[0].length;

        vis[r][c] = true;
        int dr[] = {-1,0,1,0};
        int dc[] = {0,1,0,-1};

        for(int i = 0; i < 4; i++){
            int nrow = r + dr[i];
            int ncol = c + dc[i];

            if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && !vis[nrow][ncol] && board[nrow][ncol] == 'O'){
                dfs(nrow, ncol, board, vis);
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