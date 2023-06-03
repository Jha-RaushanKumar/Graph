/*

*/

class Solution
{
    public void shortest_distance(int[][] matrix)
    {
        // Code here
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == -1){
                    matrix[i][j] = (int)1e9;
                }
            }
        }
        
        //Take min via all paths
        for(int via = 0; via < matrix.length; via++){
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    if(matrix[i][via] == (int)1e9 || matrix[via][j] == (int)1e9){
                        continue;
                    }
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][via] + matrix[via][j]);
                }
            }
        }
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == (int)1e9){
                    matrix[i][j] = -1;
                }
            }
        }
        
        //To check negative cycle
        /*
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][i] < 0){
                return true
            }
        }
        */
    }
}

/*
TC: O(N^3) 
SC: O(N*M)
*/