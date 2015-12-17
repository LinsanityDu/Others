就是卖有手续费，而且手续费恒定，所以我就是在II的基础上算profit时候减去手续费

public int maxProfit(int[] prices, int[] transFee) {
    if(prices == null || prices.length == 0) {
        return 0;
    }
    int maxProfit = 0;
    for(int i=1; i < prices.length; i++) {
        int buy = prices[i-1]+transFee[i-1];
        int sell = prices-transFee[i-1];
        if(sell > buy) {
            maxProfit += sell-buy;
        }
    }    
    return maxProfit;
}


well, what about this
用动态规划转化为背包问题。对于每一个交易日我们选择 1.买2.卖3.不动
那么定义子问题dp(n, STATE)为在第n个交易日进行买/卖活动后最大获益。其中STATE={0, 1},0表示买入，1表示卖出。状态转移方程
dp【i】[0] = max(dp[i-1][0], dp[i-1][1]-prices【i】-trandingFee)
dp【i】[1] = max(dp[i-1][1], dp[i-1][0]+prices【i】-trandingFee))

return max(dp[n-1][0], dp[n-1][1])

O(n)


public int stock5(int[] prices, int fee) {
  int n = prices.length;
  int dp[][] = new int[n][2];
  dp[0][0] = -prices[0];
  dp[0][1] = 0;
  // 0 is buy, 1 is sell
  for (int i = 1; i < n; i++) {
   dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
   dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
  }
  return dp[n - 1][1];
 }



private int stock(int prices[], int fee) {
    int afterBuy = -prices[0];
    int afterSell = 0;
    for (int i = 1; i < prices.length; i++) {
      int oldBuy = afterBuy;
      int oldSell = afterSell;
      afterBuy = Math.max(oldBuy, oldSell - prices[i]);
      afterSell = Math.max(oldSell, oldBuy + prices[i] - fee);
    }
    return afterSell;
  }
