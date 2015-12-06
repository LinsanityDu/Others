/*
// 给定二维数组，表示一片海洋，1代表有岛，0代表没岛。返回相连岛屿的大小。

// Input:
//[["1", "0", "0", "1"],
// ["1", "0", "0", "1"],
// ["1", "1", "0", "0"]]
// Output:
// [4, 2]

// Input:
//[["1", "1", "1", "0"],
// ["1", "0", "1", "0"],
// ["1", "1", "1", "0"]]
// Output:
// [8]
*/
import java.util.*;
public class Solution{
    
    public List<Integer> getSize(int[][] matrix){
        List<Integer> res = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0){
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] marked = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!marked[i][j] && matrix[i][j] == 1){
                    res.add(dfs(matrix, i, j, marked));
                }
            }
        }
        return res;
    }
    
    private int dfs(int[][] matrix, int i, int j, boolean[][] marked){
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length){
            return 0;
        }
        if(marked[i][j]){
            return 0;
        }
        if(matrix[i][j] == 0){
            return 0;
        }
        marked[i][j] = true;
        return 1 + dfs(matrix, i + 1, j, marked) + dfs(matrix, i - 1, j, marked) + 
            dfs(matrix, i, j + 1, marked) + dfs(matrix, i, j - 1, marked);
    }
    
    
    
    
    public static void main(String[] args){
        //[["1", "0", "0", "1"],
// ["1", "0", "0", "1"],
// ["1", "1", "0", "0"]]i
        int[][] matrix = {{1, 1, 1, 0},
                          {1, 0, 1, 0},
                          {1, 1, 0, 1}};
        
        Solution ins = new Solution();
        System.out.println(ins.getSize(matrix));
    }
    
    
    
    
    
    
}