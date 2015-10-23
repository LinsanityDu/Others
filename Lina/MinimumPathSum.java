/*Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/

// My suck code
public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] state = new int[m][n];
        state[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            state[i][0] = state[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            state[0][i] = state[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                state[i][j] = Math.min(state[i - 1][j], state[i][j - 1]) + grid[i][j];
            }
        }
        return state[m - 1][n - 1];
    }
}

// Modify the input
public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid[0].length;
        int n = grid.length;
        for(int i=1; i<n ; i++) grid[i][0]+=grid[i-1][0];
        for(int j=1; j<m ; j++) grid[0][j]+=grid[0][j-1];

        for(int i=1; i<n ; i++){
            for(int j=1; j<m ; j++){
                grid[i][j]+=Math.min(grid[i-1][j],grid[i][j-1]);
            }
        }

        return grid[n-1][m-1];
    }
}


// Optimiza Space (Good Thought)
public class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[row];
        dp[0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i] = dp[i - 1] + grid[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0] += grid[0][j];
            for (int i = 1; i < row; i++) {
                dp[i] = Math.min(dp[i], dp[i - 1]) + grid[i][j];
            }
        }
        return dp[row - 1];
    }
}


// 滚动数组
public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] state = new int[2][n];
        state[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            state[0][i] = state[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            state[i % 2][0] = state[(i - 1) % 2][0] + grid[i][0];
            for (int j = 1; j < n; j++) {
                state[i % 2][j] = Math.min(state[(i - 1) % 2][j], state[i % 2][j - 1]) + grid[i][j];
            }
        }
        return state[(m - 1) % 2][n - 1];
    }
}
