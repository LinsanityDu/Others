/*第一轮：很nice的白人小哥。上来自我介绍，然后coderpad做题，0,1矩阵表示ocean和land，求其中最大land的面积，上下左右四个方向表示连通。 
DFS秒杀，时间复杂度O(rows*cols)，要求优化，想了想BFS也是O(rows*cols)，似乎没什么改进，但是实在想不出别的办法，感觉O(rows*cols)是最优解，因为无论如何需要遍历矩阵一遍。索性直接提出BFS，说明BFS没有DFS中recursion的function cost，所以更好，小哥很满意。*/


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