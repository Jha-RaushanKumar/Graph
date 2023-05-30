/*
542. 01 Matrix
https://leetcode.com/problems/01-matrix/

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 
Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
*/

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        
        int m = mat.length;
        int n = mat[0].length;
        boolean vis[][] = new boolean[m][n];

        //Method : BFS
        Queue<Tuple> q = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 0){
                    vis[i][j] = true;
                    q.add(new Tuple(i, j, 0));
                }
            }
        }

        while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;
            int s = q.peek().steps;
            q.remove();
            mat[r][c] = s;

            int dr[] = {-1,0,1,0};
            int dc[] = {0,1,0,-1};

            for(int i = 0; i < 4; i++){
                int nrow = r + dr[i];
                int ncol = c + dc[i];

                if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && !vis[nrow][ncol]){
                    q.add(new Tuple(nrow, ncol, s + 1));
                    vis[nrow][ncol] = true;
                }
            }
        }
        return mat;

    }

    class Tuple{
        int row;
        int col;
        int steps;

        public Tuple(int row, int col, int steps){
            this.row = row;
            this.col = col;
            this.steps = steps;
        }
    }
}

/*
TC: O(N*M*4) ~ O(N*M) [N*M for dfs done 4 directionally]
SC: O(N*M) [For Stack space and Visited array]
*/