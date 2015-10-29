/*There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?*/


public class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int m = costs.length, n = costs[0].length, m1 = 0, m2 = 0;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            int t1 = m1, t2 = m2;
            m1 = Integer.MAX_VALUE;
            m2 = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                dp[j] = (dp[j] == t1 ? t2 : t1) + costs[i][j];
                if (m1 <= dp[j]) {
                    m2 = Math.min(dp[j], m2);
                }
                else {
                    m2 = m1;
                    m1 = dp[j];
                }
            }
        }

        return m1;
    }
}