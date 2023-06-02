/*
1631. Path With Minimum Effort
https://leetcode.com/problems/path-with-minimum-effort/

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Example 1:
Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells. This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Example 2:
Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

Example 3:
Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.
 
Constraints:
rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
*/

class Solution {
    public int minimumEffortPath(int[][] heights) {
        
        int m = heights.length;
        int n = heights[0].length;

        int dist[][] = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dist[i][j] = Integer.MAX_VALUE;
             }
        }
        dist[0][0] = 0;

        PriorityQueue<Tuple> pq = new PriorityQueue<>((a,b) -> a.effort - b.effort);
        pq.add(new Tuple(0, 0, 0));

        while(!pq.isEmpty()){
            int r = pq.peek().row;
            int c = pq.peek().col;
            int eff = pq.peek().effort;
            pq.remove();
            if(r == m - 1 && c == n - 1){
                return eff;
            }

            int delr[] = {-1, 0, 1, 0};
            int delc[] = {0, 1, 0, -1};
            for(int i = 0; i < 4; i++){
                int nrow = r + delr[i];
                int ncol = c + delc[i];

                if(nrow >= 0 && nrow < m && ncol >= 0 && ncol < n){
                    int newEff = Math.abs(heights[nrow][ncol] - heights[r][c]);
                    int maxEff = Math.max(eff, newEff);
                    if(maxEff < dist[nrow][ncol]){
                        dist[nrow][ncol] = maxEff;
                        pq.add(new Tuple(nrow, ncol, maxEff));
                    }
                }
            }
        }
        return -1;
    }
    class Tuple{
        int row;
        int col;
        int effort;

        public Tuple(int row, int col, int effort){
            this.row = row;
            this.col = col;
            this.effort = effort;
        }
    }
}

/*
TC: O(N*M*4) 
SC: O(N*N) [For dis]
*/