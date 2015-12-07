/*第一轮：很nice的白人小哥。上来自我介绍，然后coderpad做题，0,1矩阵表示ocean和land，求其中最大land的面积，上下左右四个方向表示连通。 
DFS秒杀，时间复杂度O(rows*cols)，要求优化，想了想BFS也是O(rows*cols)，似乎没什么改进，但是实在想不出别的办法，感觉O(rows*cols)是最优解，因为无论如何需要遍历矩阵一遍。索性直接提出BFS，说明BFS没有DFS中recursion的function cost，所以更好，小哥很满意。*/

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


可以用BFS
// BFS
    int count=0;
    for(int i=0;i<grid.length;i++)
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]=='1'){
                bfsFill(grid,i,j);
                count++;
            }
        }
    return count;
}
private void bfsFill(char[][] grid,int x, int y){
    grid[x][y]='0';
    int n = grid.length;
    int m = grid[0].length;
    LinkedList<Integer> queue = new LinkedList<Integer>();  
    int code = x*m+y;  
    queue.offer(code);  
    while(!queue.isEmpty())  
    {  
        code = queue.poll();  
        int i = code/m;  
        int j = code%m;  
        if(i>0 && grid[i-1][j]=='1')    //search upward and mark adjacent '1's as '0'.
        {  
            queue.offer((i-1)*m+j);  
            grid[i-1][j]='0';  
        }  
        if(i<n-1 && grid[i+1][j]=='1')  //down
        {  
            queue.offer((i+1)*m+j);  
            grid[i+1][j]='0';  
        }  
        if(j>0 && grid[i][j-1]=='1')  //left
        {  
            queue.offer(i*m+j-1);  
            grid[i][j-1]='0';  
        }  
        if(j<m-1 && grid[i][j+1]=='1')  //right
        {  
            queue.offer(i*m+j+1);  
            grid[i][j+1]='0';  
        }
    } 
} 