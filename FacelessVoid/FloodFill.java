1. 给定matrix，只有0和1，求1的连通size，连通只算上下左右，不算对角线。比如：
0 1 0 0 1
1 1 1 0 0
1 0 0 0 1
0 0 0 0 1                   返回 5， 1， 2
反正不难，DFS的时候check那个点有没有被访问过就好了。不过过程中有点小问题，reference有点没处理好。后来想改，甚至说了不需要reference了，可以直接返回int值，不需要这么麻烦了。但是面试官说算了，时间不多了，他知道了。



坑点： 1，看看是不是要求in-place, 还是要求返回一个新矩阵
2，只需要从起点开始做DFS

// This is the text editor interface. 
// Anything you type or change here will be seen by the other person in real time.
/*
1. flood fill。感谢地里面经：here and here and here 
就上面题目的各种变种，题目是有一个矩阵. 
1代表已经染色，0代表没有染色。
完成一个函数，
输入：矩阵a，整数x， 整数y
输出: 
返回一个矩阵，为以(x,y)点（0-based）为开始点的染色结果，将其周围区域染色，直到遇到已经染色的位置或边界为止。
若(x, y)已经染色则直接返回。注意：只能向上下左右四个方向染色。
输入样例：
111111
111001
100110
2 1
输出样例：
111111
111001
111110
*/

public class Solution{
    
    public int[][] floodFill(int[][] matrix, int x, int y){
        if(matrix == null || matrix.length == 0){
            return null;
        }
        if(!isValidIdx(matrix, x, y)){
            return matrix;
        }
        if(matrix[x][y] == 1){
            return matrix;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                res[i][j] = matrix[i][j];
            }
        }
        boolean[][] marked = new boolean[m][n];
        dfs(res, x, y, marked);
        return res;
    }
    
    private void dfs(int[][] matrix, int i, int j, boolean[][] marked){
        if(!isValidIdx(matrix, i, j)){
            return;
        }
        if(marked[i][j]){
            return;
        }
        if(matrix[i][j] == 1){
            return;
        }
        marked[i][j] = true;
        matrix[i][j] = 1;
        dfs(matrix, i + 1, j, marked);
        dfs(matrix, i - 1, j, marked);
        dfs(matrix, i, j + 1, marked);
        dfs(matrix, i, j - 1, marked);
    }
    
    private boolean isValidIdx(int[][] matrix, int i, int j){
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }
    
    public static void main(String[] args){
        int[][] matrix = {{1,1,1,1,1,1},
                          {1,1,1,0,0,1},
                          {1,0,0,1,1,0}};
        int[][] res = new Solution().floodFill(matrix, 2, 1);
        for(int i = 0; i < res.length; i++){
            for(int j = 0; j < res[0].length; j++){
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }    
}

1代表已经染色，0代表没有染色。
完成一个函数，
输入：矩阵a，整数x， 整数y
输出: 
返回一个矩阵，为以(x,y)点（0-based）为开始点的染色结果，将其周围区域染色，直到遇到已经染色的位置或边界为止。
若(x, y)已经染色则直接返回。注意：只能向上下左右四个方向染色。
输入样例：
111111
111001
100110
2
1
输出样例：
111111
111001
111110. 

public class Solution {
    private row = 0;
    private col = 0;
    
    public void fillBlack(int[][] a, int x, int y) {
        if (a == null || x == null || y == null) {
            return;
        }
        
        row = a.length;
        col = a[0].length;
        dfs(a, x, y);
    }
    
    public void dfs(int[][] a, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col || a[i][j] != 0) {
            return;
        }
        a[i][j] = 1;
        dfs(a, i - 1, j);
        dfs(a, i + 1, j);
        dfs(a, i, j + 1);
        dfs(a, i, j - 1);
     
    } 
    
    
}