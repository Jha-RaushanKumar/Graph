/*
1091. Shortest Path in Binary Matrix
https://leetcode.com/problems/shortest-path-in-binary-matrix/

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 1:
Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:
Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:
Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1){
            return -1;
        }

        int dist[][] = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0, 0, 1));
        dist[0][0] = 1;

        while(!q.isEmpty()){

            int r = q.peek().row;
            int c = q.peek().col;
            int p = q.peek().path;
            q.remove();

            if(r == n - 1 && c == n - 1){
                return p;
            }
            for(int delr = -1; delr <= 1; delr++){
                for(int delc = -1; delc <= 1; delc++){
                    int nrow = r + delr;
                    int ncol = c + delc;

                    if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] == 0){
                        if(p + 1 < dist[nrow][ncol]){
                            dist[nrow][ncol] = p + 1;
                            q.add(new Tuple(nrow, ncol, p + 1));
                        }
                    }
                }
            }
        }
        return -1;

    }

    class Tuple{
        int row;
        int col;
        int path;

        public Tuple(int row, int col, int path){
            this.row = row;
            this.col = col;
            this.path = path;
        }
    }
}

/*
TC: O(N*M*4) 
SC: O(N*N) [For dis]
*/