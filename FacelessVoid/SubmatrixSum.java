/*Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.*/

O(N3) 解法
public class Solution {
    /**
     * @param matrix an integer matrix
     * @return the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        int[][] result = new int[2][2];
        int M = matrix.length;
        if (M == 0) return result;
        int N = matrix[0].length;
        if (N == 0) return result;
        // pre-compute: sum[i][j] = sum of submatrix [(0, 0), (i, j)]
        int[][] sum = new int[M+1][N+1];
        for (int j=0; j<=N; ++j) sum[0][j] = 0;
        for (int i=1; i<=M; ++i) sum[i][0] = 0;
        for (int i=0; i<M; ++i) {
            for (int j=0; j<N; ++j)
                sum[i+1][j+1] = matrix[i][j] + sum[i+1][j] + sum[i][j+1] - sum[i][j];
        }
        for (int l=0; l<M; ++l) {
            for (int h=l+1; h<=M; ++h) {
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int j=0; j<=N; ++j) {
                    int diff = sum[h][j] - sum[l][j];
                    if (map.containsKey(diff)) {
                        int k = map.get(diff);
                        result[0][0] = l;   result[0][1] = k;
                        result[1][0] = h-1; result[1][1] = j-1;
                        return result;
                    } else {
                        map.put(diff, j);
                    }
                }
            }
        }
        return result;
    }
}


int existMatrix(vector>& matrix, int target) {
int m = matrix.size(), n = matrix[0].size();
for (int i = 1; i <= m; ++i) {
for (int j = 1; j <= n; ++j) {
matrix[j] += matrix[i-1][j]; //指的是第j列从上到下累加到第i行的元素和，
}
}
int dp[n+1];
dp[0] = matrix[0][0];
unordered_set st;
for (int i = 1; i <= m; ++i) {
for (int j = 0; j < i; ++j) {
for (int k = 1; k <= n; ++k) {
dp[k] = matrix[k] - matrix[j][k]; //指的是第k列，从第j行到第i行的元素和
dp[k] += dp[k-1]; //从第j行到第i行,前k列的元素和
st.insert(dp[k]);
}
for (int k = 0; k <= n; ++k) {
if (st.find(dp[k] - target) != st.end()) {
return true;
}
}
st.clear();
}
}
return false;
}