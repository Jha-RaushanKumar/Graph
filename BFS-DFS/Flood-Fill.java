/*
733. Flood Fill
https://leetcode.com/problems/flood-fill/

An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
Return the modified image after performing the flood fill.

Example 1:
Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.

Example 2:
Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
Output: [[0,0,0],[0,0,0]]
Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 
Constraints:
m == image.length
n == image[i].length
1 <= m, n <= 50
0 <= image[i][j], color < 216
0 <= sr < m
0 <= sc < n
*/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        
        int m = image.length;
        int n = image[0].length;

        boolean vis[][] = new boolean[m][n];

        int initColor = image[sr][sc];

        //Method1 : DFS
        dfs(image,sr, sc, initColor, color, vis);
        
        //Method2 : BFS
        /*
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sr, sc));
        vis[sr][sc] = true;
        image[sr][sc] = color;

        while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;
            q.remove();
            int dr[] = {-1,0,1,0};
            int dc[] = {0,1,0,-1};

            for(int k = 0; k < 4; k++){
                int nrow = r + dr[k];
                int ncol = c + dc[k];

                if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && image[nrow][ncol] == initColor && !vis[nrow][ncol]){
                    vis[nrow][ncol] = true;
                    image[nrow][ncol] = color;
                    q.add(new Pair(nrow,ncol));
                }
            }
        }
        */
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int initColor, int color, boolean vis[][]){
        int m = image.length;
        int n = image[0].length;

        vis[sr][sc] = true;
        image[sr][sc] = color;
        int dr[] = {-1,0,1,0};
        int dc[] = {0,1,0,-1};

        for(int k = 0; k < 4; k++){
            int nrow = sr + dr[k];
            int ncol = sc + dc[k];

            if(nrow >=0 && nrow < m && ncol >=0 && ncol < n && image[nrow][ncol] == initColor && !vis[nrow][ncol]){
                vis[nrow][ncol] = true;
                image[nrow][ncol] = color;
                dfs(image,nrow, ncol, initColor, color, vis);
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